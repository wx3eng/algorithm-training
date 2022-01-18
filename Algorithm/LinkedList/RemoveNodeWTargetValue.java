package Algorithm.LinkedList;

import MyUtil.ListNode;

public class RemoveNodeWTargetValue {
    public static ListNode removeNodeWTargetValue (ListNode head, int target) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(target - 1);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null) {
            if (head.val == target) {
                prev.next = head.next;
            } else {
                prev = head;
            }
            head = head.next;
        }
        return dummy.next;
    }
}
