package Algorithm.Sorting;

import java.util.Arrays;

public class SelectionSort {
    public static void selectionSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }
//    TC: O(n^2)
//    SC: O(1)

    public static void swap(int[] array, int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }

    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 5, 4};
        SelectionSort.selectionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
