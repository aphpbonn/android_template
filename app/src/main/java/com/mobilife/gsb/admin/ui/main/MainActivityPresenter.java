package com.mobilife.gsb.admin.ui.main;

import com.mobilife.gsb.admin.data.DataManager;
import com.mobilife.gsb.admin.data.model.User;
import com.mobilife.gsb.admin.data.remote.ErrorHandler;
import com.mobilife.gsb.admin.injection.ConfigPersistent;
import com.mobilife.gsb.admin.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by thawornlimwattanachai on 5/25/2017 AD.
 */
@ConfigPersistent
public class MainActivityPresenter extends BasePresenter<MainActivityView>{

    @Inject
    public MainActivityPresenter(DataManager dataManager, ErrorHandler errorHandler) {
        this.mDataManager = dataManager;
        this.errorHandler = errorHandler;
    }

    @Override
    public void attachView(MainActivityView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void loadUser() {

        getMvpView().showProgressDialog();
        //call service
        mDataManager.getUser("kinnch")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideProgressDialog();
                        getMvpView().showGenericErrorDialog("dsfdsfds", e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        getMvpView().hideProgressDialog();
                        getMvpView().showUser(user);
                    }
                });
    }
}
