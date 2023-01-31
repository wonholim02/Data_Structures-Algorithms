import java.util.NoSuchElementException;

public class ArrayStack<T> {
    public static final int INITIAL_CAPACITY = 9;
    private T[] backingArray;
    private int size;

    public ArrayStack() {
        backingArray = (T[]) (new Object[INITIAL_CAPACITY]);
        size = 0;
    }

    /**
     * @param data the data to add to the top of the stack
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid input");
        } else if (size == backingArray.length) {
            T[] newArr = (T[]) (new Object[backingArray.length * 2]);
            for (int i = 0; i < size; i++) {
                newArr[i] = backingArray[i];
            }
            newArr[size] = data;
            backingArray = newArr;
            size++;
        } else {
            backingArray[size] = data;
            size++;
        }
    }

    /**
     * @return the data formerly located at the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("This stack is empty");
        } else {
            T removedData = backingArray[size - 1];
            backingArray[size - 1] = null;
            size--;
            return removedData;
        }
    }

    /**
     * @return the data from the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException("This stack is empty");
        } else {
            return backingArray[size - 1];
        }
    }

    /**
     * @return the backing array of the stack
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * @return the size of the stack
     */
    public int size() {
        return size;
    }
}
