package com.example.ensayopruebabg2.presentation.presenter;


import com.example.ensayopruebabg2.presentation.view.LoginView;

public interface LoginPresenter {
    void setView(LoginView loginView);
    void signIn();
}
