package Algorithm.Tree;

import MyUtil.TreeNode;

public class TreeHeight {
    public int treeHeight(TreeNode root) {
        if (root == null) return 0;
        int leftH = treeHeight(root.left);
        int rightH = treeHeight(root.right);
        if (leftH > rightH) {
            return leftH + 1;
        } else {
            return rightH + 1;
        }
    }
//    TC: O(n)
//    SC: O(tree height)
}
