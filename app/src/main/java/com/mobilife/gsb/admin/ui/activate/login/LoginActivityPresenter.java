package com.mobilife.gsb.admin.ui.activate.login;

import com.mobilife.gsb.admin.data.DataManager;
import com.mobilife.gsb.admin.data.model.ServerError;
import com.mobilife.gsb.admin.data.model.Session;
import com.mobilife.gsb.admin.data.remote.ErrorHandler;
import com.mobilife.gsb.admin.injection.ConfigPersistent;
import com.mobilife.gsb.admin.ui.base.BasePresenter;
import com.mobilife.gsb.admin.util.Strings;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

        getMvpView().showProgressDialog();

        mSubscription = mDataManager.activationLogin(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Session>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ServerError error = extractError(e);
                        getMvpView().hideProgressDialog();
                        getMvpView().showGenericErrorDialog(
                                error.getCode(),
                                error.getMessage()
                        );
                        getMvpView().clearInput();
                        getMvpView().hideKeyboard();
                    }

                    @Override
                    public void onNext(Session session) {
                        getMvpView().hideProgressDialog();
                        getMvpView().goToMainPage(session.getUser());
                        getMvpView().hideKeyboard();
                    }
                });
    }
}
