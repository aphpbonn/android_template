package com.mobilife.gsb.admin.data.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobilife.gsb.admin.BuildConfig;
import com.mobilife.gsb.admin.data.remote.interceptor.ConnectivityInterceptor;
import com.mobilife.gsb.admin.data.remote.interceptor.HeaderInterceptor;
import com.mobilife.gsb.admin.data.remote.interceptor.UnauthorisedInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thawornlimwattanachai on 8/17/2016 AD.
 */

public class ServiceFactory {

    public static Retrofit retrofit;

    public static <T> T createService(Class<T> serviceClass, String serviceEndpoint,
                                      Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ConnectivityInterceptor(context))
                .addInterceptor(new UnauthorisedInterceptor(context))
//                .addInterceptor(new HeaderInterceptor(context))
//                .addInterceptor(new AuthenticationInterceptor(context))
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        
        retrofit = new Retrofit.Builder()
                .baseUrl(serviceEndpoint)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

}
