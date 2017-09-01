package com.mobilife.gsb.admin.data.exception;

import java.io.IOException;

/**
 * Created by thawornlimwattanachai on 4/27/2017 AD.
 */
public class NoConnectivityException extends IOException {
    @Override
    public String getMessage() {
        return "No connectivity exception";
    }
}
