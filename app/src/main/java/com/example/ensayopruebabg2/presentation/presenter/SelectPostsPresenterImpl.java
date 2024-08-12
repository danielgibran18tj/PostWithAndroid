package com.example.ensayopruebabg2.presentation.presenter;

import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.platform.di.navigation.Navigator;
import com.example.ensayopruebabg2.presentation.view.SelectPostsView;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class SelectPostsPresenterImpl extends BasePresenter implements SelectPostsPresenter {

    private final Navigator navigator;
    private WeakReference<SelectPostsView> view;

    @Inject
    public SelectPostsPresenterImpl(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void setView(SelectPostsView selectPostsView) {
        view = new WeakReference<>(selectPostsView);
    }

    @Override
    public void showDetails(PostEntity post) {
        view.get().navigate(navigator.navigateToDetailsPost(post));
    }
}
