package com.geca.trackingboss.userinterface.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;

import com.geca.trackingboss.R;
import com.geca.trackingboss.model.relative.ListRelativeResponse;
import com.geca.trackingboss.userinterface.BaseView;
import com.geca.trackingboss.userinterface.relative.AddEditRelativeActivity;
import com.geca.trackingboss.userinterface.relative.ViewRelativeActivity;
import com.geca.trackingboss.utility.SharedPreferenceManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View, RelativeAdapter.OnRelativeClick {
    MainContract.Presenter presenter;
    List<ListRelativeResponse.RelativeResponse> relativeResponseList;

    @BindView(R.id.onlineSwitch)
    SwitchCompat onlineSwitch;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RelativeAdapter relativeAdapter;
    RecyclerView.LayoutManager layoutManager;

    Bundle relativeBundle = null;

    SharedPreferenceManager sharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);
        sharedPreferenceManager = new SharedPreferenceManager(this);

        relativeAdapter = new RelativeAdapter(this);
        layoutManager = new GridLayoutManager(this, 1);

        recyclerView.setAdapter(relativeAdapter);
        recyclerView.setLayoutManager(layoutManager);

        presenter.listRelatives(sharedPreferenceManager.getUser().getBossId(), 1, sharedPreferenceManager.getUser().getAccessToken());
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getRelatives();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void openView(BaseView view) {
        if (relativeBundle != null)
            startActivity(new Intent(this.getBaseContext(), view.getClass()).putExtras(relativeBundle));
        else
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

    @OnClick(R.id.onlineSwitch)
    @Override
    public void getRelatives() {
        if (onlineSwitch.isChecked())
            presenter.listRelatives(sharedPreferenceManager.getUser().getBossId(), 1, sharedPreferenceManager.getUser().getAccessToken());
        else
            presenter.listRelatives(sharedPreferenceManager.getUser().getBossId(), 0, sharedPreferenceManager.getUser().getAccessToken());
    }

    @OnClick(R.id.allButton)
    public void getAllRelatives() {
        presenter.listRelatives(sharedPreferenceManager.getUser().getBossId(), null, sharedPreferenceManager.getUser().getAccessToken());
    }

    @Override
    public void setRelatives(ListRelativeResponse relatives) {
        relativeAdapter.setRelativeResponseList(relatives.getData());
        relativeResponseList = relatives.getData();
    }

    @Override
    public void OnClick(ListRelativeResponse.RelativeResponse relativeResponse) {
        relativeBundle = new Bundle();
        relativeBundle.putInt("id",relativeResponse.getId());
        relativeBundle.putBoolean("isOnline",relativeResponse.isOnline());
        relativeBundle.putBundle("user",relativeResponse.getUser().toBundle());
        relativeBundle.putBundle("boss",relativeResponse.getBoss().toBundle());
        this.openView(new ViewRelativeActivity());
    }

    @OnClick(R.id.addButton)
    public void addRelative() {
        relativeBundle = null;
        openView(new AddEditRelativeActivity());
    }
}
