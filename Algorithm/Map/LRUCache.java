package Algorithm.Map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private Map<Integer, Integer> map;
    private int capacity;

    public LRUCache(int capacity) {
        map = new LinkedHashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Integer temp = map.remove(key);
        map.put(key, temp);
        return temp;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
        } else if (map.size() == capacity) {
            map.remove(map.keySet().iterator().next());
        }
        map.put(key, value);
    }
}
