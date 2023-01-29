import java.util.Collection;
import java.util.NoSuchElementException;

public class AVL<T extends Comparable<? super T>> {

    private AVLNode<T> root;
    private int size;

    /**
     * Default constructor
     */
    public AVL() {}

    /**
     * Constructs a new AVL.
     * @param data data to be added
     * @throws java.lang.IllegalArgumentException if data or any  in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is invalid");
        }
        for (T datum: data) {
            if (datum == null) {
                throw new IllegalArgumentException("Data is invalid");
            } else {
                add(datum);
            }
        }
    }

    /**
     * Adds the element to the tree.
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is invalid");
        }
        root = add(root, data);
    }

    /**
     * Helper method for add method
     * @param curr current node
     * @param data data to be added
     * @return resulting node after adding
     */
    private AVLNode<T> add(AVLNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new AVLNode<T>(data);
        }
        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(add(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(add(curr.getRight(), data));
        }
        update(curr);
        return balance(curr);
    }
    /**
     * Update the height and balance factor of the given node
     * @param node current node
     */
    private void update(AVLNode<T> node) {
        int rightH = -1;
        int leftH = -1;
        if (node.getLeft() != null) {
            leftH = node.getLeft().getHeight();
        }
        if (node.getRight() != null) {
            rightH = node.getRight().getHeight();
        }
        int height = 1 + Math.max(leftH, rightH);
        node.setHeight(height);
        node.setBalanceFactor(leftH - rightH);
    }
    /**
     * @param curr current node
     * @return applied resulting node
     */
    private AVLNode<T> balance(AVLNode<T> curr) {
        if (curr.getBalanceFactor() < -1) {
            if (curr.getRight().getBalanceFactor() > 0) {
                curr.setRight(rightRotate(curr.getRight()));
                curr = leftRotate(curr);
            } else {
                curr = leftRotate(curr);
            }
        } else if (curr.getBalanceFactor() > 1) {
            if (curr.getLeft().getBalanceFactor() < 0) {
                curr.setLeft(leftRotate(curr.getLeft()));
                curr = rightRotate(curr);
            } else {
                curr = rightRotate(curr);
            }
        }
        return curr;
    }
    /**
     * @param curr current node
     * @return applied resulting node
     */
    private AVLNode<T> leftRotate(AVLNode<T> curr) {
        AVLNode<T> newRoot = curr.getRight();
        curr.setRight(newRoot.getLeft());
        newRoot.setLeft(curr);
        update(curr);
        update(newRoot);
        return newRoot;
    }
    /**
     * @param curr current node
     * @return applied resulting node
     */
    private AVLNode<T> rightRotate(AVLNode<T> curr) {
        AVLNode<T> newRoot = curr.getLeft();
        curr.setLeft(newRoot.getRight());
        newRoot.setRight(curr);
        update(curr);
        update(newRoot);
        return newRoot;
    }
    /**
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is invalid");
        } else {
            AVLNode<T> node = new AVLNode<T>(null);
            root = remove(root, data, node);
            return node.getData();
        }
    }
    /**
     * Helper method for remove method
     * @param curr current node
     * @param data data to be removed
     * @param dummy dummy node to store data
     * @return applied resulting node
     */
    private AVLNode<T> remove(AVLNode<T> curr, T data, AVLNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Target Data Does Not Exist");
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(remove(curr.getRight(), data, dummy));
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(remove(curr.getLeft(), data, dummy));
        } else {
            dummy.setData(curr.getData());
            size--;
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() != null && curr.getRight() == null) {
                return curr.getLeft();
            } else if (curr.getLeft() == null && curr.getRight() != null) {
                return curr.getRight();
            } else {
                AVLNode<T> temp = new AVLNode<T>(null);
                curr.setLeft(removePredecessor(curr.getLeft(), temp));
                curr.setData(temp.getData());
            }
        }
        update(curr);
        return balance(curr);
    }
    /**
     * Removes predecessor of the node recursively.
     * @param curr current node
     * @param dummy dummy node for storage
     * @return removed node
     */
    private AVLNode<T> removePredecessor(AVLNode<T> curr, AVLNode<T> dummy) {
        if (curr.getRight() == null) {
            dummy.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(removePredecessor(curr.getRight(), dummy));
            update(curr);
            return balance(curr);
        }
    }

    /**
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null");
        } else if (get(root, data) == null) {
            throw new NoSuchElementException("The following data is not in the tree");
        }
        return get(root, data);
    }

    /**
     * Helper method for get method
     * @param curr current node
     * @param data data to get
     * @return the found data if available
     */
    private T get(AVLNode<T> curr, T data) {
        if (curr == null) {
            return null;
        } else {
            T temp = curr.getData();
            if (temp.compareTo(data) < 0) {
                return get(curr.getRight(), data);
            } else if (temp.compareTo(data) > 0) {
                 return get(curr.getLeft(), data);
            } else {
                return curr.getData();
            }
        }
    }

    /**
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is invalid");
        }
        if (size == 0) {
            return false;
        }
        return contains(root, data);
    }

    /**
     * Helper method for contains
     * @param curr current node
     * @param data data to be searched for
     * @return if the tree contains the data
     */
    private boolean contains(AVLNode<T> curr, T data) {
        if (curr == null) {
            return false;
        }
        if (curr.getData().compareTo(data) > 0) {
            return contains(curr.getLeft(), data);
        } else if (curr.getData().compareTo(data) < 0) {
            return contains(curr.getRight(), data);
        }
        return true;
    }

    /**
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (size == 0) {
            return -1;
        } else {
            return root.getHeight();
        }
    }

    /**
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * @return the data in the maximum deepest node or null if the tree is empty
     */
    public T maxDeepestNode() {
        if (size == 0) {
            return null;
        }
        return maxDeepestNode(root).getData();
    }

    /**
     * Helper method for maxDeepestNode.
     * @param curr current node
     * @return the maximum deepest node
     */
    private AVLNode<T> maxDeepestNode(AVLNode<T> curr) {
        if (curr != null) {
            if (curr.getRight() == null && curr.getLeft() == null) {
                return curr;
            } else if (curr.getLeft() == null && curr.getRight() != null) {
                return maxDeepestNode(curr.getRight());
            } else if (curr.getLeft() != null && curr.getRight() == null) {
                return maxDeepestNode(curr.getLeft());
            } else {
                int leftH = curr.getLeft().getHeight();
                int rightH = curr.getRight().getHeight();
                if (leftH > rightH) {
                    return maxDeepestNode(curr.getLeft());
                } else {
                    return maxDeepestNode(curr.getRight());
                }
            }
        }
        return curr;
    }

    /**
     * @param data the data to find the successor of
     * @return the successor of data. If there is no larger data than the
     * one given, return null.
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T successor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Successor not found");
        }
        AVLNode<T> dummy = new AVLNode<>(null);
        return successor(root, data, dummy);
    }

    /**
     * Helper method for successor.
     * @param curr current node
     * @param data data to be used
     * @param dummy node to store data
     * @return the successor of the data or null
     */
    private T successor(AVLNode<T> curr, T data, AVLNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("The does not exist in tree");
        } else if (curr.getData().equals(data)) {
            if (curr.getRight() != null) {
                AVLNode<T> temp = new AVLNode<>(null);
                findSuccessor(curr.getRight(), temp);
                return temp.getData();
            } else {
                return dummy.getData();
            }
        } else if (data.compareTo(curr.getData()) > 0) {
            return successor(curr.getRight(), data, dummy);
        } else {
            dummy.setData(curr.getData());
            return successor(curr.getLeft(), data, dummy);
        }
    }

    /**
     * Finds the sucessor of the node.
     * @param curr current node
     * @param dummy node to store data
     */
    private void findSuccessor(AVLNode<T> curr, AVLNode<T> dummy) {
        if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
        } else {
            findSuccessor(curr.getLeft(), dummy);
        }
    }

    /**
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        return root;
    }

    /**
     * @return the size of the tree
     */
    public int size() {
        return size;
    }
}
