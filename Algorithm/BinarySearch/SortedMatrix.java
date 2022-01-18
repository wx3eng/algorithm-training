package Algorithm.BinarySearch;

import java.util.Arrays;

public class SortedMatrix {
    public int[] sortedMatrix(int[][] matrix, int target) {
        int[] result = {-1, -1};
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int m = mid / matrix[0].length;
            int n = mid % matrix[0].length;
            if (matrix[m][n] == target) {
                result[0] = m;
                result[1] = n;
                return result;
            } else if (matrix[m][n] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    // TC: O(logm + logn)
    // SC: O(1)

    public static void main(String[] args) {
        SortedMatrix result = new SortedMatrix();
        System.out.println(Arrays.toString(result.sortedMatrix(new int[][]{{1, 2, 3}, {4, 5, 7}, {8, 9, 10}}, 9)));
    }
}
