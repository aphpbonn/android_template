package com.mobilife.gsb.admin;

import com.mobilife.gsb.admin.data.model.Login;
import com.mobilife.gsb.admin.data.model.ServerError;
import com.mobilife.gsb.admin.data.model.Session;
import com.mobilife.gsb.admin.data.model.Token;
import com.mobilife.gsb.admin.data.model.User;
import com.mobilife.gsb.admin.data.remote.response.ActivateResponse;
import com.mobilife.gsb.admin.data.remote.response.LoginResponse;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by thawornlimwattanachai on 5/31/2017 AD.
 */

public class TestDataFactory {

    public static HttpException newHttpException(int statusCode, String code, String message) {
        Response errorResponse = Response.error(
                statusCode,
                ResponseBody.create(
                        MediaType.parse("application/json"),
                        "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}"
                )
        );
        return new HttpException(errorResponse);
    }

    public static Session newSessionResponse() {
        Session session = new Session();
        User user = new User();
        user.setUserID("0");
        user.setName("xxx");
        user.setAccessToken("12345");
        user.setRefreshToken("12345");
        user.setBranchName("test");
        user.setActivate(true);
        session.setUser(user);
        Login login = new Login();
        login.isLoggedIn = true;
        session.setLatestLogin(login);
        return session;
    }

    public static LoginResponse newLoginSuccessfulResponse() {
        LoginResponse response = new LoginResponse();
        Token token = new Token();
        token.setAccessToken("12345");
        token.setRefreshToken("12345");
        response.setToken(token);
        return response;
    }

    public static Void newActivateDeviceResponse() {
        return null;

    }

    public static ServerError newServerError() {
        ServerError error = new ServerError();
        error.setCode("E010001");
        return error;
    }

    public static User newActivatedUser() {
        User user = new User();
        user.setName("aaaa");
        user.setPassword("bbbb");
        user.setUserID("4444");
        user.setBranchName("cccc");
        user.setActivate(true);
        return user;
    }

    public static User newNonActivatedUser() {
        User user = new User();
        user.setName("aaaa");
        user.setPassword("bbbb");
        user.setUserID("4444");
        user.setBranchName("cccc");
        user.setActivate(false);
        return user;
    }
}
