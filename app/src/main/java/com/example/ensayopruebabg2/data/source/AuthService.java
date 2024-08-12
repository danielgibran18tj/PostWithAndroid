package com.example.ensayopruebabg2.data.source;

import com.example.ensayopruebabg2.data.entity.LoginRequestBody;
import com.example.ensayopruebabg2.data.entity.LoginResponseEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthService {
    @Headers({"Content-Type: application/json"})
    @POST("api/v1/auth/login")
    Call<LoginResponseEntity> login(@Body LoginRequestBody loginRequestBody);
}
