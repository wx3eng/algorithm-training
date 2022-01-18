package Algorithm.Sorting;

import java.util.Arrays;

public class MergeSort {
    public static int[] mergeSort(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        return mergeSort(array, 0, array.length - 1);
    }

    private static int[] mergeSort(int[] array, int left, int right) {
        // base case
        if (left == right) {
            return new int[] {array[left]};
        }
        int mid = left + (right - left) / 2;
        int[] leftArray = mergeSort(array, left, mid);
        int[] rightArray = mergeSort(array, mid + 1, right);
        int[] result = merge(leftArray, rightArray);
        return result;
    }

    private static int[] merge(int[] array1, int[] array2) {
        int l = 0, r = 0, cur = 0;
        int[] result = new int[array1.length + array2.length];
        while (l < array1.length && r < array2.length) {
            if (array1[l] < array2[r]) {
                result[cur++] = array1[l++];
            } else {
                result[cur++] = array2[r++];
            }
        }
        while (l < array1.length) {
            result[cur++] = array1[l++];
        }
        while (r < array2.length) {
            result[cur++] = array2[r++];
        }
        return result;
    }
//    TC(nlogn)
//    SC(n)

    public static void main(String[] args) {
        System.out.println(Arrays.toString(MergeSort.mergeSort(new int[] {5,2,7,4,1,3,8,6})));
    }
}
