import java.util.NoSuchElementException;

public class ArrayQueue<T> {
    public static final int INITIAL_CAPACITY = 9;
    private T[] backingArray;
    private int front;
    private int size;

    public ArrayQueue() {
        backingArray = (T[]) (new Object[INITIAL_CAPACITY]);
        size = 0;
        front = 0;
    }

    /**
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input");
        } else if (size == backingArray.length) {
            T[] newArr = (T[]) (new Object[backingArray.length * 2]);
            int frontNum = front;
            for (int i = 0; i < size; i++) {
                newArr[i] = backingArray[frontNum % backingArray.length];
                frontNum++;
            }
            newArr[size] = data;
            backingArray = newArr;
            size++;
            front = 0;
        } else {
            backingArray[((front + size) % backingArray.length)] = data;
            size++;
        }
    }

    /**
     * @return the data formerly located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("This queue is empty");
        } else {
            T removedData = backingArray[front];
            backingArray[front] = null;
            front = (front + 1) % backingArray.length;
            size--;
            return removedData;
        }
    }

    /**
     * @return the data located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException("This queue is empty");
        } else {
            return backingArray[front];
        }
    }

    /**
     * @return the backing array of the queue
     */
    public T[] getBackingArray() {
        return backingArray;
    }

    /**
     * @return the size of the queue
     */
    public int size() {
        return size;
    }
}
