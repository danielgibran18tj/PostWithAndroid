package com.example.ensayopruebabg2.platform.di.navigation;

import androidx.fragment.app.Fragment;

import com.example.ensayopruebabg2.data.entity.PostEntity;

import java.util.List;

public interface Navigator {
    void navigateToLogin();

    void navigateToSecond(List<PostEntity> posts);

    Fragment navigateToSelectPosts(List<PostEntity> posts);

    Fragment navigateToDetailsPost(PostEntity post);

    }
