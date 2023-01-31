import java.util.NoSuchElementException;

public class LinkedStack<T> {
    private LinkedNode<T> head;
    private int size;

    /**
     * @param data the data to add to the top of the stack
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input");
        } else if (size == 0) {
            head = new LinkedNode<T>(data);
            size = 1;
        } else {
            head = new LinkedNode<T>(data, head);
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
            T removedData = head.getData();
            head = head.getNext();
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
            return head.getData();
        }
    }

    /**
     * @return the node at the head of the stack
     */
    public LinkedNode<T> getHead() {
        return head;
    }

    /**
     * @return the size of the stack
     */
    public int size() {
        return size;
    }
}
