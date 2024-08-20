package com.example.ensayopruebabg2.domain.repository;

import com.example.ensayopruebabg2.data.entity.CommentEntity;
import com.example.ensayopruebabg2.data.entity.UserEntity;
import com.example.ensayopruebabg2.domain.model.PostModel;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface Repository {
    List<PostModel> signIn(boolean isOnlyOnline, String user, String password) throws IOException, JSONException;

    List<CommentEntity> getListComments(int id) throws IOException;

    UserEntity getUser(int userId) throws IOException;
}
