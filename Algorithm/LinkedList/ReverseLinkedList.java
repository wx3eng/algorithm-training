package Algorithm.LinkedList;

import MyUtil.ListNode;

public class ReverseLinkedList {
    public static ListNode reverseLinkedList (ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return cur;
    }
//    TC: O(n)
//    SC: O(1)

    public static ListNode reverseLinkedListRecursion (ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseLinkedListRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
//    TC: O(n)
//    SC: O(n)

}
