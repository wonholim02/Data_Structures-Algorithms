import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<? super T>> {

    public static final int INITIAL_CAPACITY = 13;
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MinHeap.
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data is null
     */
    public MinHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is invalid");
        }
        backingArray = (T[]) new Comparable[(data.size() * 2) + 1];
        int index = 1;
        for (T datum : data) {
            if (datum == null) {
                throw new IllegalArgumentException("Element in data is invalid");
            }
            backingArray[index] = datum;
            index++;
            size++;
        }
        for (int i = this.size / 2; i > 0; i--) {
            this.downHeap(i);
        }
    }

    /**
     * @param index index to be processed
     */
    private void downHeap(int index) {
        int min;
        T temp;
        while (index * 2 <= size) {
            int leftIdx = index * 2;
            int rightIdx = leftIdx + 1;
            if (rightIdx <= size) {
                if (backingArray[leftIdx].compareTo(backingArray[rightIdx]) < 0) {
                    min = leftIdx;
                } else {
                    min = rightIdx;
                }
            } else {
                min = leftIdx;
            }
            if (backingArray[index].compareTo(backingArray[min]) > 0) {
                temp = backingArray[index];
                backingArray[index] = backingArray[min];
                backingArray[min] = temp;
            } else {
                break;
            }
            index = min;
        }
    }

    /**
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null");
        }
        if (size >= backingArray.length - 1) {
            T[] newBack = (T[]) new Comparable[this.backingArray.length * 2];
            for (int i = 1; i <= size; i++) {
                newBack[i] = backingArray[i];
            }
            backingArray = newBack;
        }
        backingArray[size + 1] = data;
        size++;
        int curr = size;
        T temp;
        while (curr > 1) {
            if (backingArray[curr / 2].compareTo(backingArray[curr]) > 0) {
                temp = backingArray[curr];
                backingArray[curr] = backingArray[curr / 2];
                backingArray[curr / 2] = temp;
            }
            curr = curr / 2;
        }
    }

    /**
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("The heap is empty");
        }
        T removed = backingArray[1];
        if (this.size == 1) {
            backingArray[1] = null;
            size--;
        } else {
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            size--;
            downHeap(1);
        }
        return removed;
    }

    /**
     * @return the minimum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMin() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The heap is empty");
        }
        return backingArray[1];
    }

    /**
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Clears the heap.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        return backingArray;
    }

    /**
     * @return the size of the list
     */
    public int size() {
        return size;
    }
}
