import java.util.NoSuchElementException;
public class DoublyLinkedList<T> {

    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    /**
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Error - Index is Invalid");
        } else if (data == null) {
            throw new IllegalArgumentException("Error - Data is Null");
        }
        DoublyLinkedListNode<T> added = new DoublyLinkedListNode<>(data);
        if (size == 0) {
            head = added;
            tail = added;
        } else if (index == 0) {
            added.setPrevious(null);
            added.setNext(head);
            head.setPrevious(added);
            head = added;
        } else if (index == size) {
            added.setPrevious(tail);
            added.setNext(null);
            tail.setNext(added);
            tail = added;
        } else {
            DoublyLinkedListNode<T> curr;
            if (index < size / 2) {
                curr = head;
                for (int i = 0; i < index; i++) {
                    curr = curr.getNext();
                }
            } else {
                curr = tail;
                for (int i = size - 1; i > index; i--) {
                    curr = curr.getPrevious();
                }
            }
            added.setPrevious(curr.getPrevious());
            added.setNext(curr);
            curr.getPrevious().setNext(added);
            curr.setPrevious(added);
        }
        size++;
    }

    /**
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error - Data is Null");
        }
        DoublyLinkedListNode<T> added = new DoublyLinkedListNode<>(data);
        if (head == null) {
            head = added;
            tail = added;
        } else {
            added.setPrevious(null);
            added.setNext(head);
            head.setPrevious(added);
            head = added;
        }
        size++;
    }

    /**
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error - Data is Null");
        }
        DoublyLinkedListNode<T> added = new DoublyLinkedListNode<>(data);
        if (tail == null) {
            head = added;
            tail = added;
        } else {
            added.setPrevious(tail);
            added.setNext(null);
            tail.setNext(added);
            tail = added;
        }
        size++;
    }

    /**
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Error - Index is Invalid");
        }
        T removed = null;
        if (size == 1) {
            removed = head.getData();
            tail = null;
            head = null;
            size--;
            return removed;
        } else if (index == 0) {
            T data = head.getData();
            head.getNext().setPrevious(null);
            head = head.getNext();
            size--;
            return data;
        } else if (index == size - 1) {
            T data = tail.getData();
            tail.getPrevious().setNext(null);
            tail = tail.getPrevious();
            size--;
            return data;
        } else {
            DoublyLinkedListNode<T> curr;
            if (index < size / 2) {
                curr = head;
                for (int i = 0; i < index; i++) {
                    curr = curr.getNext();
                }
            } else {
                curr = tail;
                for (int i = size - 1; i > index; i--) {
                    curr = curr.getPrevious();
                }
            }
            removed = curr.getData();
            curr.getPrevious().setNext(curr.getNext());
            curr.getNext().setPrevious(curr.getPrevious());
            size--;
            return removed;
        }
    }

    /**
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error - The List is Empty");
        }
        T removed = head.getData();
        if (size == 1) {
            tail = null;
            head = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        size--;
        return removed;
    }

    /**
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error - The List is Empty");
        }
        T removed = tail.getData();
        if (size == 1) {
            tail = null;
            head = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
        }
        size--;
        return removed;
    }

    /**
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Error - Index is Invalid");
        }
        if (index == 0) {
            return  head.getData();
        } else if (index == (size - 1)) {
            return tail.getData();
        } else {
            DoublyLinkedListNode<T> curr;
            if (index < size / 2) {
                curr = head;
                for (int i = 0; i < index; i++) {
                    curr = curr.getNext();
                }
            } else {
                curr = tail;
                for (int i = size - 1; i > index; i--) {
                    curr = curr.getPrevious();
                }
            }
            return curr.getData();
        }
    }
    /**
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Clears the structure.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error - Data is Null");
        } else if (size == 0) {
            throw new NoSuchElementException("Error - Target Data Does Not Exist");
        }
        int index = size - 1;
        DoublyLinkedListNode<T> curr = tail;
        for (int i = size - 1; i > -1; i--) {
            if (curr == null) {
                break;
            }
            if (curr.getData().equals(data)) {
                T removed = curr.getData();
                if (size == 1) {
                    head = null;
                    tail = null;
                } else if (index == 0) {
                    head.getNext().setPrevious(null);
                    head = head.getNext();
                } else if (index == size - 1) {
                    tail.getPrevious().setNext(null);
                    tail = tail.getPrevious();
                } else {
                    curr.getPrevious().setNext(curr.getNext());
                    curr.getNext().setPrevious(curr.getPrevious());
                }
                size--;
                return removed;
            }
            index--;
            curr = curr.getPrevious();
        }
        throw new NoSuchElementException("Error - Target Data Does Not Exist");
    }

    /**
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        T[] arr = (T[]) new Object[size];
        DoublyLinkedListNode<T> curr = head;
        int count = 0;
        while (curr != null) {
            arr[count] = curr.getData();
            curr = curr.getNext();
            count++;
        }
        return arr;
    }

    /**
     * @return the node at the head of the list
     */
    public DoublyLinkedListNode<T> getHead() {
        return head;
    }

    /**
     * @return the node at the tail of the list
     */
    public DoublyLinkedListNode<T> getTail() {
        return tail;
    }

    /**
     * @return the size of the list
     */
    public int size() {
        return size;
    }
}
