package Test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyExampleTest {
    @BeforeClass
    public static void before() {
        System.out.println("This is before the test process");
    }

    @Test
    public void getMedium_null() {
        assertEquals(null, MyExample.getMedium(null));
    }

    @Test
    public void getMedium_empty() {
        int[] array = new int[0];
        assertEquals(null, MyExample.getMedium(array));
    }

    @Test
    public void getMedium_odd() {
        int[] array = new int[] {3, 1, 5};
        assertEquals(3, MyExample.getMedium(array), 0.0001);
    }
}