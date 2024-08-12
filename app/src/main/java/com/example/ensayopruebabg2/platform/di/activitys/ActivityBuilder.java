package com.example.ensayopruebabg2.platform.di.activitys;

import com.example.ensayopruebabg2.platform.views.activities.LoginActivity;
import com.example.ensayopruebabg2.platform.views.activities.SecondActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {LoginActivityModule.class})
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {SecondActivityModule.class})
    abstract SecondActivity bindSecondActivity();

}
