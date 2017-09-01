package com.mobilife.gsb.admin.ui.main;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import com.mobilife.gsb.admin.R;
import com.mobilife.gsb.admin.data.model.User;
import com.mobilife.gsb.admin.ui.base.BaseActivity;
import com.mobilife.gsb.admin.ui.base.BaseDialog;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainActivityView{

    @BindView(R.id.text_user_id) TextView textUserId;

    @Inject
    MainActivityPresenter mainActivityPresenter;

    protected Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityPresenter.loadUser();
    }

    @Override
    protected void initDaggerComponent() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachViewToPresenter() {
        mainActivityPresenter.attachView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void showUser(User user) {
        textUserId.setText(user.getId());
    }

    @Override
    public void showGenericErrorDialog(String code, String message) {
        BaseDialog.createSimpleOkErrorDialog(this, message).show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog = BaseDialog.createProgressDialog(this,"please wait", "processing");
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }
}
