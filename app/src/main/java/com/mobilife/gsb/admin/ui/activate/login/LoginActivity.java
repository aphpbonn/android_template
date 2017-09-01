package com.mobilife.gsb.admin.ui.activate.login;

import android.app.Dialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.mobilife.gsb.admin.R;
import com.mobilife.gsb.admin.data.model.User;
import com.mobilife.gsb.admin.ui.base.BaseActivity;
import com.mobilife.gsb.admin.ui.base.BaseDialog;
import com.mobilife.gsb.admin.ui.main.MainActivity;
import com.mobilife.gsb.admin.util.KeyboardHandler;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    @BindView(R.id.button_login) Button buttonLogin;
    @BindView(R.id.text_input_id) EditText editTextUserId;
    @BindView(R.id.text_input_password) EditText editTextPassword;

    protected Dialog progressDialog;

    @Inject
    LoginActivityPresenter mLoginActivityPresenter;

    @Override
    protected void initDaggerComponent() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachViewToPresenter() {
        mLoginActivityPresenter.attachView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.button_login)
    public void onLoginButtonClicked() {
        mLoginActivityPresenter.login(
                editTextUserId.getText().toString(),
                editTextPassword.getText().toString()
        );
    }

    @Override
    public void goToMainPage(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void showEmptyUserInputError() {
        BaseDialog.createSimpleOkErrorDialog(
                this,
                "กรุณากรอกไอดีและรหัสผู้ใช้งาน เพื่อเข้าสู่ระบบ"

        ).show();
    }

    @Override
    public void clearInput() {
        editTextUserId.setText("");
        editTextPassword.setText("");
    }

    @Override
    public void hideKeyboard() {
        KeyboardHandler.hideSoftKeyboard(this);
    }

    @Override
    public void showGenericErrorDialog(String code, String message) {
        BaseDialog.createSimpleOkErrorDialog(
                this,
                code,
                message

        ).show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog = BaseDialog.createProgressDialog(
                                this,
                                getString(R.string.dialog_title_waiting),
                                "กรุณารอสักครู่");
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if(progressDialog !=null) progressDialog.dismiss();
    }
}
