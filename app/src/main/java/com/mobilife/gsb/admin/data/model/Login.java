package com.mobilife.gsb.admin.data.model;

import java.util.Date;

/**
 * Created by JitsakP on 06-Feb-17.
 */

public class Login {
    public String userID;
    public Date loggedInDate;
    public boolean isLoggedIn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Login login = (Login) o;

        if (isLoggedIn != login.isLoggedIn) return false;
        if (userID != null ? !userID.equals(login.userID) : login.userID != null) return false;
        return loggedInDate != null ? loggedInDate.equals(login.loggedInDate) : login.loggedInDate == null;

    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (loggedInDate != null ? loggedInDate.hashCode() : 0);
        result = 31 * result + (isLoggedIn ? 1 : 0);
        return result;
    }
}
