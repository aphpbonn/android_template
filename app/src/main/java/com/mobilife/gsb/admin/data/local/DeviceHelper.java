package com.mobilife.gsb.admin.data.local;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.mobilife.gsb.admin.injection.ApplicationContext;
import com.mobilife.gsb.admin.util.Strings;

import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by thawornlimwattanachai on 5/30/2017 AD.
 */

@Singleton
public class DeviceHelper {

    private Context context;

    @Inject
    public DeviceHelper(@ApplicationContext Context context) {
        this.context = context;
    }

    public String getManufacturerSerialNumber() {
        String serial = "";
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            serial = (String) get.invoke(c, "ril.serialnumber", "unknown");
        } catch (Exception ignored) {
        }
        return serial;
    }

    public String getSimId(){
        TelephonyManager telemamanger =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        String simId = telemamanger.getSimSerialNumber();
        if(Strings.isEmpty(simId)){
            simId = "";
        }
        return simId;
    }

}
