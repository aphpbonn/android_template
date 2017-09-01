package com.mobilife.gsb.admin.data.remote;

import android.util.Log;

import com.mobilife.gsb.admin.data.exception.NoConnectivityException;
import com.mobilife.gsb.admin.data.model.ServerError;
import com.mobilife.gsb.admin.data.remote.response.ErrorResponse;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.net.ssl.SSLHandshakeException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by thawornlimwattanachai on 2/3/2017 AD.
 */

@Singleton
public class ErrorHandler {

    @Inject
    public ErrorHandler() {

    }

    public ServerError extract(Throwable e){
        ServerError error = new ServerError();

        if ( ServiceFactory.retrofit == null ) {
            // Mock .....
            ResponseBody body = ((HttpException) e).response().errorBody();
            try {
                JSONObject obj = new JSONObject(body.string());
                error.setCode(obj.getString("code"));
                error.setMessage(obj.getString("message"));
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            return error;
        }

        if (e instanceof NoConnectivityException){
            error.setCode("0");
            error.setMessage("คุณไม่ได้ต่ออินเตอร์เน็ต กรุณาต่ออินเตอร์เน็ทเพื่อใช้งาน");
        } else if (e instanceof HttpException) {

            int httpCode = ((HttpException) e).code();

            if(httpCode != 400){
                error.setCode(String.valueOf(httpCode));
                error.setMessage("ระบบขัดข้อง กรุณติดต่อเจ้าหน้าที่ดูแลระบบ");
                return error;
            }

            ResponseBody body = ((HttpException) e).response().errorBody();
            Converter<ResponseBody, ErrorResponse> errorConverter =
                    ServiceFactory
                            .retrofit
                            .responseBodyConverter(ErrorResponse.class, new Annotation[0]);

            // Convert the error body into our ServerError type.
            try {
                ErrorResponse errorResponse = errorConverter.convert(body);
                error = errorResponse.getError();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e instanceof SSLHandshakeException){
            error.setCode("0");
            error.setMessage("กรุณาตรวจสอบความปลอดภัย");
        }else {
            error.setCode("0");
            error.setMessage("ระบบขัดข้อง กรุณติดต่อเจ้าหน้าที่ดูแลระบบ");
        }

        return error;
    }
}
