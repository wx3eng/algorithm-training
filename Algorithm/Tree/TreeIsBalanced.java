package Algorithm.Tree;

import MyUtil.TreeNode;

public class TreeIsBalanced {
    public int treeIsBalanced(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = treeIsBalanced(root.left);
        int rightHeight = treeIsBalanced(root.right);

        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.abs(leftHeight - rightHeight) + 1;
    }
}
