import java.util.NoSuchElementException;

public class LinkedListDeque<T> {

    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    /**
     * @param data the data to add to the front of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addFirst(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error - Data is Null");
        }
        LinkedNode<T> added = new LinkedNode<T>(data);
        if (size == 0) {
            tail = added;
        } else {
            added.setNext(head);
            added.setPrevious(null);
            head.setPrevious(added);
        }
        head = added;
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
        LinkedNode<T> added = new LinkedNode<T>(data);
        if (size == 0) {
            head = added;
        } else {
            added.setPrevious(tail);
            added.setNext(null);
            tail.setNext(added);
        }
        tail = added;
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
        T removed = head.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            LinkedNode<T> newHead = head.getNext();
            head = newHead;
            head.setPrevious(null);
        }
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
        T removed = tail.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            LinkedNode<T> newTail = tail.getPrevious();
            tail = newTail;
            tail.setNext(null);
        }
        size--;
        return removed;
    }

    /**
     * @return the data located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Error - deuqe is empty");
        }
        return this.head.getData();
    }

    /**
     * @return the data located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException("Error - deuqe is empty");
        }
        return this.tail.getData();
    }

    /**
     * @return node at the head of the deque
     */
    public LinkedNode<T> getHead() {
        return head;
    }

    /**
     * @return node at the head of the deque
     */
    public LinkedNode<T> getTail() {
        return tail;
    }

    /**
     * @return the size of the deque
     */
    public int size() {
        return size;
    }
}
