package com.mobilife.gsb.admin.data;

import android.location.Location;

import com.mobilife.gsb.admin.TestDataFactory;
import com.mobilife.gsb.admin.data.local.DeviceHelper;
import com.mobilife.gsb.admin.data.model.MyLocation;
import com.mobilife.gsb.admin.data.remote.request.ActivateRequest;
import com.mobilife.gsb.admin.data.remote.response.LoginResponse;
import com.mobilife.gsb.admin.data.remote.service.AuthenticationService;
import com.mobilife.gsb.admin.data.remote.service.ContentService;
import com.mobilife.gsb.admin.util.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by thawornlimwattanachai on 5/31/2017 AD.
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock ContentService mContentService;
    @Mock AuthenticationService mAuthenticationService;
    @Mock ReactiveLocationProvider mLocationService;
    @Mock DeviceHelper mDeviceHelper;
    @Mock SessionHelper mSessionHelper;

    DataManager mDataManager;

    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mDataManager = new DataManager(
                mAuthenticationService,
                mContentService,
                mLocationService,
                mDeviceHelper,
                mSessionHelper
        );

    }

    @Test
    public void logInSuccessful() {

        LoginResponse loginResponse = TestDataFactory.newLoginSuccessfulResponse();

        doReturn(Observable.just(loginResponse))
                .when(mAuthenticationService)
                .activationLogin(anyObject());

        TestSubscriber<Session> testSubscriber = new TestSubscriber<>();

        mDataManager.activationLogin("", "").subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();

        verify(mSessionHelper).putAccessToken(loginResponse.getToken().getAccessToken());
        verify(mSessionHelper).putSignedInSession(anyObject());
    }

    @Test
    public void activateDeviceSuccessful() {

        double longitude = 1.0;
        double latitude = 1.0;

        Location location = new Location("");
        location.setLongitude(longitude);
        location.setLatitude(latitude);

        when(mAuthenticationService
                .activateDevice(new ActivateRequest(
                        "",
                        "",
                        "",
                        new MyLocation(longitude, latitude),
                        "",
                        0)))
                .thenReturn(null);

        mDataManager.activateDevice("", "", location);

        verify(mDeviceHelper).getManufacturerSerialNumber();
        verify(mDeviceHelper).getSimId();
    }


}