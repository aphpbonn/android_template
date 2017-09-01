package com.mobilife.gsb.admin;

import android.app.Application;
import android.content.Context;

import com.mobilife.gsb.admin.injection.component.ApplicationComponent;
import com.mobilife.gsb.admin.injection.component.DaggerApplicationComponent;
import com.mobilife.gsb.admin.injection.module.ApplicationModule;

/**
 * Created by thawornlimwattanachai on 10/31/2016 AD.
 */

public class AdminApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static AdminApplication get(Context context) {
        return (AdminApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
