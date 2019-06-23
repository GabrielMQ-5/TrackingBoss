package com.geca.trackingboss.userinterface.main;

import android.util.Log;

import com.geca.trackingboss.TrackingBossApplication;
import com.geca.trackingboss.model.relative.ListRelativeResponse;
import com.geca.trackingboss.network.RelativeAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mainView;
    private static RelativeAPI relative = TrackingBossApplication.RETROFIT.create(RelativeAPI.class);

    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
        relative = TrackingBossApplication.RETROFIT.create(RelativeAPI.class);
    }
    
    @Override
    public void listRelatives(String bossId, Integer isOnline, String token) {
        if (mainView != null) {
            try {
                relative.getRelativesMethod(bossId, isOnline, "Bearer " + token).enqueue(new Callback<ListRelativeResponse>() {
                    @Override
                    public void onResponse(Call<ListRelativeResponse> call, Response<ListRelativeResponse> response) {
                        if (response.isSuccessful()) {
                                int code = response.code();
                            switch (code) {
                                case 200:
                                    ListRelativeResponse result = response.body();
                                    mainView.setRelatives(result);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ListRelativeResponse> call, Throwable t) {
                        mainView.showErrorInternalError();
                        Log.d("FAILURE", t.getMessage());
                    }
                });

            } catch (Exception ex) {
                mainView.showErrorBadRequest();
                Log.d("ERROR", ex.getMessage());
            }
        }
    }
}
