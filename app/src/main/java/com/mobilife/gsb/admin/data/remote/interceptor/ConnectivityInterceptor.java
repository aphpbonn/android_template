package com.mobilife.gsb.admin.data.remote.interceptor;

import android.content.Context;

import com.mobilife.gsb.admin.data.exception.NoConnectivityException;
import com.mobilife.gsb.admin.util.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by thawornlimwattanachai on 4/27/2017 AD.
 */
public class ConnectivityInterceptor implements Interceptor {

    private Context mContext;

    public ConnectivityInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtil.isOnline(mContext)) {
            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
