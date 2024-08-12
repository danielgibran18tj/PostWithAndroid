package com.example.ensayopruebabg2.platform.di.activitys;

import com.example.ensayopruebabg2.data.source.local.temp.LocalStorage;
import com.example.ensayopruebabg2.domain.interactor.UseCaseFactory;
import com.example.ensayopruebabg2.platform.di.navigation.Navigator;
import com.example.ensayopruebabg2.platform.views.activities.LoginActivity;
import com.example.ensayopruebabg2.presentation.presenter.LoginPresenter;
import com.example.ensayopruebabg2.presentation.presenter.LoginPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
@SuppressWarnings("WeakerAccess")
public class LoginActivityModule {
    @Provides
    LoginPresenter providePresenter(LoginActivity activity, UseCaseFactory useCaseFactory, Navigator navigator, LocalStorage localStorage){
        LoginPresenter presenter = new LoginPresenterImpl(useCaseFactory, navigator, localStorage);
        presenter.setView(activity);
        return presenter;
    }
}