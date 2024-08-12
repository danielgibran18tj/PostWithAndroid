package com.example.ensayopruebabg2.presentation.presenter;

import com.example.ensayopruebabg2.presentation.view.SecondView;

public interface SecondPresenter {

    void setView(SecondView secondView);

    void viewPosts(String listPosts);

    void addNewPost(String UserId, String Id, String Title, String Body);

    void toLogin();
}
