package com.example.ensayopruebabg2.presentation.presenter;

import androidx.annotation.NonNull;

import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.data.source.local.temp.LocalStorage;
import com.example.ensayopruebabg2.domain.Observer;
import com.example.ensayopruebabg2.domain.interactor.SignIn;
import com.example.ensayopruebabg2.domain.interactor.UseCaseFactory;
import com.example.ensayopruebabg2.platform.di.navigation.Navigator;
import com.example.ensayopruebabg2.presentation.view.LoginView;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public class LoginPresenterImpl extends BasePresenter implements LoginPresenter {

    private final UseCaseFactory useCaseFactory;
    private final Navigator navigator;
    private WeakReference<LoginView> view;
    private final LocalStorage localStorage;

    @Inject
    public LoginPresenterImpl(UseCaseFactory useCaseFactory, Navigator navigator, LocalStorage localStorage) {
        this.useCaseFactory = useCaseFactory;
        this.navigator= navigator;
        this.localStorage = localStorage;
    }


    public void setView(LoginView loginView){
        view = new WeakReference<>(loginView);
    }

    @Override
    public void signIn() {
        LoginView loginView = view.get();
        boolean isValid = true;

        if (loginView.getEprUI() == null || loginView.getEprUI().isEmpty()) {
            isValid = false;
        }

        if (loginView.getPasswordUI() == null || loginView.getPasswordUI().isEmpty()) {
            isValid = false;
        }

        if (isValid) {

            SignIn useCase = useCaseFactory.signIn();
            addDisposable(useCase.execute(new SessionObserver(),
                    new SignIn.Params(
                            true,
                            view.get().getEprUI(),
                            view.get().getPasswordUI()
                    )));
        }
    }


    private final class SessionObserver extends Observer<List<PostEntity>> {

        @Override
        public void onSuccess(@NonNull List<PostEntity> listPost) {
            navigator.navigateToSecond(listPost);
        }

        @Override
        public void onError(Throwable ignored) {
            view.get().showFail();
        }

    }

}
