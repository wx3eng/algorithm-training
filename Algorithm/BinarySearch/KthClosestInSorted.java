package Algorithm.BinarySearch;

public class KthClosestInSorted {
    public Integer kthClosestInSorted(int[] array, int target, int k) {
        if (array == null || array.length == 0 || k > array.length || k <= 0) {
            return null;
        }
        int left = ClosestInSorted.closestInSortedStatic(array, target);
        int right = left + 1;
        int result = 0;
        while (k > 0) {
            if (left < 0) {
                right += k - 1;
                return array[right];
            } else if (right > array.length - 1) {
                left -= k - 1;
                return array[left];
            } else {
                if (Math.abs(array[left] - target) > Math.abs(array[right] - target)) {
                    result = array[right++];
                } else {
                    result = array[left--];
                }
                k--;
            }
        }
        return result;
    }
    // TC: O(logn + k)
    // SC: O(1)

    public static void main(String[] args) {
        KthClosestInSorted result = new KthClosestInSorted();
        int[] array = {3, 5, 5, 5, 5, 6, 9, 10};
        int target = 8;
        System.out.println(result.kthClosestInSorted(array, target, 8));
    }
}
