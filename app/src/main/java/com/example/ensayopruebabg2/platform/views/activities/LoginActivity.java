package com.example.ensayopruebabg2.platform.views.activities;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.ensayopruebabg2.R;
import com.example.ensayopruebabg2.platform.views.base.BaseActivity;
import com.example.ensayopruebabg2.presentation.presenter.LoginPresenter;
import com.example.ensayopruebabg2.presentation.view.LoginView;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginPresenter presenter;
    private Button btnLogin;
    private ProgressBar progressBar;

    private EditText etvEpr;
    private EditText etvPassword;
    private AppCompatTextView etvShowPass;
    boolean showPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initViewAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        etvEpr.setText("maria@mail.com");
        etvPassword.setText("12345");
    }

    private void initView() {
        btnLogin = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        etvEpr = findViewById(R.id.etLoginUsername);
        etvPassword = findViewById(R.id.etLoginPassword);
        etvShowPass = findViewById(R.id.etLoginShowPass);
    }

    private void initViewAction() {
        btnLogin.setEnabled(true);
        etvShowPass.setText("Show PassWord");
        btnLogin.setOnClickListener(v -> {
            presenter.signIn();
            btnLogin.setText(""); // Oculta el texto del botón
            progressBar.setVisibility(View.VISIBLE);
        });
        etvShowPass.setOnClickListener(v -> {
            showPassword = !showPassword;
            if (showPassword) {
                etvPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                etvShowPass.setText("Hide Password");
            } else {
                etvPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                etvShowPass.setText("Show PassWord");
            }
        });
    }

    @Override
    public String getEprUI() {
        return etvEpr.getText().toString();
    }

    @Override
    public String getPasswordUI() {
        return etvPassword.getText().toString();
    }

    @Override
    public void showFail() {
        Toast.makeText(getApplicationContext(), "Credenciales incorrectas. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enableButton(boolean enable) {
        btnLogin.setEnabled(enable);
        if (enable){
            progressBar.setVisibility(View.GONE);
            btnLogin.setText("S I G N  I N");
            btnLogin.setEnabled(true);
        }
    }
}