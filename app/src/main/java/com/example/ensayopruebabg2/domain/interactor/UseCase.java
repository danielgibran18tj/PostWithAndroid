package com.example.ensayopruebabg2.domain.interactor;

import com.example.ensayopruebabg2.domain.executor.JobScheduler;
import com.example.ensayopruebabg2.domain.executor.UIScheduler;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public abstract class UseCase<T, Params> {

    private final UIScheduler uiScheduler;
    private final JobScheduler jobScheduler;

    UseCase(UIScheduler uiScheduler, JobScheduler jobScheduler) {
        this.uiScheduler = uiScheduler;
        this.jobScheduler = jobScheduler;
    }

    abstract Single<T> buildUseCaseObservable(Params params);

    public Disposable execute(DisposableSingleObserver<T> observer, Params params) {
        final Single<T> observable = this.buildUseCaseObservable(params)
                .observeOn(uiScheduler.getScheduler())
                .subscribeOn(jobScheduler.getScheduler());
        return observable.subscribeWith(observer);
    }

}
