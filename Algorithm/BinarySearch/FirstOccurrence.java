package Algorithm.BinarySearch;

public class FirstOccurrence {
    public int firstOccurrence(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (array[left] == target) {
            return left;
        } else if (array[right] == target) {
            return right;
        } else {
            return -1;
        }
    }
    // TC: O(logn)
    // SC: O(1)

    public static void main(String[] args) {
        FirstOccurrence result = new FirstOccurrence();
        int[] array = {1, 1, 2, 2, 3};
        int target = 2;
        System.out.println(result.firstOccurrence(array, target));
    }
}
