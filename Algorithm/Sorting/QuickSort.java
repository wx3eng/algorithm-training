package Algorithm.Sorting;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private Random random = new Random();

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public void quickSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotIndex = left + random.nextInt(right - left + 1);
        swap (array, pivotIndex, right);
        int i = left;
        int j = right - 1;
        while (i <= j) {
            if (array[i] < array[right]) {
                i++;
            } else {
                swap(array, i, j--);
            }
        }
        swap(array, i, right);
        quickSort(array, left, i - 1);
        quickSort(array, i+1, right);
    }
//    TC: worst case: O(n^2), average: O(nlogn)
//    SC: worst case: O(n), average: O(logn)

    public static void main(String[] args) {
        QuickSort result = new QuickSort();
        int[] array = {1,4,6,8,9,5,3,2};
        result.quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
