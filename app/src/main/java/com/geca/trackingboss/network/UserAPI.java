package com.geca.trackingboss.network;

import com.geca.trackingboss.model.register.RegisterRequest;
import com.geca.trackingboss.model.relative.ListRelativeResponse;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserAPI {
    @POST("users")
    Call<ResponseBody> registerBossMethod(@Body RegisterRequest registerRequest);

    @POST("users")
    Call<ResponseBody> updateUser(@Header("bossid") String bossid, @Body RegisterRequest request);

    @DELETE("users/{userId}")
    Call<ResponseBody> deleteUser(@Path("bossId") int bossId, @Header("Authorization") String token);
}
