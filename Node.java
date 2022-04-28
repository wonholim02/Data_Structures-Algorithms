/************************************************************
Coder: Jenny Cha, Wonho Lim
Class: Advanced Computer Science
Date: 2020.11.30
Description: This code implements the Node class.
A Node object stores a value of data type E and a reference
to the next Node.
************************************************************/
public class Node<E> {
    public E value; // data
    public Node next; // next Node
    
    /**This method is a constructor of Node with one parameter(E); it makes neext Node null
    It is used to add Nodes to the end of the LinkedList**/
    public Node(E valueIn) {
        this(valueIn, null);
    }
    
    /**This is another of constructor of Node with two parameter(E and Node)
    It is used to add Nodes in the middle of the LinkedList**/
    public Node(E valueIn, Node nextIn) {
        value = valueIn;
        next = nextIn;
    }
}