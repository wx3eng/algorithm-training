package Test;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MyExample {
    public static Double getMedium(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        if (array.length == 1) {
            return (double)array[0];
        }

        Arrays.sort(array);
        if (array.length % 2 == 1) {
            return (double)array[array.length / 2];
        } else {
            return array[array.length / 2 - 1] + (double)(array[array.length / 2] - array[array.length / 2 - 1]) / 2;
        }
    }
    public static void main(String[] args) {
        System.out.println(MyExample.getMedium(new int[] {1,2,3,4,5}));
    }

    @Test
    public void getMedium_null() {
        int[] array = null;
        assertEquals(null, MyExample.getMedium(null));
    }
}
