package com.example.ensayopruebabg2.platform.util;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManagerServices {
    private static Retrofit createRetrofit(String baseUrl) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(80, TimeUnit.SECONDS)
                .readTimeout(80, TimeUnit.SECONDS)
                .writeTimeout(80, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor());

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .client(httpClientBuilder.build())
                .build();
    }

    public static Retrofit getApiServiceA() {
        return createRetrofit("https://jsonplaceholder.typicode.com/");
    }

    public static Retrofit getApiServiceB() {
        return createRetrofit("https://api.escuelajs.co/");
    }


    public static Retrofit getApiServiceC() {
        return createRetrofit("https://pokeapi.co/api/v2/");
    }
}
