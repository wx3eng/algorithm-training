package MyUtil;

import java.util.*;

public class MyTree<T> {
    private MyTreeNode<T> root;

    public MyTreeNode<T> constructBinaryTreeFromArray(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        root = new MyTreeNode<>(array[0]);
        constructBinaryTreeFromArray(array, root, 0);
        return root;
    }

    private void constructBinaryTreeFromArray(T[] array, MyTreeNode<T> root, int i) {
        if (root == null) {
            return;
        }
        if (2 * i + 1 < array.length) {
            if (array[2 * i + 1] == null) {
                root.left = null;
            } else {
                root.left = new MyTreeNode<>(array[2 * i + 1]);
                constructBinaryTreeFromArray(array, root.left, 2 * i + 1);
            }
        }
        if (2 * i + 2 < array.length) {
            if (array[2 * i + 2] == null) {
                root.right = null;
            } else {
                root.right = new MyTreeNode<>(array[2 * i + 2]);
                constructBinaryTreeFromArray(array, root.right, 2 * i + 2);
            }
        }
    }

    public T[] destructBinaryTree(MyTreeNode<T> root) {
        if (root == null) {
            return null;
        }
        List<T> result = new ArrayList<>();
        Queue<MyTreeNode<T>> q = new LinkedList<>();
        q.offer(root);
        int curLevelSize = q.size();
        int counter = q.size();
        int countNull = 0;
        while (countNull < curLevelSize) {
            if (counter != 0) {
                MyTreeNode<T> cur = q.poll();
                counter -= 1;
                if (cur != null) {
                    countNull = 0;
                    result.add(cur.value);
                    q.add(cur.left);
                    q.add(cur.right);
                } else {
                    countNull++;
                    result.add(null);
                    q.add(null);
                    q.add(null);
                }
            } else {
                curLevelSize = q.size();
                counter = q.size();
            }
        }
        return (T[]) result.subList(0, result.size() - countNull).toArray();
    }

    public void testDestructTree(MyTreeNode<T> root) {
        T[] array = destructBinaryTree(root);
        for (T elem : array) {
            System.out.println(elem);
        }
    }

    public static void main(String[] args) {
        MyTree<Integer> result = new MyTree<>();
//        Integer[] array = {0, 1, 2, 3, 4, null, 6, null, null, 9};
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        MyTreeNode<Integer> root = result.constructBinaryTreeFromArray(array);
        result.testDestructTree(root);
    }
}
