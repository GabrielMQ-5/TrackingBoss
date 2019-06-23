package com.geca.trackingboss.userinterface.relative;

import android.util.Log;

import com.geca.trackingboss.TrackingBossApplication;
import com.geca.trackingboss.network.UserAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewRelativePresenter implements ViewRelativeContract.Presenter {
    private ViewRelativeContract.View viewRelativeView;
    private static UserAPI user = TrackingBossApplication.RETROFIT.create(UserAPI.class);

    public ViewRelativePresenter(ViewRelativeContract.View viewRelativeView) {
        this.viewRelativeView = viewRelativeView;
        user = TrackingBossApplication.RETROFIT.create(UserAPI.class);
    }

    @Override
    public void deleteRelative(int relativeId, String token) {
        if (viewRelativeView != null) {
            try {
                user.deleteUser(relativeId, "Bearer " + token).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            int code = response.code();
                            switch (code) {
                                case 200:
                                    viewRelativeView.showSuccessDialog();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        viewRelativeView.showErrorInternalError();
                        Log.d("FAILURE", t.getMessage());
                    }
                });
            } catch (Exception ex) {
                viewRelativeView.showErrorBadRequest();
                Log.d("ERROR", ex.getMessage());
            }
        }
    }
}
