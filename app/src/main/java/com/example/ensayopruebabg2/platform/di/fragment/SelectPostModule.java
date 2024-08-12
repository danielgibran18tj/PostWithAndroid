package com.example.ensayopruebabg2.platform.di.fragment;

import com.example.ensayopruebabg2.platform.di.navigation.Navigator;
import com.example.ensayopruebabg2.platform.views.fragments.SelectPostsFragment;
import com.example.ensayopruebabg2.presentation.presenter.SelectPostsPresenter;
import com.example.ensayopruebabg2.presentation.presenter.SelectPostsPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
@SuppressWarnings("WeakerAccess")
public class SelectPostModule {
    @Provides
    SelectPostsPresenter providePresenter(SelectPostsFragment fragment, Navigator navigator ) {
        SelectPostsPresenter presenter = new SelectPostsPresenterImpl(navigator);
        presenter.setView(fragment);
        return presenter;
    }

}
