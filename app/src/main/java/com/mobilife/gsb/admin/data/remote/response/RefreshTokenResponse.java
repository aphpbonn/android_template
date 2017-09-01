package com.mobilife.gsb.admin.data.remote.response;

import com.google.gson.annotations.SerializedName;
import com.mobilife.gsb.admin.data.model.Token;


/**
 * Created by JitsakP on 06-Feb-17.
 */

public class RefreshTokenResponse {
    @SerializedName("token") protected Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
