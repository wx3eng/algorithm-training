package MyUtil;

public class MyDeque<T> {
    //Fields
    public T[] array;
    private int head;
    private int tail;
    private static final int DEFAULT_CAPACITY = 10;

    //CTORs
    public MyDeque() {
        this(DEFAULT_CAPACITY);
    }

    public MyDeque(int capacity) {
        if (capacity < 0 || capacity > 1000) {
            // capacity is limited to be within [0, 1000]
            // if under/over the limit, use default limit
            capacity = DEFAULT_CAPACITY;
        }
        array = (T[]) new Object[capacity + 1];
        head = capacity;
        tail = 0;
    }

    //Methods
    public boolean offerFirst(T elem) {
        if (isFull()) {
            return false;
        }
        array[head] = elem;
        head = (array.length + head - 1) % array.length;
        return true;
    }

    public boolean offerLast(T elem) {
        if (isFull()) {
            return false;
        }
        array[tail] = elem;
        tail = (tail + 1) % array.length;
        return true;
    }

    public T pollFirst() {
        if (isEmpty()) {
            return null;
        }
        T result = array[(head + 1) % array.length];
        head = (head + 1) % array.length;
        return result;
    }

    public T pollLast() {
        if (isEmpty()) {
            return null;
        }
        T result = array[(array.length + tail - 1) % array.length];
        tail = (array.length + tail - 1) % array.length;
        return result;
    }

    public T peekFirst() {
        return isEmpty() ? null : array[(head + 1) % array.length];
    }

    public T peekLast() {
        return isEmpty() ? null : array[(array.length + tail - 1) % array.length];
    }

    public boolean isFull() {
        return head == tail;
    }

    public boolean isEmpty() {
        return tail - head == 1 || head - tail == array.length - 1;
    }

    public int size() {
        return tail > head ? tail - head - 1 : tail - head + array.length - 1;
    }

    public static void main(String[] args) {
        MyDeque<Integer> result = new MyDeque<>();
        System.out.println(result.isEmpty());
    }
}
