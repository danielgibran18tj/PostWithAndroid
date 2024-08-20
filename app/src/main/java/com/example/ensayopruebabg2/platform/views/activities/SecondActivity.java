package com.example.ensayopruebabg2.platform.views.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ensayopruebabg2.R;
import com.example.ensayopruebabg2.platform.views.base.BaseActivity;
import com.example.ensayopruebabg2.platform.views.fragments.SelectPostsFragment;
import com.example.ensayopruebabg2.presentation.presenter.SecondPresenter;
import com.example.ensayopruebabg2.presentation.view.SecondView;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;

public class SecondActivity extends BaseActivity implements SecondView {

    @Inject
    SecondPresenter presenter;
    private ImageView ivBack;
    private ImageView ivLogout;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ivBack = findViewById(R.id.ivBack);
        ivLogout = findViewById(R.id.ivLogout);
        initViewAction();

    }

    private void initViewAction() {
        String postsJson = getIntent().getStringExtra("posts");
        presenter.viewPosts(postsJson);
        ivBack.setOnClickListener(v -> onBackPressed());
        ivLogout.setOnClickListener(v -> {
            presenter.toLoguot();
            onBackPressed();
        });

        // Configuración del listener para cambios en la pila de retroceso
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                // Actualiza la visibilidad de los botones según el fragmento visible
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutMain);
                if (currentFragment instanceof SelectPostsFragment) {
                    ivBack.setVisibility(View.INVISIBLE);
                    ivLogout.setVisibility(View.VISIBLE);
                } else {
                    ivBack.setVisibility(View.VISIBLE);
                    ivLogout.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 1) {
            manager.popBackStack();
        } else {
            super.onBackPressed(); // Retroceso predeterminado (cerrar actividad)
            presenter.toLogin();
        }
    }

    @Override
    public void navigateToFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
//        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); //elimina todos los fragmentos del back stack antes de realizar la nueva transacción
//        if (fragment instanceof SelectPostsFragment){
//            ivBack.setVisibility(View.INVISIBLE);
//            ivLogout.setVisibility(View.VISIBLE);
//        }else {
//            ivBack.setVisibility(View.VISIBLE);
//            ivLogout.setVisibility(View.INVISIBLE);
//        }
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayoutMain,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

