package com.mobilife.gsb.admin.data.remote.response;

import com.google.gson.annotations.SerializedName;
import com.mobilife.gsb.admin.data.model.ServerError;

/**
 * Created by thawornlimwattanachai on 1/30/2017 AD.
 */
public class ErrorResponse {
    @SerializedName("error")
    ServerError error;

    public ServerError getError() {
        return error;
    }

    public void setError(ServerError error) {
        this.error = error;
    }
}
