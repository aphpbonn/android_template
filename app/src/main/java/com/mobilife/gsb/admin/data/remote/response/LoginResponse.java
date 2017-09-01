package com.mobilife.gsb.admin.data.remote.response;

import com.google.gson.annotations.SerializedName;
import com.mobilife.gsb.admin.data.model.Token;

/**
 * Created by JitsakP on 03-Feb-17.
 */

public class LoginResponse {
    @SerializedName("token") protected Token token;
    @SerializedName("user_name") protected String userName;
    @SerializedName("branch_name") protected String branchName;
    @SerializedName("is_activate") protected boolean isActivate;

    public boolean isActivate() {
        return isActivate;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

}
