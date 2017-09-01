package com.mobilife.gsb.admin.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JitsakP on 03-Feb-17.
 */

public class User {

    @SerializedName("login") private String login;
    @SerializedName("id") private String id;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
