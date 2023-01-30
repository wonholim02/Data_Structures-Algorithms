public class LinearProbingMapEntry<K, V> {

    private K key;
    private V value;
    private boolean removed;

    LinearProbingMapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    boolean isRemoved() {
        return removed;
    }

    void setKey(K key) {
        this.key = key;
    }

    void setValue(V value) {
        this.value = value;
    }

    void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", key.toString(), value.toString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (!(o instanceof LinearProbingMapEntry)) {
            return false;
        } else {
            LinearProbingMapEntry<K, V> that = (LinearProbingMapEntry<K, V>) o;
            return that.getKey().equals(key)
                    && that.getValue().equals(value)
                    && that.isRemoved() == removed;
        }
    }
}