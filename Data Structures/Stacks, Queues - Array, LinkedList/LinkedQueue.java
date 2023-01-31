import java.util.NoSuchElementException;

public class LinkedQueue<T> {
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    /**
     * @param data the data to add to the front of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input");
        } else if (size == 0) {
            LinkedNode<T> newT = new LinkedNode<T>(data);
            head = newT;
            tail = head;
            size = 1;
        } else {
            LinkedNode<T> newT = new LinkedNode<T>(data);
            tail.setNext(newT);
            tail = newT;
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
        } else if (size == 1) {
            T removedData = head.getData();
            head = null;
            tail = null;
            size = 0;
            return removedData;
        } else {
            T removedData = head.getData();
            head = head.getNext();
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
            throw new NoSuchElementException("The queue is empty");
        } else {
            return head.getData();
        }
    }

    /**
     * @return the node at the head of the queue
     */
    public LinkedNode<T> getHead() {
        return head;
    }

    /**
     * @return the node at the tail of the queue
     */
    public LinkedNode<T> getTail() {
        return tail;
    }

    /**
     * @return the size of the queue
     */
    public int size() {
        return size;
    }
}
