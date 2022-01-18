package MyUtil;

public class MyTreeNode<T> {
    public T value;
    public MyTreeNode<T> left;
    public MyTreeNode<T> right;
    public MyTreeNode<T> parent;

    public MyTreeNode(T value) {
        this.value = value;
    }
}
