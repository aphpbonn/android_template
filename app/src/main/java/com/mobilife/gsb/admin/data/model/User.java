package com.mobilife.gsb.admin.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JitsakP on 03-Feb-17.
 */

public class User implements Parcelable {

    @SerializedName("user_id") private String userID;
    @SerializedName("name") private String name;
    @SerializedName("password") private String password="";
    @SerializedName("access_token") private String accessToken="";
    @SerializedName("refresh_token") private String refreshToken="";
    @SerializedName("branch_name") private String branchName;
    @SerializedName("is_activate") private boolean isActivate;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public boolean isActivate() {
        return isActivate;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }

    public User() {

    }

    public User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userID);
        dest.writeString(this.name);
        dest.writeString(this.password);
        dest.writeString(this.accessToken);
        dest.writeString(this.refreshToken);
        dest.writeString(this.branchName);
        dest.writeByte(this.isActivate ? (byte) 1 : (byte) 0);
    }

    protected User(Parcel in) {
        this.userID = in.readString();
        this.name = in.readString();
        this.password = in.readString();
        this.accessToken = in.readString();
        this.refreshToken = in.readString();
        this.branchName = in.readString();
        this.isActivate = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
