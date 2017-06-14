package com.wenld.app_multitypeadapter.one2many;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * <p/>
 * Author: 温利东 on 2017/6/14 11:47.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */

public class Bean04 implements Serializable {
    public static final String TYPE_ONE="TYPE_ONE";
    public static final String TYPE_TWO="TYPE_TWO";

    public String title;
    public @DrawableRes int imgRes;
    public String imgUrl = "http://upload-images.jianshu.io/upload_images/1599843-876468433f5dfe91.jpg";
    public String type=TYPE_ONE;
    public Bean04(String title) {
        this.title=title;
    }

    public void setType(String type) {
        this.type = type;
    }
}
