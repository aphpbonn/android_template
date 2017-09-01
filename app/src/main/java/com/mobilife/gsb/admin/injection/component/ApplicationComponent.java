package com.mobilife.gsb.admin.injection.component;

import android.app.Application;
import android.content.Context;

import com.mobilife.gsb.admin.data.DataManager;
import com.mobilife.gsb.admin.data.remote.ErrorHandler;
import com.mobilife.gsb.admin.data.remote.interceptor.HeaderInterceptor;
import com.mobilife.gsb.admin.data.remote.interceptor.UnauthorisedInterceptor;
import com.mobilife.gsb.admin.injection.ApplicationContext;
import com.mobilife.gsb.admin.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Application application();
    DataManager dataManager();
    ErrorHandler errorHandler();

    void inject(UnauthorisedInterceptor unauthorisedInterceptor);

    void inject(HeaderInterceptor headerInterceptor);
}
