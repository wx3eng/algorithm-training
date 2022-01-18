package MyUtil;

import java.util.PriorityQueue;

public class MinHeap {
    public static void minHeap (){
        PriorityQueue<Integer> result = new PriorityQueue<>();
        result.offer(1);
        if (result.isEmpty() == true) System.out.println("pq is empty.");
    }

    public static void main (String[] args) {
        MinHeap.minHeap();
    }
}
