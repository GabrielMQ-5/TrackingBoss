package com.geca.trackingboss.userinterface.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.geca.trackingboss.R;
import com.geca.trackingboss.model.register.RegisterRequest;
import com.geca.trackingboss.userinterface.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    RegisterContract.Presenter presenter;

    @BindView(R.id.emailEditText)
    EditText emailEditText;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;
    @BindView(R.id.confirmPasswordEditText)
    EditText confirmPasswordEditText;
    @BindView(R.id.firstNamedEditText)
    EditText firstNamedEditText;
    @BindView(R.id.lastNamedEditText)
    EditText lastNamedEditText;
    @BindView(R.id.dnidEditText)
    EditText dnidEditText;
    @BindView(R.id.phoneEditText)
    EditText phoneEditText;
    @BindView(R.id.addressEditText)
    EditText addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter = new RegisterPresenter(this);
    }

    @OnClick(R.id.registerButton)
    void registerBoss() {
        RegisterRequest request = new RegisterRequest(null, emailEditText.getText().toString(), passwordEditText.getText().toString(), confirmPasswordEditText.getText().toString(),
                firstNamedEditText.getText().toString(), lastNamedEditText.getText().toString(), dnidEditText.getText().toString(),
                phoneEditText.getText().toString(), addressEditText.getText().toString(), true);
        presenter.registerBoss(request);
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
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
