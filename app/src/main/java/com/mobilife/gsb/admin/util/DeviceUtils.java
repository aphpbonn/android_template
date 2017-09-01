package com.mobilife.gsb.admin.util;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

/**
 * Created by thawornlimwattanachai on 4/12/2017 AD.
 */

public class DeviceUtils {

    public static String getManufacturerSerialNumber() {
        String serial = "";
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            serial = (String) get.invoke(c, "ril.serialnumber", "unknown");
        } catch (Exception ignored) {}
        return serial;
    }

    public String getSimId(Context context){
        TelephonyManager telemamanger = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telemamanger.getSimSerialNumber();
    }
}
