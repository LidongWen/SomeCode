package com.wenld.app_material;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Before @After
    public void reset(){
        Log.e("reset","begin / end");
    }
    @Test
    public void test1(){
        Log.e("test1","begin / end");
//        assertThat(false).isTrue();
        Log.e("test1","begin / end");
    }
}