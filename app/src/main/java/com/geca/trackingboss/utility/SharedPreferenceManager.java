package com.geca.trackingboss.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SharedPreferenceManager {
    private SharedPreferences preferences;
    private static final String TAG = "TrackingBoss";
    private static final String TOKEN = "token";
    private static final String USER_ID = "userId";
    private static final String BOSS_ID = "bossId";
    private static final String USER_SESSION = "userSession";

    public SharedPreferenceManager(Context context) {
        preferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    public void saveUser(Session session) {
        JSONObject userJson = new JSONObject();
        try {
            userJson.put(TOKEN, session.getAccessToken());
            userJson.put(USER_ID,session.getUserId());
            userJson.put(BOSS_ID,session.getBossId());
        } catch (JSONException ex) {
            Log.d(TAG, ex.getMessage());
        }
        preferences.edit().putString(USER_SESSION, userJson.toString()).commit();
    }

    public void deleteUser() {
        JSONObject userJson = new JSONObject();
        try {
            userJson.put(TOKEN, null);
            userJson.put(USER_ID,null);
            userJson.put(BOSS_ID,null);
        } catch (JSONException ex) {
            Log.d(TAG, ex.getMessage());
        }
        preferences.edit().putString(USER_SESSION, userJson.toString()).commit();
    }

    public Session getUser() {
        Session user = new Session();
        JSONObject userJson;
        try {
            userJson = new JSONObject(preferences.getString(USER_SESSION, ""));
            if (!userJson.isNull(TOKEN)) user.setAccessToken(userJson.getString(TOKEN));
            if (!userJson.isNull(USER_ID)) user.setUserId(userJson.getString(USER_ID));
            if (!userJson.isNull(BOSS_ID)) user.setBossId(userJson.getString(BOSS_ID));
        } catch (JSONException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return user;
    }

}