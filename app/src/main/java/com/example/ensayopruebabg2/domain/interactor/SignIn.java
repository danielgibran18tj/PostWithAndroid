package com.example.ensayopruebabg2.domain.interactor;

import android.util.Log;


import com.example.ensayopruebabg2.domain.executor.JobScheduler;
import com.example.ensayopruebabg2.domain.executor.UIScheduler;
import com.example.ensayopruebabg2.domain.model.PostModel;
import com.example.ensayopruebabg2.domain.repository.Repository;

import java.util.List;

import io.reactivex.Single;

public class SignIn extends UseCase<List<PostModel>, SignIn.Params> {

    private final Repository repository;

    public SignIn(Repository repository,
                  UIScheduler uiScheduler,
                  JobScheduler jobScheduler){
        super(uiScheduler, jobScheduler);
        this.repository = repository;
    }

    @Override
    Single<List<PostModel>> buildUseCaseObservable(final Params params){
        return Single.create(emitter -> {
            try {
                List<PostModel> listPosts = repository.signIn(params.isRemote(), params.getUser(), params.getPassword());
                if (listPosts == null) {
                    throw new Exception("Credenciales incorrectas o lista de publicaciones es nula.");
                }
                emitter.onSuccess(listPosts);
            } catch (Exception exception) {
                Log.e(this.toString(), "login", exception);
                if (!emitter.isDisposed()) {
                    emitter.onError(exception);
                }
            }
        });
    }

    public static final class Params{
        private final boolean remote;
        private final String user;
        private final String password;

        public Params(boolean remote, String user, String password){
            this.remote= remote;
            this.user = user;
            this.password = password;
        }

        private boolean isRemote() {
            return remote;
        }

        String getUser() {
            return user;
        }

        String getPassword() {
            return password;
        }

    }

}