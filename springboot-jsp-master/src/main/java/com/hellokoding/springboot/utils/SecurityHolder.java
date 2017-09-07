package com.hellokoding.springboot.utils;

import com.hellokoding.springboot.domain.User;

/**
 * Created by kietlam on 9/7/2017.
 */
public final class SecurityHolder {
    private static final ThreadLocal<User> currentUserHolder = new ThreadLocal<>();

    public static User getCurrentUser() {
        return currentUserHolder.get();
    }

    public static void setCurrentUser(User user) {
        currentUserHolder.set(user);
    }
}
