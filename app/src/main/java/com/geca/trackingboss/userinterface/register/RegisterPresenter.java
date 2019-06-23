package com.geca.trackingboss.userinterface.register;

import android.util.Log;

import com.geca.trackingboss.TrackingBossApplication;
import com.geca.trackingboss.model.register.RegisterRequest;
import com.geca.trackingboss.network.UserAPI;
import com.geca.trackingboss.userinterface.login.LoginActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View registerView;
    private static UserAPI boss = TrackingBossApplication.RETROFIT.create(UserAPI.class);

    public RegisterPresenter(RegisterContract.View registerView) {
        this.registerView = registerView;
        registerView.setPresenter(this);
    }

    @Override
    public void registerBoss(RegisterRequest request) {
        if (registerView!=null){
            if(isValidRegisterRequest(request)){
                try {
                    boss.registerBossMethod(request).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                int code = response.code();
                                switch (code){
                                    case 201:
                                        registerView.openView(new LoginActivity());
                                        registerView.closeView();
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
                request.getAddress().trim().isEmpty() || request.getPhone().trim().isEmpty());
    }

}
