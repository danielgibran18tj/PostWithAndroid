package com.example.ensayopruebabg2.presentation.presenter;


import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.data.source.local.temp.LocalStorage;
import com.example.ensayopruebabg2.platform.di.navigation.Navigator;
import com.example.ensayopruebabg2.platform.views.base.BaseActivity;
import com.example.ensayopruebabg2.presentation.view.SecondView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.List;

public class SecondPresenterImpl extends BaseActivity implements SecondPresenter{

    private final LocalStorage localStorage;
    private final Navigator navigator;
    private WeakReference<SecondView> view;

    public SecondPresenterImpl(LocalStorage localStorage, Navigator navigator) {
        this.localStorage = localStorage;
        this.navigator = navigator;
    }

    public void setView(SecondView secondView){
        view = new WeakReference<>(secondView);
    }

    @Override
    public void viewPosts(String postsJson) {
        localStorage.setPosts(postsJson);
        Gson gson = new Gson();
        Type postsListType = new TypeToken<List<PostEntity>>() {}.getType();
        List<PostEntity> posts = gson.fromJson(postsJson, postsListType);
        view.get().navigateToFragment(navigator.navigateToSelectPosts(posts));
    }

    @Override
    public void addNewPost(String userId, String id, String title, String body){
        PostEntity postNew = new PostEntity(Integer.parseInt(userId) , Integer.parseInt(id) , title, body);
        String postsJson = localStorage.getPosts();

        Gson gson = new Gson();
        Type postsListType = new TypeToken<List<PostEntity>>() {}.getType();
        List<PostEntity> posts = gson.fromJson(postsJson, postsListType);
        posts.add(postNew);
        view.get().navigateToFragment(navigator.navigateToSelectPosts(posts));

    }

    @Override
    public void toLogin() {
        navigator.navigateToLogin();
    }

}
