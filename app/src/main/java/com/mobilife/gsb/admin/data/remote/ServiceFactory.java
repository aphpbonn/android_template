package com.mobilife.gsb.admin.data.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobilife.gsb.admin.BuildConfig;
import com.mobilife.gsb.admin.data.remote.interceptor.AuthenticationInterceptor;
import com.mobilife.gsb.admin.data.remote.interceptor.ConnectivityInterceptor;
import com.mobilife.gsb.admin.data.remote.interceptor.HeaderInterceptor;
import com.mobilife.gsb.admin.data.remote.interceptor.UnauthorisedInterceptor;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
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
                .addInterceptor(new HeaderInterceptor(context))
//                .addInterceptor(new AuthenticationInterceptor(context))
                .addInterceptor(logging)
                .connectTimeout(60, TimeUnit.SECONDS)
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
