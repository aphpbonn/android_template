package com.mobilife.gsb.admin.ui.activate.login;

import com.mobilife.gsb.admin.data.model.User;
import com.mobilife.gsb.admin.ui.base.MvpView;
import com.mobilife.gsb.admin.ui.base.ProgressView;

/**
 * Created by thawornlimwattanachai on 5/25/2017 AD.
 */
public interface LoginActivityView extends ProgressView {

    void goToMainPage(User user);

    void showEmptyUserInputError();

    void clearInput();

    void hideKeyboard();
}
