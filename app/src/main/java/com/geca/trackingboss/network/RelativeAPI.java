package com.geca.trackingboss.network;

import com.geca.trackingboss.model.relative.ListRelativeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface RelativeAPI {
    @GET("bosses/{bossId}/relatives")
    Call<ListRelativeResponse> getRelativesMethod(@Path("bossId") String bossId, @Header("isOnline") Integer isOnline, @Header("Authorization") String token);
}
