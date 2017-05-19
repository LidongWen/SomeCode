package com.wenld.app_material;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
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
        System.out.println( "reset  begin / end");
    }
    @Test
    public void test1(){
        System.out.println( "test1  begin / end");
        assertThat(true).isTrue();
        System.out.println( "test1  begin / end");
    }
}