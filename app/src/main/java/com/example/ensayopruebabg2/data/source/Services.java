package com.example.ensayopruebabg2.data.source;

import com.example.ensayopruebabg2.data.entity.CommentEntity;
import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.data.entity.UserEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface Services {
    @GET("posts")
    Call<List<PostEntity>> getPosts();

    @GET("posts/{id}/comments")
    Call<List<CommentEntity>> getComments(
            @Path(value = "id", encoded = true) String id
            );

    @GET("users")
    Call<List<UserEntity>> getUsers();

}

