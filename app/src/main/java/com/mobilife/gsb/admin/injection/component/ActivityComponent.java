package com.mobilife.gsb.admin.injection.component;


import com.mobilife.gsb.admin.injection.PerActivity;
import com.mobilife.gsb.admin.injection.module.ActivityModule;
import com.mobilife.gsb.admin.ui.activate.login.LoginActivity;
import com.mobilife.gsb.admin.ui.main.MainActivity;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent{

    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);
}
