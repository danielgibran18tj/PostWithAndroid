package com.example.ensayopruebabg2.platform.di.fragment;

import com.example.ensayopruebabg2.domain.interactor.UseCaseFactory;
import com.example.ensayopruebabg2.platform.di.navigation.Navigator;
import com.example.ensayopruebabg2.platform.views.fragments.DetailsPostFragment;
import com.example.ensayopruebabg2.presentation.presenter.DetailsPostPresenter;
import com.example.ensayopruebabg2.presentation.presenter.DetailsPostPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
@SuppressWarnings("WeakerAccess")
public class DetailsPostModule {

    @Provides
    DetailsPostPresenter providePresenter(DetailsPostFragment fragment, Navigator navigator, UseCaseFactory useCaseFactory){
        DetailsPostPresenter presenter = new DetailsPostPresenterImpl(useCaseFactory, navigator);
        presenter.setView(fragment);
        return presenter;
    }
}
