package com.mobilife.gsb.admin.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thawornlimwattanachai on 1/26/2017 AD.
 */
public class ServerError {
    @SerializedName("code") String code="";
    @SerializedName("message") String message="";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
