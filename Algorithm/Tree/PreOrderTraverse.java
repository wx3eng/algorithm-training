package Algorithm.Tree;

import MyUtil.TreeNode;

public class PreOrderTraverse {
    public static void preOrderTraverse(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        PreOrderTraverse.preOrderTraverse(head.left);
        PreOrderTraverse.preOrderTraverse(head.right);
    }
//    TC: O(n)
//    SC: O(tree height), worst case O(n)
}
