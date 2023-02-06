import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MaxHeap<T extends Comparable<? super T>> {

    public static final int INITIAL_CAPACITY = 13;
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MaxHeap.
     */
    public MaxHeap() {
        this.size = 0;
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input - null data");
        }
        this.size = data.size();
        backingArray = (T[]) new Comparable[2 * data.size() + 1];
        backingArray[0] = null;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("Invalid Input - null data exist in list");
            } else {
                backingArray[i + 1] = data.get(i);
            }
        }
        for (int j = size / 2; j >= 1; j--) {
            downHeap(j);
        }
    }

    /**
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input - Cannot use null data");
        } else if (isEmpty()) {
            this.size++;
            backingArray[1] = data;
        } else if (backingArray.length == (this.size + 1)) {
            T[] newList  = (T[]) new Comparable[backingArray.length * 2];
            newList[0] = null;
            for (int i = 0; i < this.size; i++) {
                newList[i + 1] = backingArray[i + 1];
            }
            backingArray = newList;
            upHeap(data);
        } else {
            upHeap(data);
        }
    }

    /**
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("This array has 0 elements");
        }
        T removedData = backingArray[1];
        backingArray[1] = backingArray[this.size];
        backingArray[this.size] = null;
        this.size = this.size - 1;
        downHeap(1);
        return removedData;
    }
    
    /**
     * @param upData data to be upheaped
     * @return the result of the upheap
     */
    private T[] upHeap(T upData) {
        this.size++;
        backingArray[this.size] = upData;
        int i = this.size;
        while (i > 1 && backingArray[i].compareTo(backingArray[i / 2]) > 0) {
            T change = backingArray[i / 2];
            backingArray[i / 2] = backingArray[i];
            backingArray[i] = change;
            i = i / 2;
        }
        return backingArray;
    }
    
    /**
     * @param curr the current index we are using.
     * @return the result of the downheap
     */
    private T[] downHeap(int curr) {
        while (curr * 2 <= this.size) {
            int childIndex = curr * 2;
            if (childIndex <= (this.size - 1)
                && (backingArray[childIndex + 1].compareTo(backingArray[childIndex]) > 0)) {
                childIndex = childIndex + 1;
            }
            if (backingArray[curr].compareTo(backingArray[childIndex]) < 0) {
                T change = backingArray[curr];
                backingArray[curr] = backingArray[childIndex];
                backingArray[childIndex] = change;
            } else {
                return backingArray;
            }
            curr = childIndex;
        }
        return backingArray;
    }

    /**
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("This array has 0 elements");
        }
        T max = backingArray[1];
        return max;
    }

    /**
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * Clears the heap.
     */
    public void clear() {
        this.size = 0;
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
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