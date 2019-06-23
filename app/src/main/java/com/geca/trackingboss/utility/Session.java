package com.geca.trackingboss.utility;

public class Session {
    private String accessToken;
    private String userId;
    private String bossId;

    public Session() {
    }

    public Session(String accessToken, String userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Session setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Session setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getBossId() {
        return bossId;
    }

    public Session setBossId(String bossId) {
        this.bossId = bossId;
        return this;
    }
}
