package Algorithm.BinarySearch;

public class LastOccurrence {
    public int lastOccurrence(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (array[right] == target) {
            return right;
        } else if (array[left] == target) {
            return left;
        } else {
            return -1;
        }
    }
    // TC: O(logn)
    // SC: O(1)

    public static void main(String[] args) {
        LastOccurrence result = new LastOccurrence();
        int[] array = {1, 2, 3, 4, 4, 4, 5};
        int target = 0;
        System.out.println(result.lastOccurrence(array, target));
    }
}
