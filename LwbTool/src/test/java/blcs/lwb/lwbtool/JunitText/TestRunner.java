package blcs.lwb.lwbtool.JunitText;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner {
    @Test
    public void testAll(){
        Result result = JUnitCore.runClasses(ExampleUnitTest.class);
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
