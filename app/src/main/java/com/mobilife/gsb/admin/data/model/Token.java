package com.mobilife.gsb.admin.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JitsakP on 08-Feb-17.
 */

public class Token {
    @SerializedName("access_token") protected String accessToken;
    @SerializedName("refresh_token") protected String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
