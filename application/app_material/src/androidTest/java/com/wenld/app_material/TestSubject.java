package com.wenld.app_material;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.wenld.module_materialdesign.md_test.AnimatorActivity;

/**
 * <p/>
 * Author: wenld on 2017/5/18 16:04.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */

public class TestSubject extends ActivityInstrumentationTestCase2<AnimatorActivity> {
    private Instrumentation mInstrument;
    private AnimatorActivity mActivity;
    private Button bt1;
    public TestSubject() {
        super("com.wenld.app_material", AnimatorActivity.class);
    }
    public TestSubject(String pkg, Class<AnimatorActivity> activityClass) {
        super(pkg, activityClass);
    }
    @Override
    public void setUp() throws Exception {
        super.setUp();
        mInstrument = getInstrumentation();
        // 启动被测试的Activity
        mActivity = getActivity();
        bt1 = (Button) mActivity.findViewById(R.id.bt1);
    }
    public void testPreConditions() {
        // 在执行测试之前，确保程序的重要对象已被初始化
        assertTrue(bt1 != null);
    }

    public void testPublishSubject() {
        testPreConditions();
        launchActivity("com.wenld.app_material",AnimatorActivity.class,null);
        bt1.performClick();
//        onView(withId(R.id.bt1)).perform(click());
    }
}
