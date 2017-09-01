package com.mobilife.gsb.admin.data.remote.interceptor;

import android.content.Context;
import android.os.Build;

import com.mobilife.gsb.admin.AdminApplication;
import com.mobilife.gsb.admin.BuildConfig;
import com.mobilife.gsb.admin.data.local.SessionHelper;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by JitsakP on 08-Feb-17.
 */

public class HeaderInterceptor implements Interceptor {

    Context context;

    @Inject
    SessionHelper sessionHelper;

    public HeaderInterceptor(Context context) {
        AdminApplication.get(context).getComponent().inject(this);
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        Request request = createRequestWithAuthorization(original, sessionHelper);
        Response response = chain.proceed(request);

        return response;
    }

    private Request createRequestWithAuthorization(Request original, SessionHelper sessionHelper){
        Request.Builder builder = original.newBuilder();

        builder.header(
                "x-device", "Google (" + Build.MANUFACTURER +" "+ Build.MODEL + ") " +
                        "android ("+ Build.VERSION.RELEASE + ")"
        );

        builder.header("x-application", "GSB Admin (" + BuildConfig.VERSION_NAME +")");
        builder.header("accept-language", "th");

        Request request = builder
                .method(original.method(), original.body())
                .build();

        return request;
    }


}
