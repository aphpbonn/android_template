package com.mobilife.gsb.admin.injection.module;

import android.app.Application;
import android.content.Context;

import com.mobilife.gsb.admin.BuildConfig;
import com.mobilife.gsb.admin.data.remote.ServiceFactory;
import com.mobilife.gsb.admin.data.remote.mock.AuthenticationServiceMock;
import com.mobilife.gsb.admin.data.remote.service.AuthenticationService;
import com.mobilife.gsb.admin.data.remote.service.ContentService;
import com.mobilife.gsb.admin.data.remote.service.GithubService;
import com.mobilife.gsb.admin.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import data.configuration.EnvConstant;
//import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    //@Provides
    //ReactiveLocationProvider provideLocationService() {
    //    return new ReactiveLocationProvider(mApplication);
    //}

    @Provides
    @Singleton
    AuthenticationService provideAuthenticationService() {
        if(BuildConfig.MOCK_VERSION){
            return new AuthenticationServiceMock();
        }

        return ServiceFactory.createService(AuthenticationService.class,
                EnvConstant.SERVER_URL,
                mApplication);
    }

    @Provides
    @Singleton
    GithubService provideGithubService() {
//        if(BuildConfig.MOCK_VERSION){
//            return new AuthenticationServiceMock();
//        }

        return ServiceFactory.createService(GithubService.class,
                "https://api.github.com/",
                mApplication);
    }

}
