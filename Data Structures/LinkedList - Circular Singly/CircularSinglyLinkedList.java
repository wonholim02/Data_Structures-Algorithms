import java.util.NoSuchElementException;
/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Wonho Lim
 * @version 1.0
 * @userid wlim49
 * @GTID 903724333
 *
 * Collaborators: Done individually.
 *
 * Resources: Done individually.
 */
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index detected");
        } else if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data, curr.getNext());
            curr.setNext(newNode);
            size++;
        }
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Data Detected(Null)");
        } else if (size == 0) {
            CircularSinglyLinkedListNode<T> newList = new CircularSinglyLinkedListNode<T>(data);
            head = newList;
            head.setData(data);
            head.setNext(head);
            size++;
        } else {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(head.getData());
            newNode.setData(head.getData());
            newNode.setNext(head.getNext());
            head.setData(data);
            head.setNext(newNode);
            size++;
        }
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Data Detected(Null)");
        } else if (size == 0) {
            head = new CircularSinglyLinkedListNode<T>(data);
            head.setNext(head);
            size++;
        } else {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(head.getData(), head.getNext());
            head.setData(data);
            head.setNext(newNode);
            head = newNode;
            size++;
        }
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index detected");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size) {
            return removeFromBack();
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            T removedData = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
            size--;
            return removedData;
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("This list is empty");
        } else if (size == 1) {
            T removedData = head.getData();
            head = null;
            size = 0;
            return removedData;
        } else {
            T removedData = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
            size--;
            return removedData;
        }
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("This list is empty");
        } else if (size == 1) {
            T removedData = head.getData();
            head = null;
            size = 0;
            return removedData;
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            for (int i = 0; i < size() - 2; i++) {
                curr = curr.getNext();
            }
            T removedData = curr.getNext().getData();
            curr.setNext(head);
            size--;
            return removedData;
        }
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index detected");
        } else if (index == 0) {
            return head.getData();
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            return curr.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        size = 0;
        head = null;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Data Detected(Null)");
        } else if (size == 0) {
            throw new NoSuchElementException("This list is empty");
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            CircularSinglyLinkedListNode<T> temp = null;
            for (int i = 0; i < size - 1; i++) {
                if (curr.getNext().getData().equals(data)) {
                    temp = curr;
                }
                curr = curr.getNext();
            }
            if (temp == null) {
                if (head.getData().equals(data)) {
                    return removeFromFront();
                } else {
                    throw new NoSuchElementException("Data was not found");
                }
            } else {
                T removedData = temp.getNext().getData();
                temp.setNext(temp.getNext().getNext());
                size--;
                return removedData;
            }
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        CircularSinglyLinkedListNode<T> curr = head;
        T[] list = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            list[i] = curr.getData();
            curr = curr.getNext();
        }
        return list;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
