package com.mobilife.gsb.admin.ui.base;

import com.mobilife.gsb.admin.data.DataManager;
import com.mobilife.gsb.admin.data.model.ServerError;
import com.mobilife.gsb.admin.data.remote.ErrorHandler;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by thawornlimwattanachai on 5/25/2017 AD.
 */
public class BasePresenter <T extends MvpView> implements Presenter<T> {

    private T mMvpView;
    protected DataManager mDataManager;
    protected Subscription mSubscription;
    protected ErrorHandler errorHandler;

    @Inject
    public BasePresenter(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public BasePresenter() {
    }

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    public ServerError extractError(Throwable e) {
        return errorHandler.extract(e);
    }
}
