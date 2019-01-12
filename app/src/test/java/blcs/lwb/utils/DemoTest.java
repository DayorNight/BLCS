package blcs.lwb.utils;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DemoTest {

    /**
     * 1、@BeforeClass修饰的方法会在所有方法被调用前被执行，
     * 而且该方法是静态的，所以当测试类被加载后接着就会运行它，
     * 而且在内存中它只会存在一份实例，它比较适合加载配置文件。
     * 2、@AfterClass所修饰的方法通常用来对资源的清理，如关闭数据库连接
     * 3、@Before和@After会在每个测试方法的前后各执行一次
     *
     * 执行流程：BeforeClass-->Before-->Test-->After-->AfterClass
     * 执行流程：BeforeClass-->Before-->Test-->After-->Before-->Test1-->After-->AfterClass
     */

    @Test
    public void testName() {
        assertFalse(false);

    }

    @Test
    public void test1() {
        System.out.println("Test1");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("Before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("After");
    }

    @BeforeClass
    public static  void setBeforeClass() throws Exception{
        System.out.println("BeforeClass");
    }
    @AfterClass
    public static void setAfterClass() throws Exception{
        System.out.println("AfterClass");
    }
}
