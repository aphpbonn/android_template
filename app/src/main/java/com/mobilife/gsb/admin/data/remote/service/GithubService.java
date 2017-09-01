package com.mobilife.gsb.admin.data.remote.service;

import com.mobilife.gsb.admin.data.model.User;
import com.mobilife.gsb.admin.data.remote.request.ActivateRequest;
import com.mobilife.gsb.admin.data.remote.request.LoginRequest;
import com.mobilife.gsb.admin.data.remote.request.RefreshTokenRequest;
import com.mobilife.gsb.admin.data.remote.request.VerifyCredentialRequest;
import com.mobilife.gsb.admin.data.remote.response.LoginResponse;
import com.mobilife.gsb.admin.data.remote.response.RefreshTokenResponse;
import com.mobilife.gsb.admin.data.remote.response.VerifyCredentialResponse;

import java.util.Map;

import data.configuration.EnvConstant;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by JitsakP on 03-Feb-17.
 */

public interface GithubService {

    @GET("/users/{id}")
    Observable<User> getUser(@Path("id") String userId);
}
