package com.mobilife.gsb.admin.data.remote.interceptor;

import android.content.Context;

import com.mobilife.gsb.admin.AdminApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class UnauthorisedInterceptor implements Interceptor {

    public UnauthorisedInterceptor(Context context) {
        AdminApplication.get(context).getComponent().inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(chain.request());
        switch (response.code()){
            case 401:
            case 403:
            case 405:
            case 406:
            case 408:
            case 409:
            case 426:
            case 500:
            case 501:
            case 502:
            case 503:
            case 504:
            case 505:
            //EventBus.getDefault().post();
                break;

        }
        return response;
    }
}
