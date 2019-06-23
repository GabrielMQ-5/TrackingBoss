package com.geca.trackingboss.userinterface.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.geca.trackingboss.R;
import com.geca.trackingboss.model.login.LoginRequest;
import com.geca.trackingboss.model.login.LoginResponse;
import com.geca.trackingboss.userinterface.BaseView;
import com.geca.trackingboss.userinterface.main.MainActivity;
import com.geca.trackingboss.userinterface.register.RegisterActivity;
import com.geca.trackingboss.utility.Session;
import com.geca.trackingboss.utility.SharedPreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    LoginContract.Presenter presenter;

    SharedPreferenceManager sharedPreferenceManager;

    @BindView(R.id.emailEditText)
    EditText emailEditText;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
        sharedPreferenceManager = new SharedPreferenceManager(this);

    }

    @OnClick(R.id.loginButton)
    void logIn(){
        LoginRequest request = new LoginRequest(emailEditText.getText().toString(), passwordEditText.getText().toString());
        presenter.login(request);
    }

    @OnClick(R.id.registerButton)
    void register(){
        openView(new RegisterActivity());
        closeView();
    }

    @Override
    public void setSharedPreferences(LoginResponse response) {
        if (response.getUser().getBoss() == null) {
            showErrorBadRequest();
            return;
        }
        Session session = new Session();
        session.setAccessToken(response.getAccessToken());
        session.setUserId(String.valueOf(response.getUser().getId()));
        session.setBossId(String.valueOf(response.getUser().getBoss().getId()));
        sharedPreferenceManager.saveUser(session);

        openView(new MainActivity());
        closeView();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void openView(BaseView view) {
        startActivity(new Intent(this, view.getClass()));
    }

    @Override
    public void closeView() {
        finish();
    }

    @Override
    public void showErrorBadRequest() {

    }

    @Override
    public void showErrorInternalError() {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void showSuccessDialog() {

    }


}
