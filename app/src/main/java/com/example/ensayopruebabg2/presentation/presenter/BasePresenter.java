package com.example.ensayopruebabg2.presentation.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter {

    private final CompositeDisposable disposables = new CompositeDisposable();

    void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    public void destroy(){
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
