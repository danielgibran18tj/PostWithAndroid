package com.example.ensayopruebabg2.platform.di.fragment;

import com.example.ensayopruebabg2.data.source.local.temp.LocalStorage;
import com.example.ensayopruebabg2.platform.di.navigation.Navigator;
import com.example.ensayopruebabg2.platform.views.fragments.AddPostDialogFragment;
import com.example.ensayopruebabg2.presentation.presenter.SecondPresenter;
import com.example.ensayopruebabg2.presentation.presenter.SecondPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
@SuppressWarnings("WeakerAccess")
public class AddPostDialogModule {

    @Provides
    SecondPresenter secondPresenter(AddPostDialogFragment activity, LocalStorage localStorage, Navigator navigator){
        SecondPresenter presenter = new SecondPresenterImpl(localStorage, navigator);
        presenter.setView(activity);
        return presenter;
    }

}
