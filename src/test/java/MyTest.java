package src.test.java;

import com.microsoft.demo.Demo;
import org.junit.Test;

public class MyTest {
    @Test
    public void testMethod1() {
        Demo d = new Demo();
        d.DoSomething(true);
    }
}