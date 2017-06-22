package com.wenld.router;

import android.support.v4.app.Fragment;

public class FragmentRouter {

    public static Fragment getFragment(String name) {
        Fragment fragment;
        try {
            Class fragmentClass = Class.forName(name);
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fragment;
    }
}
