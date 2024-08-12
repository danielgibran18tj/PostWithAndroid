package com.example.ensayopruebabg2.data.source;

import com.example.ensayopruebabg2.data.entity.CommentEntity;
import com.example.ensayopruebabg2.data.entity.LoginRequestBody;
import com.example.ensayopruebabg2.data.entity.LoginResponseEntity;
import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.data.entity.UserEntity;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class DataSourceImpl implements DataSource {

    private final AuthService authService;
    private final Services services;


    @Inject
    public DataSourceImpl(AuthService authService, Services services) {
        this.authService = authService;
        this.services = services;
    }

    @Override
    public String signIn(LoginRequestBody loginRequestBody) throws IOException {
        Response<LoginResponseEntity> response = authService.login(loginRequestBody).execute();
        switch (response.code()){
            case 200:
            case 201: if (response.body() != null) return response.body().getAccess_token();

        }
        if (response.code() == 200)
            if (response.body() != null) return response.body().getToken();

        return null;
    }

    @Override
    public List<PostEntity> fetchPosts() throws IOException {
        Response<List<PostEntity>> response = services.getPosts().execute();
        return response.body();
    }

    @Override
    public List<CommentEntity> fetchComments(String id) throws IOException {
        Response<List<CommentEntity>> response = services.getComments(id).execute();
        return response.body();
    }

    @Override
    public List<UserEntity> fetchUsers() throws IOException {
        Response<List<UserEntity>> response = services.getUsers().execute();
        return response.body();
    }

}
