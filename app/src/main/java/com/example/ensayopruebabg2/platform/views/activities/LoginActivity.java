package com.example.ensayopruebabg2.platform.views.activities;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
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
        etvEpr = findViewById(R.id.etLoginUsername);
        etvPassword = findViewById(R.id.etLoginPassword);
        etvShowPass = findViewById(R.id.etLoginShowPass);
    }

    private void initViewAction() {
        btnLogin.setEnabled(true);
        etvShowPass.setText("Ver Contra");
        btnLogin.setOnClickListener(v -> {
            presenter.signIn();
        });
        etvShowPass.setOnClickListener(v -> {
            showPassword = !showPassword;
            if (showPassword) {
                etvPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                etvShowPass.setText("Ocultar Contra");
            } else {
                etvPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                etvShowPass.setText("Ver Contra");
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
    }
}