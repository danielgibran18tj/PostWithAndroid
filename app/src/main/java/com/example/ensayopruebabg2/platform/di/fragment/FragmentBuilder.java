package com.example.ensayopruebabg2.platform.di.fragment;

import com.example.ensayopruebabg2.platform.views.fragments.AddPostDialogFragment;
import com.example.ensayopruebabg2.platform.views.fragments.DetailsPostFragment;
import com.example.ensayopruebabg2.platform.views.fragments.SelectPostsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = {SelectPostModule.class})
    abstract SelectPostsFragment bindSelectPostsFragment();

    @ContributesAndroidInjector(modules = {DetailsPostModule.class})
    abstract DetailsPostFragment bindDetailsPostFragment();

    @ContributesAndroidInjector(modules = {AddPostDialogModule.class})
    abstract AddPostDialogFragment bindAddPostDialogFragment();

}
