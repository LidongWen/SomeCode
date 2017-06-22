package com.wenld.router;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ActivityRouter {

    public static void startActivity(Context context, String action) {
        context.startActivity(new Intent(action));
    }

    public static void startActivity(Context context, Class clazz) {
        context.startActivity(getIntent(context, clazz));
    }

    public static void startActivity(Context context, String action, Uri uri) {
        context.startActivity(new Intent(action, uri));
    }

    public static Intent getIntent(Context context, Class clazz) {
        return new Intent(context, clazz);
    }

    public static void startActivityForName(Context context, String name) {
        try {
            Class clazz = Class.forName(name);
            startActivity(context, clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
