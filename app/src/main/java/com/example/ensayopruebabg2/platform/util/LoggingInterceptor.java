package com.example.ensayopruebabg2.platform.util;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        long endTime = 0;
        // Registra la solicitud
        logRequest(request);
        Response response = null;
        try {
            response = chain.proceed(request);
            endTime = System.currentTimeMillis();
        } catch (Exception ex) {
            Log.e("Response", "Error de conexion", ex);
            throw ex;
        }
        // Registra la respuesta
        return logResponse(response, startTime, endTime);
    }

    private void logRequest(Request request) {
        try {
            // Log de la solicitud
            Log.d("Request", String.format("Sending request %s on %s%n%s",
                    request.url(), request.method(), request.headers()));

            // Log del cuerpo de la solicitud si lo deseas
            if (request.body() != null) {
                Buffer buffer = new Buffer();
                request.body().writeTo(buffer);
                Log.d("Request", "Request Body: " + buffer.readUtf8());
            }
        } catch (Exception ex) {
            Log.e("Request", "Error al registrar log de request", ex);
        }
    }

    private Response logResponse(Response response, long startTime, long endTime) throws IOException {
        // Log de la respuesta
        Log.d("Response", String.format("Received response for %s in %dms %n %s status code: %d",
                response.request().url(), endTime - startTime, response.headers(), response.code()));
        long requestSize = response.request().body() == null ? 0 : response.request().body().contentLength();
        long responseSize = response.body() == null ? 0 : response.body().contentLength();

        // Log del cuerpo de la respuesta si lo deseas
        ResponseBody responseBody = response.body();
        String responseBodyString = null;
        if (responseBody != null) {
            responseBodyString = responseBody.string();
            Log.d("Response", "Response Body: " + responseBodyString);

            // Recrear el cuerpo de la respuesta para que esté disponible para la posterior lectura
            responseBody = ResponseBody.create(responseBody.contentType(), responseBodyString);
            response = response.newBuilder().body(responseBody).build();
        }

        return response;
    }

}