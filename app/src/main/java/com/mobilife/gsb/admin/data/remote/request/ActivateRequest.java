package com.mobilife.gsb.admin.data.remote.request;

import com.google.gson.annotations.SerializedName;
import com.mobilife.gsb.admin.data.model.MyLocation;

/**
 * Created by thawornlimwattanachai on 5/26/2017 AD.
 */
public class ActivateRequest {
    @SerializedName("user_id") protected String userId;
    @SerializedName("password") protected String password;
    @SerializedName("device_id") protected String deviceId;
    @SerializedName("location") protected MyLocation location;
    @SerializedName("sim_id") protected String simId;
    @SerializedName("agreement_reading_time") protected long readAgreementDuration;

    public ActivateRequest(String userId,
                           String password,
                           String deviceId,
                           MyLocation location,
                           String simId,
                           long readAgreementDuration) {
        this.userId = userId;
        this.password = password;
        this.deviceId = deviceId;
        this.location = location;
        this.simId = simId;
        this.readAgreementDuration = readAgreementDuration;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public MyLocation getLocation() {
        return location;
    }

    public void setLocation(MyLocation location) {
        this.location = location;
    }

    public String getSimId() {
        return simId;
    }

    public void setSimId(String simId) {
        this.simId = simId;
    }

    public long getReadAgreementDuration() {
        return readAgreementDuration;
    }

    public void setReadAgreementDuration(int readAgreementDuration) {
        this.readAgreementDuration = readAgreementDuration;
    }
}
