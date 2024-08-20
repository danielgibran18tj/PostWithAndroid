package com.example.ensayopruebabg2.platform.di.navigation;

import androidx.fragment.app.Fragment;

import com.example.ensayopruebabg2.domain.model.PostModel;

import java.util.List;

public interface Navigator {
    void navigateToLogin();

    void navigateToSecond(List<PostModel> posts);

    Fragment navigateToSelectPosts(List<PostModel> posts);

    Fragment navigateToDetailsPost(PostModel post);

    }
