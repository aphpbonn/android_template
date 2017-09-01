package com.mobilife.gsb.admin.ui.main;

import com.mobilife.gsb.admin.data.model.User;
import com.mobilife.gsb.admin.ui.base.ProgressView;

/**
 * Created by thawornlimwattanachai on 5/25/2017 AD.
 */
public interface MainActivityView extends ProgressView {

    void showUser(User user);

}
