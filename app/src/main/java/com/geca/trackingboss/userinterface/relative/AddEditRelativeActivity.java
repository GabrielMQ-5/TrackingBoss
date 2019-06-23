package com.geca.trackingboss.userinterface.relative;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.geca.trackingboss.R;
import com.geca.trackingboss.model.register.RegisterRequest;
import com.geca.trackingboss.model.relative.ListRelativeResponse;
import com.geca.trackingboss.userinterface.BaseView;
import com.geca.trackingboss.userinterface.main.MainActivity;
import com.geca.trackingboss.utility.SharedPreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddEditRelativeActivity extends AppCompatActivity implements AddEditRelativeContract.View {
    AddEditRelativeContract.Presenter presenter;
    RegisterRequest request;

    @BindView(R.id.usernameTextView)
    EditText usernameTextView;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;
    @BindView(R.id.confirmPasswordEditText)
    EditText confirmPasswordEditText;
    @BindView(R.id.firstNameEditText)
    EditText firstNameEditText;
    @BindView(R.id.lastNameEditText)
    EditText lastNameEditText;
    @BindView(R.id.dnidEditText)
    EditText dnidEditText;
    @BindView(R.id.phoneEditText)
    EditText phoneEditText;
    @BindView(R.id.addressEditText)
    EditText addressEditText;

    SharedPreferenceManager sharedPreferenceManager;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_relative);
        ButterKnife.bind(this);

        presenter = new AddEditRelativePresenter(this);
        sharedPreferenceManager = new SharedPreferenceManager(this);

        request = new RegisterRequest();
        request.setId(null);
        request.setRole(new RegisterRequest.Role(3));

        ListRelativeResponse.RelativeResponse.User user = null;
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            user = new ListRelativeResponse.RelativeResponse.User(extras);
            usernameTextView.setText(user.getUsername());
            firstNameEditText.setText(user.getFirstname());
            lastNameEditText.setText(user.getLastname());
            dnidEditText.setText(user.getDni());
            phoneEditText.setText(user.getPhone());
            addressEditText.setText(user.getAddress());

            request.setId(user.getId());
        }

        firebaseAuth = FirebaseAuth.getInstance();
    }


    @OnClick(R.id.saveButton)
    void saveRelative() {
        request.setUsername(usernameTextView.getText().toString());
        request.setPassword(passwordEditText.getText().toString());
        request.setConfirmPassword(confirmPasswordEditText.getText().toString());
        request.setFirstname(firstNameEditText.getText().toString());
        request.setLastname(lastNameEditText.getText().toString());
        request.setDni(dnidEditText.getText().toString());
        request.setPhone(phoneEditText.getText().toString());
        request.setAddress(addressEditText.getText().toString());
        request.setType(false);
        presenter.saveRelative(request, sharedPreferenceManager.getUser().getBossId(), sharedPreferenceManager.getUser().getAccessToken());
    }

    @Override
    public void firebaseRegister() {
        firebaseAuth.createUserWithEmailAndPassword(request.getUsername(), request.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String userId = firebaseAuth.getCurrentUser().getUid();
                DatabaseReference currentUserDB = FirebaseDatabase.getInstance().getReference().child("Users").child("Relative").child(userId);
                currentUserDB.setValue(true);

                openView(new MainActivity());
                closeView();
            }
        });
    }

    @Override
    public void setPresenter(AddEditRelativeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void openView(BaseView view) {
        startActivity(new Intent(this, view.getClass()));
    }

    @OnClick(R.id.backButton)
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
