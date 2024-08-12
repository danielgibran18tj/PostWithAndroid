package com.example.ensayopruebabg2.platform.di.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.platform.views.activities.LoginActivity;
import com.example.ensayopruebabg2.platform.views.activities.SecondActivity;
import com.example.ensayopruebabg2.platform.views.fragments.DetailsPostFragment;
import com.example.ensayopruebabg2.platform.views.fragments.SelectPostsFragment;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NavigatorImpl implements Navigator {

    private final Context context;

    @Inject
    public NavigatorImpl(Context context) {
        this.context = context;
    }


    @Override
    public void navigateToLogin(){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    public void navigateToSecond(List<PostEntity> posts) {
        Intent intent = new Intent(context, SecondActivity.class);
        Gson gson = new Gson();
        String postsJson = gson.toJson(posts);
        intent.putExtra("posts", postsJson);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);  // Añadir esta línea al llamado de activitys
        context.startActivity(intent);
    }

    @Override
    public Fragment navigateToSelectPosts(List<PostEntity> posts) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("Posts", (Serializable) posts);
        return SelectPostsFragment.newInstance(bundle);
    }

    @Override
    public Fragment navigateToDetailsPost(PostEntity post) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("Post", post);
        return DetailsPostFragment.newInstance(bundle);
    }

}
