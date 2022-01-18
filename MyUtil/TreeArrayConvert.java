package MyUtil;

import java.util.*;

public class TreeArrayConvert {
    public TreeNode constructTree(Integer[] array) { // array must represent complete tree
        // use Level order traversal to construct a tree
        if (array == null || array.length == 0 || array[0] == null) {
            return null;
        }
        TreeNode[] nodeArray = new TreeNode[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                nodeArray[i] = null;
            } else {
                nodeArray[i] = new TreeNode(array[i]);
            }
        }
        for (int i = 1; i < array.length; i++) {
            if (nodeArray[i] == null) continue;
            if ((i - 1) % 2 == 0) {
                nodeArray[(i - 1) / 2].left = nodeArray[i];
                nodeArray[i].parent = nodeArray[(i - 1) / 2];
            }
            if ((i - 1) % 2 == 1) {
                nodeArray[(i - 1) / 2].right = nodeArray[i];
                nodeArray[i].parent = nodeArray[(i - 1) / 2];
            }
        }
        return nodeArray[0];
    }

    public Integer[] destructTree(TreeNode root) {
        // use Level order traversal to destruct a tree
        if (root == null) {
            return null;
        }
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> resultList = new ArrayList<>();
        q.offer(root);
        boolean validLayer = true;
        while (validLayer) {
            validLayer = false;
            int size = q.size();
            for (int i = 0 ; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur != null) {
                    resultList.add(cur.value);
                    validLayer = true;
                    q.offer(cur.left);
                    q.offer(cur.right);
                } else {
                    resultList.add(null);
                    q.offer(null);
                    q.offer(null);
                }
            }
        }
        while (resultList.get(resultList.size() - 1) == null) {
            resultList.remove(resultList.size() - 1);
        }
        Integer[] result = new Integer[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(0);
            resultList.remove(0);
        }
        return result;
    }
}
