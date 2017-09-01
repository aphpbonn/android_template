package com.mobilife.gsb.admin.data;

import com.mobilife.gsb.admin.data.local.DeviceHelper;
import com.mobilife.gsb.admin.data.model.User;
import com.mobilife.gsb.admin.data.remote.service.AuthenticationService;
import com.mobilife.gsb.admin.data.remote.service.GithubService;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by thawornlimwattanachai on 10/31/2016 AD.
 * This class is the business core of application
 */

@Singleton
public class DataManager {

    private final AuthenticationService authenticationService;
    private final DeviceHelper mDeviceHelper;
    private final GithubService mGithubService;

    @Inject
    public DataManager(AuthenticationService authenticationService,
                       GithubService githubService,
                       DeviceHelper deviceHelper) {
        this.authenticationService = authenticationService;
        this.mGithubService = githubService;
        this.mDeviceHelper = deviceHelper;
    }

    public Observable<User> getUser(String userId) {
        return mGithubService.getUser(userId);
    }
}
