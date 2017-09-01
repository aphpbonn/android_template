package com.mobilife.gsb.admin.util.timer;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by thawornlimwattanachai on 5/31/2017 AD.
 */

public class Timer implements Runnable {

    private Handler mHandler = new Handler();
    private long millisecondTime, startTime, timeBuffer, updateTime = 0L ;

    @Override
    public void run() {
        millisecondTime = SystemClock.uptimeMillis() - startTime;
        updateTime = timeBuffer + millisecondTime;
        mHandler.postDelayed(this, 0);
    }

    public void startTimer() {
        startTime = SystemClock.uptimeMillis();
        mHandler.postDelayed(this, 0);
    }

    public void pauseTimer() {
        timeBuffer += millisecondTime;
        mHandler.removeCallbacks(this);
    }

    public void stopTimer() {
        millisecondTime = 0L ;
        startTime = 0L ;
        timeBuffer = 0L ;
        updateTime = 0L ;
    }

    public long getUpdateTime() {
        Log.d("update time", updateTime+"");
        return updateTime;
    }
}
