package Algorithm.Sorting;

import MyUtil.IntArraySwap;

import java.util.Arrays;

public class RainbowSort {
    public void rainbowSort(int[] array) {
        int i = 0, j = 0;
        int k = array.length - 1;
        while (j <= k) {
            if (array[j] == 1) {
                IntArraySwap.swap(array, i++, j++);
            } else if (array[j] == 2) {
                j++;
            } else {
                IntArraySwap.swap(array, j, k--);
            }
        }
    }

//    TC: O(n)
//    SC: O(1)

    public static void main(String[] args) {
        RainbowSort result = new RainbowSort();
        int[] array = {1,2,3,1,2,3,1,3};
        result.rainbowSort(array);
        System.out.println(Arrays.toString(array));
    }
}
