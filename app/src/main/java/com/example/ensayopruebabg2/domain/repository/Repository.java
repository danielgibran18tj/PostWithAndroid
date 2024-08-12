package com.example.ensayopruebabg2.domain.repository;

import com.example.ensayopruebabg2.data.entity.CommentEntity;
import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.data.entity.UserEntity;

import java.io.IOException;
import java.util.List;

public interface Repository {
    List<PostEntity> signIn(boolean isOnlyOnline, String user, String password) throws IOException;

    List<CommentEntity> getListComments(int id) throws IOException;

    UserEntity getUser(int userId) throws IOException;
}
