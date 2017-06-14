package com.wenld.app_material;

import android.content.Context;

/**
 * Created by wenld on 2017/5/20.
 */

public class Test {
    private static Test ourInstance = null;
    private Context mContext;

    public static Test getInstance( Context mContext) {
        if (ourInstance == null) {
            ourInstance = new Test(mContext);
        }
        return ourInstance;
    }

    private Test(Context mContext) {
        this.mContext=mContext;
    }
}
