package hash;

/**
 * Created by shaojiexu on 12/3/17.
 */
public class BasicHashTable<K, V> {

    private HashEntry[] data;
    private int capacity;
    private int size;

    private int size() {
        return  this.size();
    }

    public BasicHashTable(int tableSize) {
        this.capacity = tableSize;
        data = new HashEntry[capacity];
        this.size = 0;
    }

    public V get(K key) {
        return null;
    }
    private class HashEntry<K, V> {

        private  K key;
        private  V value;

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
