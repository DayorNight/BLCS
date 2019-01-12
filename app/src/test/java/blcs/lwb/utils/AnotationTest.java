package blcs.lwb.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnotationTest {
    /**
     * @Test:将一个普通的方法修饰成为一个测试方法
     *      @Test(expected=XX.class):预判错误异常
     *      @Test(timeout=毫秒)：超时时间
     * @BeforeClass:它会在所有的方法运行前辈执行，static修饰
     * @AfterClass:它会在所有的方法运行结束后被执行，static修饰
     * @Before:会在每一个测试方法被执行前执行一次
     * @After:会在每一个测试方法运行后被执行一次
     * @Ignore:所修饰的测试方法会被测试运行器忽略
     * @RunWith:可以更改测试运行器org.junit.runner.Runner
     *
     */

    @Test
    public void testDivide(){
        System.out.println("testDivide");
    }

    @Test(expected = ArithmeticException.class)
    public void testDivide1(){
        System.out.println("testDivide1");
    }

    @Test(timeout = 2000)
    public void testDivide2(){
        System.out.println("testDivide2");
    }

}
