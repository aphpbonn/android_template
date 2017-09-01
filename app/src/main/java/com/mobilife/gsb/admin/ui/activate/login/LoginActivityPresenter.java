package com.mobilife.gsb.admin.ui.activate.login;

import com.mobilife.gsb.admin.data.DataManager;
import com.mobilife.gsb.admin.data.remote.ErrorHandler;
import com.mobilife.gsb.admin.injection.ConfigPersistent;
import com.mobilife.gsb.admin.ui.base.BasePresenter;
import com.mobilife.gsb.admin.util.Strings;

import javax.inject.Inject;

/**
 * Created by thawornlimwattanachai on 5/25/2017 AD.
 */
@ConfigPersistent
public class LoginActivityPresenter extends BasePresenter<LoginActivityView>{

    @Inject
    public LoginActivityPresenter(DataManager dataManager, ErrorHandler errorHandler) {
        this.mDataManager = dataManager;
        this.errorHandler = errorHandler;
    }

    @Override
    public void attachView(LoginActivityView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void login(final String username, final String password) {
        checkViewAttached();

        if(Strings.isEmpty(username) || Strings.isEmpty(password)){
            getMvpView().showEmptyUserInputError();
            return;
        }

        getMvpView().goToMainPage(null);
    }
}
