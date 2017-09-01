package com.mobilife.gsb.admin.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.mobilife.gsb.admin.AdminApplication;
import com.mobilife.gsb.admin.event.LogoutEvent;
import com.mobilife.gsb.admin.injection.component.ActivityComponent;
import com.mobilife.gsb.admin.injection.component.ConfigPersistentComponent;
import com.mobilife.gsb.admin.injection.component.DaggerConfigPersistentComponent;
import com.mobilife.gsb.admin.injection.module.ActivityModule;
import com.mobilife.gsb.admin.ui.activate.login.LoginActivity;
import com.mobilife.gsb.admin.ui.base.listeners.BaseView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity
        implements BaseView {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final Map<Long, ConfigPersistentComponent> sComponentsMap = new HashMap<>();

    private ActivityComponent mActivityComponent;
    private long mActivityId;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();
        ConfigPersistentComponent configPersistentComponent;
        if (!sComponentsMap.containsKey(mActivityId)) {
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(AdminApplication.get(this).getComponent())
                    .build();
            sComponentsMap.put(mActivityId, configPersistentComponent);
        } else {
            configPersistentComponent = sComponentsMap.get(mActivityId);
        }
        mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));

        initDaggerComponent();
        setContentView(getLayoutId());
        initButterKnifeComponent();
        attachViewToPresenter();
    }

    protected abstract void initDaggerComponent();

    protected abstract void attachViewToPresenter();

    protected void initButterKnifeComponent() {
        unbinder = ButterKnife.bind(this);
    }

    protected void attachFragment(int containnerLayout, Fragment fragment, String tag) {

        Fragment currentFragment = getSupportFragmentManager()
                .findFragmentByTag(tag);

        if (currentFragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            containnerLayout,
                            fragment,
                            tag
                    )
                    .commit();
        }
    }

    public abstract int getLayoutId();

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, mActivityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            sComponentsMap.remove(mActivityId);
        }
        super.onDestroy();
        unbinder.unbind();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogoutEvent(LogoutEvent event) {
        goToLoginPage();
    }

    protected void goToLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
