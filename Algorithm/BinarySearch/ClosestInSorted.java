package Algorithm.BinarySearch;

import java.math.*;

public class ClosestInSorted {
    public int closestInSorted(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                right = mid;
            } else if (array[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        int para = Math.abs(array[left] - target) - Math.abs(array[right] - target);
        if (para <= 0) {
            return left;
        } else {
            return right;
        }
    }

    public static int closestInSortedStatic(int[] array, int target) {
        ClosestInSorted result = new ClosestInSorted();
        return result.closestInSorted(array, target);
    }
    // TC: O(logn)
    // SC: O(1)

    public static void main(String[] args) {
        ClosestInSorted result = new ClosestInSorted();
        int[] array = {3, 5, 5, 5, 5, 6, 9, 10};
        int target = 8;
        System.out.println(result.closestInSorted(array, target));
    }
}
