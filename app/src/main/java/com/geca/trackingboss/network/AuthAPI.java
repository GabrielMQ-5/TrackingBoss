package com.geca.trackingboss.network;

import com.geca.trackingboss.model.login.LoginRequest;
import com.geca.trackingboss.model.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthAPI {
    @POST("auth/login")
    Call<LoginResponse> loginAccountMethod(@Body LoginRequest loginRequest);

}
