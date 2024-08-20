package com.example.ensayopruebabg2.data;

import android.util.Log;

import com.example.ensayopruebabg2.data.entity.CommentEntity;
import com.example.ensayopruebabg2.data.entity.LoginRequestBody;
import com.example.ensayopruebabg2.data.entity.PokemonResponse;
import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.data.entity.UserEntity;
import com.example.ensayopruebabg2.data.source.DataSource;
import com.example.ensayopruebabg2.domain.model.PostModel;
import com.example.ensayopruebabg2.domain.repository.Repository;

import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.ArrayList;
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
    public List<PostModel> signIn(boolean isOnlyOnline, String user, String password) throws IOException, JSONException {
        ModelMapper modelMapper = new ModelMapper();
        String token = dataSource.signIn(new LoginRequestBody(user, password));
//        localStorage.setToken(token);
        if (token != null) {
            List<PostEntity> postEntities = dataSource.fetchPosts();
            List<PostModel> postModels = new ArrayList<>();
            for (int i = 0; i < postEntities.size(); i++) {
                try {
                    PokemonResponse dataImg = dataSource.fetchImgs(String.valueOf(i+1));
                    String img = dataImg.getSprites().getOther().getDream_world().getFront_default();
                    if (img != null) postEntities.get(i).setImg(img);
                } catch (Exception e){
                    Log.i("img error", e.getMessage());
                    postEntities.get(i).setImg(null);
                }
                postModels.add(modelMapper.map(postEntities.get(i), PostModel.class));
            }
            return postModels;
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
