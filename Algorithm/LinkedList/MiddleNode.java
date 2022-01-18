package Algorithm.LinkedList;

import MyUtil.ListNode;

public class MiddleNode {
    public static Integer middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.val;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.createList(new Integer[] {1, 2, 3});
        System.out.println(MiddleNode.middleNode(head));
    }
}
