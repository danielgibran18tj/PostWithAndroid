package com.example.ensayopruebabg2.domain.interactor;

import android.util.Log;

import com.example.ensayopruebabg2.data.entity.UserEntity;
import com.example.ensayopruebabg2.domain.executor.JobScheduler;
import com.example.ensayopruebabg2.domain.executor.UIScheduler;
import com.example.ensayopruebabg2.domain.repository.Repository;

import io.reactivex.Single;

public class GetUser extends UseCase<UserEntity, GetUser.Params>{

    private final Repository repository;

    public GetUser(UIScheduler uiScheduler, JobScheduler jobScheduler, Repository repository) {
        super(uiScheduler, jobScheduler);
        this.repository = repository;
    }

    @Override
    Single<UserEntity> buildUseCaseObservable(Params params) {
        return Single.create(emitter -> {
            try {
                UserEntity user = repository.getUser(params.getId());
                emitter.onSuccess(user);
            } catch (Exception exception) {
                Log.e(this.toString(), "login", exception);
                if (!emitter.isDisposed()) {
                    emitter.onError(exception);
                }
            }
        });
    }

    public static final class Params{
        private final int id;

        public Params(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
