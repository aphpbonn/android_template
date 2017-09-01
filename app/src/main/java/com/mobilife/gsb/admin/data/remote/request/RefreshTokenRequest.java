package com.mobilife.gsb.admin.data.remote.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JitsakP on 06-Feb-17.
 */

public class RefreshTokenRequest {
    @SerializedName("refresh_token") protected String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
