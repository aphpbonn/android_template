package com.mobilife.gsb.admin.data;

import com.mobilife.gsb.admin.data.local.DeviceHelper;
import com.mobilife.gsb.admin.data.local.SessionHelper;
import com.mobilife.gsb.admin.data.model.Login;
import com.mobilife.gsb.admin.data.model.Session;
import com.mobilife.gsb.admin.data.model.User;
import com.mobilife.gsb.admin.data.remote.request.LoginRequest;
import com.mobilife.gsb.admin.data.remote.response.LoginResponse;
import com.mobilife.gsb.admin.data.remote.service.AuthenticationService;
import com.mobilife.gsb.admin.event.LogoutEvent;
import com.mobilife.gsb.admin.util.Strings;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by thawornlimwattanachai on 10/31/2016 AD.
 * This class is the business core of application
 */

@Singleton
public class DataManager {

    private final AuthenticationService authenticationService;
    private final DeviceHelper mDeviceHelper;
    private final SessionHelper mSessionHelper;

    @Inject
    public DataManager(AuthenticationService authenticationService,
                       DeviceHelper deviceHelper,
                       SessionHelper sessionHelper) {
        this.authenticationService = authenticationService;
        this.mDeviceHelper = deviceHelper;
        this.mSessionHelper = sessionHelper;
    }

    public Observable<Session> activationLogin(final String userId, final String password) {
        return authenticationService.activationLogin(new LoginRequest(
                userId,
                Strings.md5(password),
                mDeviceHelper.getManufacturerSerialNumber()
        ))
                .map(new Func1<LoginResponse, Session>() {
                    @Override
                    public Session call(LoginResponse loginResponse) {
                        mSessionHelper.putAccessToken(loginResponse.getToken().getAccessToken());
                        Session session = createSession(loginResponse);
                        session.getUser().setUserID(userId);
                        session.getUser().setPassword(password);
                        mSessionHelper.putSignedInSession(session);

                        return session;
                    }
                });
    }

    private Session createSession(LoginResponse loginResponse) {
        Session session = null;
        if (loginResponse != null && loginResponse.getToken().getAccessToken() != null) {
            session = new Session();
            User user = new User();
            user.setName(loginResponse.getUserName());
            user.setBranchName(loginResponse.getBranchName());
            user.setActivate(loginResponse.isActivate());
            user.setAccessToken(loginResponse.getToken().getAccessToken());
            user.setRefreshToken(loginResponse.getToken().getRefreshToken());
            session.setUser(user);
            Login login = new Login();
            login.isLoggedIn = true;
            session.setLatestLogin(login);
        }
        return session;
    }

    public Observable<Void> activationLogout() {
        Map logoutRequest = new HashMap();
        logoutRequest.put("device_id", mDeviceHelper.getManufacturerSerialNumber());
        return authenticationService.activationLogout(logoutRequest)
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mSessionHelper.clear();
                        EventBus.getDefault().post(new LogoutEvent());
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        mSessionHelper.clear();
                    }
                });
    }


}
