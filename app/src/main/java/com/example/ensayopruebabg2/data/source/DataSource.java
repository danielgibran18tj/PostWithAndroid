package com.example.ensayopruebabg2.data.source;

import com.example.ensayopruebabg2.data.entity.CommentEntity;
import com.example.ensayopruebabg2.data.entity.LoginRequestBody;
import com.example.ensayopruebabg2.data.entity.PokemonResponse;
import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.data.entity.UserEntity;

import java.io.IOException;
import java.util.List;

public interface DataSource {

    List<PostEntity> fetchPosts() throws IOException;

    String signIn(LoginRequestBody loginRequestBody) throws IOException;

    List<CommentEntity> fetchComments(String id) throws IOException;

    List<UserEntity> fetchUsers() throws IOException;

    PokemonResponse fetchImgs(String id) throws IOException;
}
