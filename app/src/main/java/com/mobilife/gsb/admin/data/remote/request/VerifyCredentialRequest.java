package com.mobilife.gsb.admin.data.remote.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JitsakP on 03-Feb-17.
 */

public class VerifyCredentialRequest {
    @SerializedName("password") protected String password;
    @SerializedName("device_id") protected String deviceId;

    public VerifyCredentialRequest(String password, String deviceId) {
        this.password = password;
        this.deviceId = deviceId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
