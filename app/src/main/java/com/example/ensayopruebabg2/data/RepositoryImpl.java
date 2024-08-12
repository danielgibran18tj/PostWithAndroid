package com.example.ensayopruebabg2.data;

import com.example.ensayopruebabg2.data.entity.CommentEntity;
import com.example.ensayopruebabg2.data.entity.LoginRequestBody;
import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.data.entity.UserEntity;
import com.example.ensayopruebabg2.data.source.DataSource;
import com.example.ensayopruebabg2.domain.repository.Repository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class RepositoryImpl implements Repository {

//    private final LocalStorage localStorage;
    private final DataSource dataSource;

    @Inject
    public RepositoryImpl( DataSource dataSource) {
//        this.localStorage = localStorage;
        this.dataSource = dataSource;
    }

    @Override
    public List<PostEntity> signIn(boolean isOnlyOnline, String user, String password) throws IOException {
        String token = dataSource.signIn(new LoginRequestBody(user, password));
//        localStorage.setToken(token);
        if (token != null) {
            return dataSource.fetchPosts();
        }
        return null;
    }


    @Override
    public List<CommentEntity> getListComments(int id) throws IOException {
        return dataSource.fetchComments(String.valueOf(id));
    }

    @Override
    public UserEntity getUser(int userId) throws IOException {
        List<UserEntity> users = dataSource.fetchUsers();
        for (UserEntity user : users ) {
            if (user.getId() == userId) return user;
        }
        return null;
    }
}
