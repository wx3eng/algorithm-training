package Algorithm.Heap;

import java.util.*;

public class KSmallest {
/*
Find the K smallest numbers in an unsorted integer array A. The returned numbers should be in ascending order.

Assumptions

A is not null
K is >= 0 and smaller than or equal to size of A
Return

an array with size K containing the K smallest numbers in ascending order
Examples

A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}
 */
    public static void main(String[] args) {
        KSmallest solution = new KSmallest();
        System.out.print(Arrays.toString(solution.kSmallest(new int[] {2,5,3,1,4,7,8,6}, 8)));
    }

    public int[] kSmallest(int[] array, int k) {
        if (k == 0) return new int[0];
        if (k > array.length) k = array.length;
        int[] result = new int[k];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(array.length);
        for (int i = 0; i < array.length; i++) {
            minHeap.offer(array[i]);
        }
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }

//    private class MyComparator implements Comparator<Integer> { // for maxHeap to reverse natural order
//        @Override
//        public int compare(Integer a, Integer b) {
//            if (a > b) return -1;
//            else if (a == b) return 0;
//            else return 1;
//        };
//    }
}
