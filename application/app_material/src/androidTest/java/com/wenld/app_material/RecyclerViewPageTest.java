package com.wenld.app_material;


import android.test.ActivityInstrumentationTestCase2;

import com.wenld.module_materialdesign.md_test.AnimatorActivity;
import com.wenld.recyclerview_test.recyclertest.RecyclerViewToViewPageActivity;

/**
 * <p/>
 * Author: wenld on 2017/5/18 17:47.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */

public class RecyclerViewPageTest extends ActivityInstrumentationTestCase2<RecyclerViewToViewPageActivity> {
    public RecyclerViewPageTest(Class<RecyclerViewToViewPageActivity> activityClass) {
        super(activityClass);
    }

    public void testOpen(){
        launchActivity("com.wenld.recyclerview_test",AnimatorActivity.class,null);
    }
}
