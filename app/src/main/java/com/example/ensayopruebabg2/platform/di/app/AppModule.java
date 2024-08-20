package com.example.ensayopruebabg2.platform.di.app;

import android.app.Application;
import android.content.Context;

import com.example.ensayopruebabg2.data.executor.JobThread;
import com.example.ensayopruebabg2.data.source.AuthService;
import com.example.ensayopruebabg2.data.source.DataSource;
import com.example.ensayopruebabg2.data.source.DataSourceImpl;
import com.example.ensayopruebabg2.data.source.Services;
import com.example.ensayopruebabg2.data.source.local.temp.ImgService;
import com.example.ensayopruebabg2.data.source.local.temp.LocalStorage;
import com.example.ensayopruebabg2.data.source.local.temp.LocalStorageImpl;
import com.example.ensayopruebabg2.domain.executor.JobScheduler;
import com.example.ensayopruebabg2.domain.executor.UIScheduler;
import com.example.ensayopruebabg2.platform.di.navigation.Navigator;
import com.example.ensayopruebabg2.platform.di.navigation.NavigatorImpl;
import com.example.ensayopruebabg2.platform.executor.UIThread;
import com.example.ensayopruebabg2.platform.util.ManagerServices;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppModule {


    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    LocalStorage provideLocalStorage(Context context){ return new LocalStorageImpl(context); }

    @Provides
    @Singleton
    Navigator provideNavigation(Context context){
        return new NavigatorImpl(context);
    }

    @Provides
    @Singleton
    DataSource provideSessionDataSource(AuthService authService, Services service, ImgService imgService) {
        return new DataSourceImpl(authService, service, imgService);
    }

    @Provides
    @Singleton
    JobScheduler provideJobScheduler(JobThread jobThread){
        return jobThread;
    }

    @Provides
    @Singleton
    UIScheduler provideUIScheduler(UIThread uiThread){
        return uiThread;
    }

    @Provides
    @Singleton
    @Named("serviceA")
    Retrofit provideRetrofitServiceA() {
        return ManagerServices.getApiServiceA();
    }

    @Provides
    @Singleton
    @Named("serviceB")
    Retrofit provideRetrofitServiceB() {
        return ManagerServices.getApiServiceB();
    }

    @Provides
    @Singleton
    @Named("serviceC")
    Retrofit provideRetrofitServiceC() {
        return ManagerServices.getApiServiceC();
    }

    @Provides
    @Singleton
    Services provideServiceA(@Named("serviceA") Retrofit retrofit) {
        return retrofit.create(Services.class);
    }

    @Provides
    @Singleton
    AuthService provideServiceB(@Named("serviceB") Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }

    @Provides
    @Singleton
    ImgService provideServiceC(@Named("serviceC") Retrofit retrofit) {
        return retrofit.create(ImgService.class);
    }

}
