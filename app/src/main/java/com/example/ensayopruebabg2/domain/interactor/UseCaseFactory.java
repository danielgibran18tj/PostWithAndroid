package com.example.ensayopruebabg2.domain.interactor;

import com.example.ensayopruebabg2.data.source.DataSource;
import com.example.ensayopruebabg2.data.source.local.temp.LocalStorage;
import com.example.ensayopruebabg2.domain.executor.JobScheduler;
import com.example.ensayopruebabg2.domain.executor.UIScheduler;
import com.example.ensayopruebabg2.domain.repository.Repository;

import javax.inject.Inject;

public class UseCaseFactory {

    private final Repository dataSource;
    private final UIScheduler uiScheduler;
    private final JobScheduler jobScheduler;
    private final LocalStorage localStorage;


    @Inject
    public UseCaseFactory(
            Repository dataSource,
            UIScheduler uiScheduler,
            JobScheduler jobScheduler,
            LocalStorage localStorage){
        this.dataSource = dataSource;
        this.uiScheduler = uiScheduler;
        this.jobScheduler = jobScheduler;
        this.localStorage = localStorage;
    }

    public SignIn signIn(){ return new SignIn(dataSource, uiScheduler, jobScheduler); }

    public GetComments getComments() {
        return new GetComments(dataSource, uiScheduler, jobScheduler);
    }

    public GetUser getUser() {
        return new GetUser(uiScheduler, jobScheduler, dataSource);
    }

//    public SignOut signOut(){ return new SignOut(dataSource, uiScheduler, jobScheduler); }

}
