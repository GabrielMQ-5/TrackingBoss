package com.geca.trackingboss.userinterface.relative;

import android.util.Log;

import com.geca.trackingboss.TrackingBossApplication;
import com.geca.trackingboss.model.register.RegisterRequest;
import com.geca.trackingboss.network.UserAPI;
import com.geca.trackingboss.userinterface.main.MainActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditRelativePresenter implements AddEditRelativeContract.Presenter {
    private AddEditRelativeContract.View addEditRelativeView;
    private static UserAPI user = TrackingBossApplication.RETROFIT.create(UserAPI.class);

    public AddEditRelativePresenter(AddEditRelativeContract.View addEditRelativeView) {
        this.addEditRelativeView = addEditRelativeView;
        user = TrackingBossApplication.RETROFIT.create(UserAPI.class);
    }

    @Override
    public void saveRelative(RegisterRequest request, String bossId, String token) {
        if (addEditRelativeView!=null){
            if(isValidRegisterRequest(request)){
                try {
                    user.updateUser(bossId, request).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                int code = response.code();
                                switch (code){
                                    case 201:
                                        addEditRelativeView.firebaseRegister();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("ERROR", t.getMessage());
                        }
                    });
                }catch (Exception ex){
                    Log.d("ERROR", ex.getMessage());
                }
            }
        }
    }

    private boolean isValidRegisterRequest(RegisterRequest request) {
        return !(request.getUsername().trim().isEmpty() || request.getPassword().trim().isEmpty() || request.getConfirmPassword().trim().isEmpty() ||
                request.getFirstname().trim().isEmpty() || request.getLastname().trim().isEmpty() || request.getDni().trim().isEmpty() ||
                request.getAddress().trim().isEmpty() || request.getPhone().trim().isEmpty() || request.getPassword().length() < 6);
    }
}
