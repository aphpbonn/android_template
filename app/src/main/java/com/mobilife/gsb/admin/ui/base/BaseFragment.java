package com.mobilife.gsb.admin.ui.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobilife.gsb.admin.R;
import com.mobilife.gsb.admin.util.KeyboardHandler;
import com.mobilife.gsb.admin.util.Strings;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by thawornlimwattanachai on 5/25/2017 AD.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    protected Dialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        initButterKnifeComponent(rootView);
        attachViewToPresenter();
        return rootView;
    }

    public abstract int getLayoutId();
    protected abstract void attachViewToPresenter();

    protected void initButterKnifeComponent(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void showProgress(String message) {
        if(Strings.isEmpty(message)){
            message = getString(R.string.dialog_message_waiting);
        }
        progressDialog = BaseDialog.createProgressDialog(
                getContext(), getString(R.string.dialog_title_waiting), message
        );
        progressDialog.show();
    }

    protected void showGenericError(String code, String message) {
        BaseDialog.createSimpleOkErrorDialog(
                getContext(),
                code,
                message

        ).show();
    }

    protected void showGenericError(String message) {
        BaseDialog.createSimpleOkErrorDialog(
                getContext(),
                message

        ).show();
    }

    protected void hideProgress() {
        if(progressDialog !=null) progressDialog.dismiss();
    }

    protected void showKeyboard(){
        KeyboardHandler.showSoftKeyboard(getActivity());
    }

    protected void hideKeyboard(){
        KeyboardHandler.hideSoftKeyboard(getActivity());
    }
}
