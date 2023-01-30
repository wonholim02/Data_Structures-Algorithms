import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class LinearProbingHashMap<K, V> {

    public static final int INITIAL_CAPACITY = 13;
    public static final double MAX_LOAD_FACTOR = 0.67;
    private LinearProbingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new LinearProbingHashMap.
     */
    public LinearProbingHashMap() {
        this.size = 0;
        this.table = (LinearProbingMapEntry<K, V>[]) new LinearProbingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Constructs a new LinearProbingHashmap with capacity.
     * @param initialCapacity the initial capacity of the backing array
     */
    public LinearProbingHashMap(int initialCapacity) {
        this.size = 0;
        this.table = (LinearProbingMapEntry<K, V>[]) new LinearProbingMapEntry[initialCapacity];
    }

    /**
     * @param key   the key to add
     * @param value the value to add
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     * @throws java.lang.IllegalArgumentException if key or value is null
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value is not valid");
        }
        if ((double) (size + 1) / (double) table.length >= MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }
        int count = 0;
        int delIdx = -1;
        int hash = Math.abs(key.hashCode() % table.length);
        for (int i = 0; i < table.length && count < size; i++) {
            int newIdx = (hash + i) % table.length;
            if (table[newIdx] == null) {
                if (delIdx == -1) {
                    table[newIdx] = new LinearProbingMapEntry<>(key, value);
                    size++;
                    return null;
                } else {
                    table[delIdx] = new LinearProbingMapEntry<>(key, value);
                    size++;
                    return null;
                }
            } else {
                if (table[newIdx].isRemoved()) {
                    if (table[newIdx].getKey().equals(key)) {
                        if (delIdx == -1) {
                            table[newIdx] = new LinearProbingMapEntry<>(key, value);
                            size++;
                            return null;
                        } else {
                            table[delIdx] = new LinearProbingMapEntry<>(key, value);
                            size++;
                            return null;
                        }
                    } else {
                        if (delIdx == -1) {
                            delIdx = newIdx;
                        }
                    }
                } else {
                    count++;
                    if (table[newIdx].getKey().equals(key)) {
                        V target = table[newIdx].getValue();
                        table[newIdx].setValue(value);
                        return target;
                    }
                }
            }
        }
        if (delIdx == -1) {
            table[(hash + size) % table.length] = new LinearProbingMapEntry<>(key, value);
            size++;
            return null;
        } else {
            table[delIdx] = new LinearProbingMapEntry<>(key, value);
            size++;
            return null;
        }
    }

    /**
     * @param key the key to remove
     * @return the value previously associated with the key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key is invalid");
        }
        if (size == 0) {
            throw new NoSuchElementException("The hash table is empty");
        }
        int hash = Math.abs(key.hashCode() % table.length);
        int count = 0;
        V target;
        for (int i = 0; i < table.length && count < size; i++) {
            int index = (hash + i) % table.length;
            if (table[index] != null) {
                if (table[index].isRemoved()) {
                    if (table[index].getKey().equals(key)) {
                        throw new NoSuchElementException("Data not found");
                    }
                } else {
                    count++;
                    if (table[index].getKey().equals(key)) {
                        target = table[index].getValue();
                        table[index].setRemoved(true);
                        size--;
                        return target;
                    }
                }
            } else {
                throw new NoSuchElementException("Data not found");
            }
        }
        throw new NoSuchElementException("Data not found");
    }

    /**
     * @param key the key to search for in the map
     * @return the value associated with the given key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key is invalid");
        }
        if (size == 0) {
            throw new NoSuchElementException("The hash table is empty");
        }
        int hash = Math.abs(key.hashCode() % table.length);
        int count = 0;
        for (int i = 0; i < table.length && count < size; i++) {
            int index = (hash + i) % table.length;
            if (table[index] != null) {
                if (table[index].getKey().equals(key)) {
                    if (!table[index].isRemoved()) {
                        return table[index].getValue();
                    } else {
                        throw new NoSuchElementException("Data not found");
                    }
                } else {
                    if (!table[index].isRemoved()) {
                        count++;
                    }
                }
            } else {
                throw new NoSuchElementException("Data not found");
            }
        }
        throw new NoSuchElementException("Data not found");
    }

    /**
     * @param key the key to search for in the map
     * @return true if the key is contained within the map, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if key is null
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key is invalid");
        }
        if (size == 0) {
            return false;
        }
        int hash = Math.abs(key.hashCode() % table.length);
        int count = 0;
        for (int i = 0; i < table.length && count < size; i++) {
            int index = (hash + i) % table.length;
            if (table[index] != null) {
                if (table[index].isRemoved()) {
                    if (table[index].getKey().equals(key)) {
                        return false;
                    }
                } else {
                    count++;
                    if (table[index].getKey().equals(key)) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * @return the set of keys in this map
     */
    public Set<K> keySet() {
        Set<K> target = new HashSet<>();
        if (size == 0) {
            return target;
        }
        int count = 0;
        for (int i = 0; i < table.length && count < size; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                target.add(table[i].getKey());
                count++;
            }
        }
        return target;
    }

    /**
     * @return list of values in this map
     */
    public List<V> values() {
        List<V> target = new ArrayList<>();
        if (size == 0) {
            return target;
        }
        int count = 0;
        for (int i = 0; i < table.length && count < size; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                target.add(table[i].getValue());
                count++;
                if (count == size) {
                    return target;
                }
            }
        }
        return target;
    }

    /**
     * Resize the backing table.
     * @param length new length of the backing table
     * @throws java.lang.IllegalArgumentException if length is less than thenumber of items in the hash map
     */
    public void resizeBackingTable(int length) {
        if (length < size) {
            throw new IllegalArgumentException("The new length is invalid");
        }
        LinearProbingMapEntry<K, V>[] newTable
                = (LinearProbingMapEntry<K, V>[]) new LinearProbingMapEntry[length];
        int count = 0;
        for (int i = 0; i < table.length && count < size; i++) {
            if (table[i] != null) {
                int hash = Math.abs((table[i].getKey()).hashCode() % length);
                int index = hash;
                int cnt = 0;
                while (newTable[index] != null) {
                    cnt++;
                    index = (hash + cnt) % length;
                }
                if (!table[i].isRemoved()) {
                    count++;
                    newTable[index] = table[i];
                }
            }
        }
        this.table = newTable;
    }

    /**
     * Clears the map.
     */
    public void clear() {
        this.size = 0;
        this.table = (LinearProbingMapEntry<K, V>[]) new LinearProbingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * @return the table of the map
     */
    public LinearProbingMapEntry<K, V>[] getTable() {
        return table;
    }

    /**
     * @return the size of the map
     */
    public int size() {
        return size;
    }
}
