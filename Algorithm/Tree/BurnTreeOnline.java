package Algorithm.Tree;

import MyUtil.TreeArrayConvert;
import MyUtil.TreeNode;

public class BurnTreeOnline {
    public int timeToBurn(TreeNode root, TreeNode target) {
        if (root == null || target == null) {
            return 0;
        }
        int[] result = {0, 0}; // [0] as global max, [1] as a flag to indicate if the target node is found.
        // the flag is to make sure the target is within the tree
        timeToBurn(root, target, result);
        if (result[1] == 1) {
            return result[0];
        }
        return 0;
    }

    private int timeToBurn(TreeNode root, TreeNode target, int[] result) {
        // base case
        if (root == null) {
            return 0;
        }
        // ask from left & right child
        int left = timeToBurn(root.left, target, result);
        int right = timeToBurn(root.right, target, result);
        /*
        * *** Meaning of the returned values ***
        * (+) target is NOT in the left subtree or right subtree of the child node, the returned positive
        * value indicates height of the tree with the child node as the root
        *
        * (-) target is in either left subtree or right subtree of the child node, the returned negative
        * value indicates the distance between the child node and the target node (target must be below the child node)
        *
        * Note: at the current level, the returning value should follow the same rule
        *
        * *** Operation with the returned values ***
        * 1. If current node (root) is the target, both left & right will be positive (or 0), so the potential time to burn
        * the tree will be max(left tree height, right tree height) + 1. Compare this value with the currently stored
        * global max (result[0]) to determine if this is more likely to be the answer.
        *
        * 2. If current node is not target, and both left & right are positive (or 0), it means the target node is outside
        * the traversed part, so the time to burn the tree with the current node on the path will add 1 to account for
        * burning of the current node. We will not compare it with the global max as we know this must not be the answer
        *
        * 3. If one of the returned values is negative, the other must be positive, it means the target is in one of the
        * subtree, so the fire is burning up from the target to this node in the subtree with negative returned value,
        * then burns down the other subtree with positive returned value. Thus, we do abs(left) + abs(right) + 1. This
        * is potentially the answer, so we will need to compare it with the global max
        * */
        // condition 1
        if (root == target) {
            result[1] = 1;
            result[0] = Math.max(result[0], Math.max(Math.abs(left), Math.abs(right)) + 1);
            return -1;
        } else if (left >= 0 && right >= 0) { //&& root != target  <= this is condition 2
            return Math.max(left, right) + 1;
        } else { // left < 0 or right < 0  <= this is condition 3
            result[0] = Math.max(result[0], Math.abs(left) + Math.abs(right) + 1);
            return left < 0 ? left - 1 : right - 1;
        }
    }

    public static void main(String[] args) {
        BurnTreeOnline result = new BurnTreeOnline();
        TreeArrayConvert converter = new TreeArrayConvert();
        TreeNode root1 = converter.constructTree(new Integer[]{0, 1, 2, 3, 4, 5, 6});
        TreeNode root2 = converter.constructTree(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7});
        System.out.println(result.timeToBurn(root2, root2.right.left));
    }
}
