package com.geca.trackingboss.userinterface.relative;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geca.trackingboss.R;
import com.geca.trackingboss.model.relative.ListRelativeResponse;
import com.geca.trackingboss.userinterface.BaseView;
import com.geca.trackingboss.utility.SharedPreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewRelativeActivity extends AppCompatActivity implements ViewRelativeContract.View {
    ViewRelativeContract.Presenter presenter;
    ListRelativeResponse.RelativeResponse relative;

    @BindView(R.id.trackButton)
    Button trackButton;
    @BindView(R.id.usernameTextView)
    TextView usernameTextView;
    @BindView(R.id.firstnameTextView)
    TextView firstnameTextView;
    @BindView(R.id.lastnameTextView)
    TextView lastnameTextView;
    @BindView(R.id.dniTextView)
    TextView dniTextView;
    @BindView(R.id.phoneTextView)
    TextView phoneTextView;
    @BindView(R.id.addressTextView)
    TextView addressTextView;

    Bundle relativeBundle;
    SharedPreferenceManager sharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_relative);
        ButterKnife.bind(this);

        presenter = new ViewRelativePresenter(this);
        sharedPreferenceManager = new SharedPreferenceManager(this);

        Bundle extras = this.getIntent().getExtras();
        if (extras == null) closeView();

        relative = new ListRelativeResponse.RelativeResponse(extras);
        if (relative.isOnline()) trackButton.setVisibility(View.VISIBLE);
        else trackButton.setVisibility(View.INVISIBLE);
        usernameTextView.setText(relative.getUser().getUsername());
        firstnameTextView.setText(relative.getUser().getFirstname());
        lastnameTextView.setText(relative.getUser().getLastname());
        dniTextView.setText(relative.getUser().getDni());
        phoneTextView.setText(relative.getUser().getPhone());
        addressTextView.setText(relative.getUser().getAddress());
    }

    @Override
    public void setPresenter(ViewRelativeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void openView(BaseView view) {
        if (relativeBundle != null)
            startActivity(new Intent(this, view.getClass()).putExtras(relativeBundle));
        else
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

    @OnClick(R.id.editButton)
    public void editRelative() {
        relativeBundle = new Bundle();
        relativeBundle.putAll(relative.getUser().toBundle());
        openView(new AddEditRelativeActivity());
    }

    @OnClick(R.id.trackButton)
    public void trackRelative() {
        relativeBundle = new Bundle();
        relativeBundle.putInt("id", relative.getId());
        relativeBundle.putString("fullName", relative.getUser().getFirstname() + " " + relative.getUser().getLastname());
        relativeBundle.putBundle("user", relative.getUser().toBundle());
        openView(new TrackRelativeActivity());
    }

    @OnClick(R.id.deleteButton)
    public void deleteRelative() {
        presenter.deleteRelative(relative.getId(), sharedPreferenceManager.getUser().getAccessToken());
    }
}
