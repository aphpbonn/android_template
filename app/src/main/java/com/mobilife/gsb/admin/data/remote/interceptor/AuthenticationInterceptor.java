package com.mobilife.gsb.admin.data.remote.interceptor;

import android.content.Context;

import com.mobilife.gsb.admin.AdminApplication;
import com.mobilife.gsb.admin.data.local.SessionHelper;
import com.mobilife.gsb.admin.data.model.Login;
import com.mobilife.gsb.admin.data.model.Session;
import com.mobilife.gsb.admin.data.model.User;
import com.mobilife.gsb.admin.data.remote.ServiceFactory;
import com.mobilife.gsb.admin.data.remote.request.RefreshTokenRequest;
import com.mobilife.gsb.admin.data.remote.response.RefreshTokenResponse;
import com.mobilife.gsb.admin.data.remote.service.AuthenticationService;

import java.io.IOException;

import javax.inject.Inject;

import data.configuration.EnvConstant;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

/**
 * Created by JitsakP on 08-Feb-17.
 */

public class AuthenticationInterceptor implements Interceptor {

    Context context;

    @Inject
    SessionHelper sessionHelper;

    public AuthenticationInterceptor(Context context) {
        AdminApplication.get(context).getComponent().inject(this);
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();

        Request request = createRequestWithAuthorization(original, sessionHelper);

        Response response = chain.proceed(request);

        if(response.code() == 404){
            //Refresh new token
            RefreshTokenRequest tokenRequest = new RefreshTokenRequest();
            tokenRequest.setRefreshToken(
                    sessionHelper.getSignedInSession().getUser().getRefreshToken()
            );

            AuthenticationService authenticationService = ServiceFactory
                    .createService(AuthenticationService.class,
                            EnvConstant.SERVER_URL,
                            context);

            Call<RefreshTokenResponse> call = authenticationService.refreshTokenSync(tokenRequest);
            retrofit2.Response<RefreshTokenResponse> refreshTokenResponse = call.execute();

            refreshTokenResponse.body().getToken();

            //Update session data
            if (refreshTokenResponse != null) {
                sessionHelper.putAccessToken(refreshTokenResponse.body().getToken().getAccessToken());
                Session session = updateSession(refreshTokenResponse, sessionHelper.getSignedInSession());
                sessionHelper.putSignedInSession(session);
            }

            // create a new request and modify it accordingly using the new token
            Request newRequest = createRequestWithAuthorization(original, sessionHelper);

            // retry the request
            return chain.proceed(newRequest);
        }

        return response;
    }

    private Request createRequestWithAuthorization(Request original, SessionHelper sessionHelper){
        Request.Builder builder = original.newBuilder();

        if(sessionHelper != null && sessionHelper.getAccessToken() != null){
            builder.header("Authorization", "bearer " + sessionHelper.getAccessToken());
        }

        Request request = builder
                .method(original.method(), original.body())
                .build();

        return request;
    }


    private Session updateSession(retrofit2.Response<RefreshTokenResponse> refreshTokenResponse, Session session){
        if(refreshTokenResponse != null && refreshTokenResponse.body().getToken().getAccessToken() != null){
            User user = session.getUser();
            user.setAccessToken(refreshTokenResponse.body().getToken().getAccessToken());
            user.setRefreshToken(refreshTokenResponse.body().getToken().getRefreshToken());
            session.setUser(user);
            Login login = session.getLatestLogin();
            login.isLoggedIn = true;
            session.setLatestLogin(login);
        }
        return session;
    }

}
