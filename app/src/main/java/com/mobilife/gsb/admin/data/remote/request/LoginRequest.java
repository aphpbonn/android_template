package com.mobilife.gsb.admin.data.remote.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JitsakP on 03-Feb-17.
 */

public class LoginRequest {
    @SerializedName("user_id") protected String userId;
    @SerializedName("password") protected String password;
    @SerializedName("device_id") protected String deviceId;

    public LoginRequest(String userId, String password, String deviceId) {
        this.userId = userId;
        this.password = password;
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
