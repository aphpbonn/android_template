package com.mobilife.gsb.admin.data.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by JitsakP on 06-Feb-17.
 */

public class Session implements Comparable<Session> {
    protected User user;
    @Nullable protected Login latestLogin;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Nullable
    public Login getLatestLogin() {
        return latestLogin;
    }

    public void setLatestLogin(@Nullable Login latestLogin) {
        this.latestLogin = latestLogin;
    }

    @Override
    public int compareTo(@NonNull Session another) {
        return user.getUserID().compareToIgnoreCase(another.user.getUserID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (user != null ? !user.equals(session.user) : session.user != null) return false;
        return latestLogin != null ? latestLogin.equals(session.latestLogin) : session.latestLogin == null;

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (latestLogin != null ? latestLogin.hashCode() : 0);
        return result;
    }
}
