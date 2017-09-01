package com.mobilife.gsb.admin.data.remote.mock;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by kasam on 2/8/2017.
 */

class BaseServiceMock {

    public HttpException error(int statusCode, String code, String message) {
        Response errorResponse = Response.error(
                statusCode,
                ResponseBody.create(
                        MediaType.parse("application/json"),
                        "{\"code\":\"" + code + "\","
                            + "\"message\":\"" + message + "\"}"
                )
        );
        return new HttpException(errorResponse);
    }

}
