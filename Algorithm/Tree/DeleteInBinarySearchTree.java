package Algorithm.Tree;

import MyUtil.TreeNode;

public class DeleteInBinarySearchTree {
    public TreeNode deleteTree(TreeNode root, int key) {
        // This is an iterative way
        if (root == null) {
            return null;
        }

        TreeNode cur = root;
        TreeNode pre = null;
        while (cur.value != key) {
            pre = cur;
            if (cur.value > key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
            if (cur == null) {
                return root;
            }
        }
        // cur.key == key
        if (cur != root) {
            if (cur.left == null) {
                if (pre.left == cur) {
                    pre.left = cur.right;
                } else {
                    pre.right = cur.right;
                }
            } else if (cur.right == null) {
                if (pre.left == cur) {
                    pre.left = cur.left;
                } else {
                    pre.right = cur.left;
                }
            } else {
                if (pre.left == cur) {
                    pre.left = liftMinOfLeftSubtree(cur);
                } else {
                    pre.right = liftMinOfLeftSubtree(cur);
                }
            }
            return root;
        } else {
            if (cur.left == null) {
                return cur.right;
            } else if (cur.right == null) {
                return cur.left;
            } else {
                return liftMinOfLeftSubtree(cur);
            }
        }

        // this is a recursive way
        // if (root == null) {
        //   return root;
        // }

        // if (root.key != key) {
        //   root.left = deleteTree(root.left, key);
        //   root.right = deleteTree(root.right, key);
        //   return root;
        // } else {
        //   // root.key == key
        //   if (root.left == null) {
        //     return root.right;
        //   } else if (root.right == null) {
        //     return root.left;
        //   } else {
        //     // root.key == key && root.left != null && root.right != null
        //     return liftMinOfLeftSubtree(root);
        //   }
        // }
    }

    private TreeNode liftMinOfLeftSubtree(TreeNode root) {
        if (root.right.left == null) {
            root.right.left = root.left;
            return root.right;
        }
        TreeNode pre = root.right;
        TreeNode cur = root.right.left;
        while (cur.left != null) {
            pre = cur;
            cur = cur.left;
        }
        // cur.left == null, cur is the min of left subtree, pre is its parent node, cur is pre's left child
        pre.left = cur.right;
        cur.left = root.left;
        cur.right = root.right;
        return cur;
    }
}
