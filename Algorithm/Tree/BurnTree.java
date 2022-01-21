package Algorithm.Tree;

import MyUtil.TreeArrayConvert;
import MyUtil.TreeNode;

import java.util.*;

public class BurnTree {
    public int timeToBurn(TreeNode root, TreeNode target) {
        if (root == null || target == null) {
            return 0;
        }
        List<TreeNode> pathToRoot = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();
        createPathToRoot(root, target, pathToRoot, visited);

        if (pathToRoot.isEmpty()) {
            return 0;
        }
        int result = 0;

        for (int i = 0; i < pathToRoot.size(); i++) {
            TreeNode node = pathToRoot.get(i);
            int otherBranchHeight;
            if (i == 0) {
                otherBranchHeight = Math.max(getHeight(node.left), getHeight(node.right));
            } else {
                if (!visited.contains(node.left)) {
                    otherBranchHeight = getHeight(node.left);
                } else {
                    otherBranchHeight = getHeight(node.right);
                }
            }
            result = Math.max(result, otherBranchHeight + i + 1);
        }

        // code for testing below
        for (TreeNode elem : pathToRoot) {
            System.out.print(elem.value + " ");
        }
        System.out.print("=>");
        return result;
    }

    private void createPathToRoot(TreeNode root, TreeNode target, List<TreeNode> pathToRoot, Set<TreeNode> visited) {
        if (root == null) {
            return;
        }
        if (pathToRoot.isEmpty() && root != target) {
            createPathToRoot(root.left, target, pathToRoot, visited);
            createPathToRoot(root.right, target, pathToRoot, visited);
            if (!pathToRoot.isEmpty()) {
                pathToRoot.add(root);
                visited.add(root);
            }
        } else if (root == target) { //&& pathToRoot.isEmpty() == true
            pathToRoot.add(root);
            visited.add(root);
        }
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        BurnTree result = new BurnTree();
        TreeArrayConvert converter = new TreeArrayConvert();
        TreeNode root1 = converter.constructTree(new Integer[]{0, 1, 2, 3, 4, 5, 6});
        TreeNode root2 = converter.constructTree(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7});
        System.out.println(result.timeToBurn(root2, root2.left));
    }
}
