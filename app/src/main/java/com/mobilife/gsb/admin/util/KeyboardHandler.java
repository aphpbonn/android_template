package com.mobilife.gsb.admin.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by thawornlimwattanachai on 2/3/2017 AD.
 */
public class KeyboardHandler {

    public static void hideSoftKeyboard(Activity activity){
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void showSoftKeyboard(Activity activity){
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
