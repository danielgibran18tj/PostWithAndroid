package com.example.ensayopruebabg2.platform.di.app;

import com.example.ensayopruebabg2.data.RepositoryImpl;
import com.example.ensayopruebabg2.data.source.DataSource;
import com.example.ensayopruebabg2.data.source.local.temp.LocalStorage;
import com.example.ensayopruebabg2.domain.repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public Repository provideSessionRepository(
//            LocalStorage localStorage,
            DataSource remoteDataSource
    ) {
        return new RepositoryImpl( remoteDataSource);
    }


}
