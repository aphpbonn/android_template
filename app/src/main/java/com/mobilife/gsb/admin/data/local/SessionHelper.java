package com.mobilife.gsb.admin.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobilife.gsb.admin.data.model.Session;
import com.mobilife.gsb.admin.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionHelper {

    public static final String SESSION_FILE_NAME = "app_pref_file";

    private static final String SESSION_KEY_ACCESS_TOKEN = "SESSION_KEY_ACCESS_TOKEN";
    private static final String SESSION_KEY_SIGNED_IN = "SESSION_KEY_SIGNED_IN";

    private final SharedPreferences mPref;
    private final Gson mGson;

    @Inject
    public SessionHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(SESSION_FILE_NAME, Context.MODE_PRIVATE);
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz")
                .create();
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public void putAccessToken(String accessToken) {
        mPref.edit().putString(SESSION_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Nullable
    public String getAccessToken() {
        return mPref.getString(SESSION_KEY_ACCESS_TOKEN, null);
    }

    public void putSignedInSession(Session session) {
        mPref.edit().putString(SESSION_KEY_SIGNED_IN, mGson.toJson(session)).apply();
    }

    @Nullable
    public Session getSignedInSession() {
        String sessionJson = mPref.getString(SESSION_KEY_SIGNED_IN, null);
        if (sessionJson == null) return null;
        return mGson.fromJson(sessionJson, Session.class);
    }

    public void clearSignedIn() {
        SharedPreferences.Editor editor = mPref.edit();
        editor.remove(SESSION_KEY_ACCESS_TOKEN);
        editor.remove(SESSION_KEY_SIGNED_IN);
        editor.apply();
    }
}
