package blcs.lwb.utils;

import org.junit.Test;

import blcs.lwb.lwbtool.utils.StringUtils;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public  void addition_isCorrect1() {
        assertEquals(true, StringUtils.isPhone("15359283132"));
    }

}