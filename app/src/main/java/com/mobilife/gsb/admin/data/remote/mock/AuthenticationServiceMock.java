package com.mobilife.gsb.admin.data.remote.mock;

import com.mobilife.gsb.admin.data.model.Token;
import com.mobilife.gsb.admin.data.remote.request.ActivateRequest;
import com.mobilife.gsb.admin.data.remote.request.LoginRequest;
import com.mobilife.gsb.admin.data.remote.request.RefreshTokenRequest;
import com.mobilife.gsb.admin.data.remote.request.VerifyCredentialRequest;
import com.mobilife.gsb.admin.data.remote.response.LoginResponse;
import com.mobilife.gsb.admin.data.remote.response.RefreshTokenResponse;
import com.mobilife.gsb.admin.data.remote.response.VerifyCredentialResponse;
import com.mobilife.gsb.admin.data.remote.service.AuthenticationService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.http.Body;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by JitsakP on 06-Feb-17.
 */

public class AuthenticationServiceMock extends BaseServiceMock implements AuthenticationService {
    @Override
    public Observable<LoginResponse> login(@Body final LoginRequest loginRequest) {
        return Observable
                .create(new Observable.OnSubscribe<LoginResponse>() {
                    @Override
                    public void call(Subscriber<? super LoginResponse> subscriber) {
                        if (loginRequest.getUserId().startsWith("9")) {
                            subscriber.onError(error(400, "E000001", "Invalid user id[user 9xxx will error]"));
                            return;
                        } else if (loginRequest.getUserId().startsWith("8")) {
                            subscriber.onError(error(400, "E020006", "ActivationComplete"));
                            return;
                        }

                        LoginResponse response = new LoginResponse();

                        Token token = new Token();
                        token.setAccessToken("AccessToken");
                        token.setRefreshToken("RefreshToken");
                        response.setToken(token);

                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    }
                }).delay(1, TimeUnit.SECONDS);
    }

    @Override
    public Observable<RefreshTokenResponse> refreshToken(@Body RefreshTokenRequest refreshTokenRequest) {
        return Observable
                .create(new Observable.OnSubscribe<RefreshTokenResponse>() {
                    @Override
                    public void call(Subscriber<? super RefreshTokenResponse> subscriber) {
                        RefreshTokenResponse response = new RefreshTokenResponse();

                        Token token = new Token();
                        token.setAccessToken("AccessToken");
                        token.setRefreshToken("RefreshToken");
                        response.setToken(token);

                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    }
                }).delay(1, TimeUnit.SECONDS);
    }

    @Override
    public Observable<Void> logout(Map deviceId) {
        return Observable
                .create(new Observable.OnSubscribe<Void>() {
                    @Override
                    public void call(Subscriber<? super Void> subscriber) {
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    }
                }).delay(1, TimeUnit.SECONDS);
    }

    @Override
    public Call<RefreshTokenResponse> refreshTokenSync(@Body RefreshTokenRequest refreshTokenRequest) {
        return null;
    }

    @Override
    public Observable<VerifyCredentialResponse> verifyCredential(
            @Body VerifyCredentialRequest verifyCredentialRequest) {
        return Observable
                .create(new Observable.OnSubscribe<VerifyCredentialResponse>() {
                    @Override
                    public void call(Subscriber<? super VerifyCredentialResponse> subscriber) {
                        VerifyCredentialResponse response = new VerifyCredentialResponse();
                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    }
                }).delay(1, TimeUnit.SECONDS);
    }

    @Override
    public Observable<Void> activateDevice(@Body ActivateRequest activateRequest) {
        return Observable
                .create(new Observable.OnSubscribe<Void>() {
                    @Override
                    public void call(Subscriber<? super Void> subscriber) {
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    }
                }).delay(1, TimeUnit.SECONDS);
    }

    @Override
    public Observable<LoginResponse> activationLogin(@Body final LoginRequest loginRequest) {
        return Observable
                .create(new Observable.OnSubscribe<LoginResponse>() {
                    @Override
                    public void call(Subscriber<? super LoginResponse> subscriber) {

                        if (loginRequest.getUserId().startsWith("9")) {
                            subscriber.onError(error(400, "E000001", "Invalid user id/password"));
                            return;
                        }

                        LoginResponse response = new LoginResponse();

                        Token token = new Token();
                        token.setAccessToken("AccessToken");
                        token.setRefreshToken("RefreshToken");
                        response.setToken(token);

                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    }
                }).delay(1, TimeUnit.SECONDS);
    }

    @Override
    public Observable<Void> activationLogout(@Body Map logoutRequest) {
        return Observable
                .create(new Observable.OnSubscribe<Void>() {
                    @Override
                    public void call(Subscriber<? super Void> subscriber) {
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    }
                }).delay(1, TimeUnit.SECONDS);
    }

    @Override
    public Observable<Void> deactivateDevice(@Body ActivateRequest activateRequest) {
        return Observable
                .create(new Observable.OnSubscribe<Void>() {
                    @Override
                    public void call(Subscriber<? super Void> subscriber) {
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    }
                }).delay(1, TimeUnit.SECONDS);
    }

}
