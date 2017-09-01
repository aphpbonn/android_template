package com.mobilife.gsb.admin.ui.activate.login;

import com.mobilife.gsb.admin.TestDataFactory;
import com.mobilife.gsb.admin.data.DataManager;
import com.mobilife.gsb.admin.data.model.User;
import com.mobilife.gsb.admin.data.remote.ErrorHandler;
import com.mobilife.gsb.admin.util.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by thawornlimwattanachai on 5/31/2017 AD.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginActivityPresenterTest {

    @Mock
    DataManager mMockDataManager;
    @Mock
    ErrorHandler mErrorHandler;
    @Mock
    LoginActivityView mLoginActivityView;

    private LoginActivityPresenter mLoginActivityPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mLoginActivityPresenter = new LoginActivityPresenter(mMockDataManager, mErrorHandler);
        mLoginActivityPresenter.attachView(mLoginActivityView);
    }

    @After
    public void detachView() {
        mLoginActivityPresenter.detachView();
    }

    @Test
    public void loginSuccessful() {

        String userId = "4444";
        String password = "xxx";

        doReturn(Observable.just(TestDataFactory.newSessionResponse()))
                .when(mMockDataManager)
                .activationLogin(userId, password);

        mLoginActivityPresenter.login(userId, password);

        verify(mLoginActivityView).showProgressDialog();
        verify(mLoginActivityView).hideProgressDialog();
        verify(mLoginActivityView).goToMainPage((User) anyObject());
    }

    @Test
    public void loginWithEmptyUserIdOrPasswordReturnError() {

        String userId = "";
        String password = "";

        doReturn(Observable.just(new Session()))
                .when(mMockDataManager)
                .activationLogin(userId, password);

        mLoginActivityPresenter.login(userId, password);

        verify(mLoginActivityView).showEmptyUserInputError();
    }

    @Test
    public void loginWithWrongUserIdOrPasswordReturnError() {

        doReturn(TestDataFactory.newServerError())
                .when(mErrorHandler)
                .extract((Throwable) anyObject());

        doReturn(Observable.error(
                TestDataFactory.newHttpException(400,"E0010001","Invalid user or password")))
                .when(mMockDataManager)
                .activationLogin(anyString(), anyString());

        mLoginActivityPresenter.login("4444", "xxx");

        verify(mLoginActivityView).showProgressDialog();
        verify(mLoginActivityView).hideProgressDialog();
        verify(mLoginActivityView).showGenericErrorDialog(anyString(), anyString());
        verify(mLoginActivityView).clearInput();
    }
}