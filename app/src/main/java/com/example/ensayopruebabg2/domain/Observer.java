package com.example.ensayopruebabg2.domain;

import androidx.annotation.NonNull;

import io.reactivex.observers.DisposableSingleObserver;

public class Observer<T> extends DisposableSingleObserver<T> {

    @Override
    public void onSuccess(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

}
