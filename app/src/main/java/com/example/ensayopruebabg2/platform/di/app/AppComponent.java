package com.example.ensayopruebabg2.platform.di.app;

import android.app.Application;

import com.example.ensayopruebabg2.platform.App;
import com.example.ensayopruebabg2.platform.di.activitys.ActivityBuilder;
import com.example.ensayopruebabg2.platform.di.fragment.FragmentBuilder;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        FragmentBuilder.class,
        RepositoryModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Override
    void inject(App app);


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

}
