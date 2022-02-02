package MyUtil;

public class MyHashMap<K, V> {
    static class Entry<K, V> {
        V value;
        K key;
        Entry<K, V> next;
        Entry(K key, V value) {
            this.value = value;
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
    private static final int DEFAULT_CAPACITY = 11;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final double RESIZE_FACTOR = 1.5;
    private Entry<K, V>[] array;
    private double loadFactor;
    private int size;

    //Ctor
    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int capacity, double loadFactor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity cannot be less than 1");
        }
        array = (Entry<K, V> []) new Entry[capacity];
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    public V put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> cur = array[index];
        while (cur != null) {
            if (equalsKey(cur.getKey(), key)) {
                V result = cur.getValue();
                cur.setValue(value);
                return result;
            }
        }
        Entry<K, V> newEntry = (Entry<K, V>) new Entry(key, value);
        newEntry.next = array[index];
        array[index] = newEntry;
        size++;

        if (size > loadFactor * array.length) {
            rehash();
        }
        return null;
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> cur = array[index];
        while (cur != null) {
            if (equalsKey(cur.getKey(), key)) {
                return cur.getValue();
            }
            cur = cur.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);
        Entry<K, V> cur = array[index];
        Entry<K, V> pre = null;
        while (cur != null) {
            if (equalsKey(cur.getKey(), key)) {
                if (cur == array[index]) {
                    array[index] = cur.next;
                } else {
                    pre.next = cur.next;
                }
                size--;
                return cur.getValue();
            }
            pre = cur;
            cur = cur.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        Entry<K, V> cur = array[index];
        while (cur != null) {
            if (equalsKey(cur.getKey(), key)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void rehash() {
        Entry<K, V>[] oldArray = array;
        array = (Entry<K, V>[]) new Entry[(int) (array.length * RESIZE_FACTOR)];
        for (Entry<K, V> entry : oldArray) {
            Entry<K, V> cur = entry;
            while (cur != null) {
//                put(cur.getKey(), cur.getValue());
                Entry<K, V> next = cur.next;
                int index = getIndex(cur.getKey());
                cur.next = array[index];
                array[index] = cur;
                cur = next;
            }
        }
    }

    private boolean equalsKey(K key1, K key2) {
        return key1 == key2 || key1 != null && key1.equals(key2);
    }

//    private boolean equalsValue(V value1, V value2) {
//        return value1 == value2 || value1 != null && value1.equals(value2);
//    }

    private int getHashCode(K key) {
        if (key == null) {
            return 0;
        }
        int hashCode = key.hashCode();
        return hashCode & 0X7FFFFFFF;
    }

    private int getIndex(K key) {
        return getHashCode(key) % array.length;
    }

    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<>(2, 0.8);
        System.out.println(map.isEmpty());
        System.out.println(map.put("1", "1111"));
        System.out.println(map.put("1", "222"));
        System.out.println(map.put("2", "1234"));
        System.out.println(map.array.length);
        System.out.println(map.size());
        System.out.println(map.containsKey("2"));
        System.out.println(map.getIndex("1"));
        System.out.println(map.get("1"));
        System.out.println(map.remove("1"));
        System.out.println(map.get("1"));
        System.out.println(map.isEmpty());
    }
}
