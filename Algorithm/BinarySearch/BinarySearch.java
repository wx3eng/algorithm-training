package Algorithm.BinarySearch;

public class BinarySearch {
    public boolean binarySearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            return false;
        }
        int l = 0;
        int r = array.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (array[mid] == target) {
                return true;
            } else if (array[mid] < target) {
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return false;
    }
    // TC: O(logn)
    // SC: O(1)

    public static void main(String[] args) {
        int[] list = {1, 2, 4, 5, 7, 8, 9};
        int target = 3;
        BinarySearch result = new BinarySearch();
        System.out.println(result.binarySearch(list, target));
    }
}
