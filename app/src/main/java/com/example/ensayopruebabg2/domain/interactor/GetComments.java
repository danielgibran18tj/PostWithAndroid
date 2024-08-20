package com.example.ensayopruebabg2.domain.interactor;

import android.util.Log;

import com.example.ensayopruebabg2.data.entity.CommentEntity;
import com.example.ensayopruebabg2.domain.executor.JobScheduler;
import com.example.ensayopruebabg2.domain.executor.UIScheduler;
import com.example.ensayopruebabg2.domain.repository.Repository;

import java.util.List;

import io.reactivex.Single;

public class GetComments extends UseCase<List<CommentEntity>, GetComments.Params>{

    private final Repository repository;

    public GetComments(Repository repository,
                       UIScheduler uiScheduler,
                       JobScheduler jobScheduler) {
        super(uiScheduler, jobScheduler);
        this.repository = repository;
    }


    @Override
    Single<List<CommentEntity>> buildUseCaseObservable(final Params params) {
        return Single.create(emitter -> {
            try {
                List<CommentEntity> comments = repository.getListComments(params.getId());
                emitter.onSuccess(comments);
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
