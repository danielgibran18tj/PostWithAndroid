package com.example.ensayopruebabg2.platform;

import android.app.Service;

import com.example.ensayopruebabg2.platform.di.app.AppComponent;
import com.example.ensayopruebabg2.platform.di.app.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerApplication;

public class App extends DaggerApplication  {

    @Inject
    DispatchingAndroidInjector<Service> dispatchingServiceInjector;

    public DispatchingAndroidInjector<Service> serviceInjector() {
        return dispatchingServiceInjector;
    }

    @Override
    protected AndroidInjector<App> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);

        return appComponent;
    }
}
