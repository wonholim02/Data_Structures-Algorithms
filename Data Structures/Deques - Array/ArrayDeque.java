import java.util.NoSuchElementException;

public class ArrayDeque<T> {

    public static final int INITIAL_CAPACITY = 11;
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayDeque.
     */
    public ArrayDeque() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
        size = 0;
    }

    /**
     * @param data the data to add to the front of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addFirst(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error - Data is Null");
        }
        if (size + 1 > backingArray.length) {
            int len = backingArray.length;
            int newLen = backingArray.length * 2;
            T[] newBackingArray = (T[]) new Object[newLen];
            newBackingArray[0] = data;
            int count = front;
            for (int i = 1; i < len + 1; i++) {
                newBackingArray[i] = backingArray[count % len];
                count++;
            }
            backingArray = newBackingArray;
            front = 0;
        } else {
            front = (front - 1 + backingArray.length) % backingArray.length;
            backingArray[front] = data;
        }
        size++;
    }

    /**
     * @param data the data to add to the back of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addLast(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error - Data is Null");
        }
        if (size + 1 > backingArray.length) {
            int len = backingArray.length;
            int newLen = backingArray.length * 2;
            T[] newBackingArray = (T[]) new Object[newLen];
            int count = front;
            for (int i = 0; i < len; i++) {
                newBackingArray[i] = backingArray[count % len];
                count++;
            }
            newBackingArray[len] = data;
            backingArray = newBackingArray;
            front = 0;
        } else {
            int last = (front + size) % backingArray.length;
            backingArray[last] = data;
        }
        size++;
    }

    /**
     * @return the data formerly located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Error - deuqe is empty");
        }
        T removed = backingArray[front];
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        size--;
        return removed;
    }

    /**
     * @return the data formerly located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("Error - deuqe is empty");
        }
        int lastIndex = mod((front + size - 1), getBackingArray().length);
        T removed = backingArray[lastIndex];
        backingArray[lastIndex] = null;
        size--;
        return removed;
    }

    /**
     * @return the first data
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Error - deuqe is empty");
        }
        return backingArray[front];
    }

    /**
     * @return the last data
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException("Error - deuqe is empty");
        }
        return backingArray[(front + size - 1) % backingArray.length];
    }

    /**
     * @return the backing array of the deque
     */
    public T[] getBackingArray() {
        return backingArray;
    }

    /**
     * @return the size of the deque
     */
    public int size() {
        return size;
    }

    /**
     * @param index  the number to take the remainder of
     * @param modulo the divisor to divide by
     * @return the remainder in its smallest non-negative form
     * @throws java.lang.IllegalArgumentException if the modulo is non-positive
     */
    private static int mod(int index, int modulo) {
        if (modulo <= 0) {
            throw new IllegalArgumentException("The modulo must be positive");
        }
        int newIndex = index % modulo;
        return newIndex >= 0 ? newIndex : newIndex + modulo;
    }
}
