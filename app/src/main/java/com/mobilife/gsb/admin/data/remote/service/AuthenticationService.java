package com.mobilife.gsb.admin.data.remote.service;

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
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by JitsakP on 03-Feb-17.
 */

public interface AuthenticationService {
    @POST(EnvConstant.API_VERSION + "/authentication/login")
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST(EnvConstant.API_VERSION + "/authentication/refresh-token")
    Observable<RefreshTokenResponse> refreshToken(@Body RefreshTokenRequest refreshTokenRequest);

    @POST(EnvConstant.API_VERSION + "/authentication/logout")
    Observable<Void> logout(@Body Map logoutRequest);

    @POST(EnvConstant.API_VERSION + "/authentication/refresh-token")
    Call<RefreshTokenResponse> refreshTokenSync(@Body RefreshTokenRequest refreshTokenRequest);

    @POST(EnvConstant.API_VERSION + "/authentication/verify-credential")
    Observable<VerifyCredentialResponse> verifyCredential(
            @Body VerifyCredentialRequest verifyCredentialRequest);

    @POST(EnvConstant.API_VERSION + "/activation/activate")
    Observable<Void> activateDevice(@Body ActivateRequest activateRequest);

    @POST(EnvConstant.API_VERSION + "/activation/login")
    Observable<LoginResponse> activationLogin(@Body LoginRequest loginRequest);

    @POST(EnvConstant.API_VERSION + "/activation/logout")
    Observable<Void> activationLogout(@Body Map logoutRequest);

    @POST(EnvConstant.API_VERSION + "/activation/deactivate")
    Observable<Void> deactivateDevice(@Body ActivateRequest activateRequest);
}
