package com.mobilife.gsb.admin.data.remote.service;

import com.mobilife.gsb.admin.data.remote.response.TermsResponse;

import data.configuration.EnvConstant;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by JitsakP on 03-Feb-17.
 */

public interface ContentService {

    @GET(EnvConstant.API_VERSION+"/contents/terms")
    Observable<TermsResponse> getTermsAndCondition();

}
