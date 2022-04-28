/*****************************************************
Coder: Jenny Cha, Wonho Lim
Class: Advanced Computer Science
Date: 2020.11.30
Description: This code implements the LinkedList class.
A LinkedList object consists of nodes that are connected
to each other. Node stores the value and place of 
an element in the LinkedList. 
*******************************************************/
public class LinkedList<E> {

    private Node head;
    private int size;
    
    //This is a constructor of LinkedList. It initializes the two fields as null and 0
    public LinkedList() {
        head = null;
        size = 0;
    }
    
    //This method adds given item to the end of the LinkedList
    public boolean add(E item) { 
        if (size == 0) {
            size++;
            head = new Node(item);
            return true;
        }
        size++;
        Node node = new Node(item);
        Node curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = node;
        return true;
    }
    
    /**This method adds given item to the given index of the LinkedList. It shifts the
    elements given after that index**/
    public void add(int index, E item) { 
        if (size == 0) {
            add(item);
        }
        size++;
        if (index == 0) {
            addFirst(item);
        }

        Node curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        curr.next = new Node(item, curr.next);
    }
    
    /**This method adds given item to the beginning of the LinkedList. It shifts the
    rest of the elements**/
    public void addFirst(E item) {
        if (size == 0) {
            add(item);
        }
        size++;
        Node temp = head;
        head = new Node(item, temp);
    }
    
    //This method adds given item to the end of the LinkedList
    public void addLast(E item) {
        if (size == 0) {
            add(item);
        }
        size++;
        Node node = new Node(item);
        Node curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = node;
    }

    //This method eliminates all the values in the LinkedList and set the size as 0
    public void clear() {
        head = null;
        size = 0;
    }
    
    /**This method checks if the LinkedList contains the item given by the parameter and
    returns true or false (boolean)**/
    public boolean contains(E item) {
        Node curr = head;
        for (int i = 0; i < size - 1; i++) {
            if (curr.value.equals(item)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
   
    //This method finds the element in the given index of the LinkedList and return its value
    public E get(int index) {
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return (E) curr.value;
    }

    //This method returns the first element in the given LinkedList
    public E getFirst() {
        return (E) head.value;
    }
    
    //This method returns the last element in the given LinkedList
    public E getLast() {
        Node curr = head;
        for (int i = 0; i < size - 1; i++) {
            curr = curr.next;
        }
        return (E) curr.value;
    }
    
    /**This method finds the index of the item given by the parameter and returns the index.
    If the given item does not exist, returns -1 instead**/
    public int indexOf(E item) {
        Node curr = head;
        int count = 0; // index number
        while (curr != null && !curr.value.equals(item)) {
            count++;
            curr = curr.next;
        }
        if (count == size) {
            return -1;
        }
        return count;
    }
    
    /**This method finds the last index of the item given by the parameter and returns the index.
    If the given item does not exist, returns -1 instead**/
    public int lastIndexOf(E item) {
        Node curr = head;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (curr.value.equals(item)) {
                index = i;
            }
            curr = curr.next;
        }
        return index;
    }
    //This method removes the last element of the LinkedList
    public E remove() {
        Node curr = head;
        for (int i = 0; i < size - 1; i++) {
            curr = curr.next;
        }
        E removed = (E) curr.value;
        size--;
        curr.next = null;
        return removed;
    }

    //This method removes the element in the given index
    public E remove(int index) {
        E removed = null;
        if (index >= size) {
            return null;
        } else if (index == size - 1) {
            return remove();
        } else if (index == 0) {
            return removeFirst();
        }
        Node curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        removed = (E) curr.next.value;
        size--;
        curr.next = curr.next.next;
        return removed;
    }
    
    /**This method removes the first occurence of the given item; if the given item does not exist,
    returns -1 instead**/ 
    public E remove(E obj) {
        return remove(indexOf(obj));
    } 
    
    //This method removes the first element of the LinkedList
    public E removeFirst() {
        E removed = (E) head.value;
        head = head.next;
        size--;
        return removed;
    }
    
    //This method removes the last element of the LinkedList
    public E removeLast() {
        return remove();
    }

    //This method replaces the value in the given index with the given item
    public E set(int index, E item) {
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        E changed = (E) curr.value;
        curr.value = item;
        return changed;
    }
    
    //This method returns the size(length) of the LinkedList
    public int size() {
        return size;
    }

    //This method helps the programmer print the LinkedList
    public String toString() {
        if (size == 0) {
            return null;
        }
        String print = "[";
        Node curr = head;
        for (int i = 0; i < size - 1; i++) {
            print += curr.value + ", ";
            curr = curr.next;
        }
        print += curr.value + "]";
        return print;
    }
}
