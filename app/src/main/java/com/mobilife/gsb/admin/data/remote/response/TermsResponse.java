package com.mobilife.gsb.admin.data.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thawornlimwattanachai on 5/26/2017 AD.
 */
public class TermsResponse {
    @SerializedName("content") protected String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
