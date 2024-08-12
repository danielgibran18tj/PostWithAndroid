package com.example.ensayopruebabg2.presentation.presenter;

import androidx.annotation.NonNull;

import com.example.ensayopruebabg2.data.entity.CommentEntity;
import com.example.ensayopruebabg2.data.entity.UserEntity;
import com.example.ensayopruebabg2.domain.Observer;
import com.example.ensayopruebabg2.domain.interactor.GetComments;
import com.example.ensayopruebabg2.domain.interactor.GetUser;
import com.example.ensayopruebabg2.domain.interactor.UseCaseFactory;
import com.example.ensayopruebabg2.platform.di.navigation.Navigator;
import com.example.ensayopruebabg2.presentation.view.DetailsPostView;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public class DetailsPostPresenterImpl extends BasePresenter implements DetailsPostPresenter {

    private final UseCaseFactory useCaseFactory;
    private final Navigator navigator;
    private WeakReference<DetailsPostView> view;

    @Inject
    public DetailsPostPresenterImpl(UseCaseFactory useCaseFactory, Navigator navigator) {
        this.useCaseFactory = useCaseFactory;
        this.navigator = navigator;
    }

    @Override
    public void setView(DetailsPostView detailsPostView) {
        view = new WeakReference<>(detailsPostView);
    }

    @Override
    public void getListComments(int id) {
        GetComments useCase = useCaseFactory.getComments();
        addDisposable(useCase.execute(new CommentObserver(),
                new GetComments.Params( id )));
    }

    @Override
    public void getUser(int id) {
        GetUser useCase = useCaseFactory.getUser();
        addDisposable(useCase.execute(new UserObserver(),
                new GetUser.Params( id )));
    }

    private final class CommentObserver extends Observer<List<CommentEntity>> {

        @Override
        public void onSuccess(@NonNull List<CommentEntity> comments) {
             view.get().showListComments(comments);
        }

        @Override
        public void onError(Throwable ignored) {

        }
    }

    private final class UserObserver extends Observer<UserEntity> {

        @Override
        public void onSuccess(@NonNull UserEntity user) {
            view.get().showUser(user);
        }

        @Override
        public void onError(Throwable ignored) {

        }
    }


}
