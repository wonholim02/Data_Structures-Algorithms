import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<? super T>> {

    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     */
    public BinarySearchTree() {}

    /**
     * Constructs a new BST.
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BinarySearchTree(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is Null");
        } else {
            for (T input : data) {
                if (input == null) {
                    throw new IllegalArgumentException("Data is Null");
                } else {
                    add(input);
                }
            }
        }
    }

    /**
     * Adds the data to the tree.
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is Null");
        } else {
            BSTNode<T> added = new BSTNode<T>(data);
            root = add(root, added);
        }
    }
    
    /**
     * @param curr current node
     * @param added node to be added
     * @return node to be pointed at
     */
    private BSTNode<T> add(BSTNode<T> curr, BSTNode<T> added) {
        if (curr == null) {
            size++;
            return added;
        } else if (added.getData().compareTo(curr.getData()) < 0) {
            BSTNode<T> left = add(curr.getLeft(), added);
            curr.setLeft(left);
        } else if (added.getData().compareTo(curr.getData()) > 0) {
            BSTNode<T> right = add(curr.getRight(), added);
            curr.setRight(right);
        }
        return curr;
    }

    /**
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is Null");
        }
        BSTNode<T> removed = new BSTNode<T>(null);
        root = remove(root, data, removed);
        size--;
        return removed.getData();
    }
    
    /**
     * @param curr current node
     * @param data node to contain successor information
     * @param removed node to contain removed data information
     * @return node to be pointed at
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    private BSTNode<T> remove(BSTNode<T> curr, T data, BSTNode<T> removed) {
        if (curr == null) {
            throw new NoSuchElementException("Data doees not exist");
        }
        if (curr.getData().compareTo(data) < 0) {
            BSTNode<T> right = remove(curr.getRight(), data, removed);
            curr.setRight(right);
        } else if (curr.getData().compareTo(data) > 0) {
            BSTNode<T> left = remove(curr.getLeft(), data, removed);
            curr.setLeft(left);
        } else {
            removed.setData(curr.getData());
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() != null && curr.getRight() != null) {
                BSTNode<T> dummy = new BSTNode<T>(null);
                BSTNode<T> replace = replaceSuccessor(curr.getRight(), dummy);
                curr.setRight(replace);
                curr.setData(dummy.getData());
            } else {
                if (curr.getLeft() != null) {
                    return curr.getLeft();
                } else if (curr.getRight() != null) {
                    return curr.getRight();
                }
            }
        }
        return curr;
    }
    
    /**
     * @param curr current node
     * @param container node to contain successor information
     * @return the node to be pointed at
     */
    private BSTNode<T> replaceSuccessor(BSTNode<T> curr, BSTNode<T> container) {
        if (curr.getLeft() == null) {
            container.setData(curr.getData());
            return curr.getRight();
        } else {
            BSTNode<T> node = replaceSuccessor(curr.getLeft(), container);
            curr.setLeft(node);
        }
        return curr;
    }

    /**
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is Null");
        }
        BSTNode<T> target = get(root, data);
        return target.getData();
    }
    
    /**
     * @param curr current node
     * @param data target data
     * @return the target node
     * @throws java.util.NoSuchElementException if the data is not in the tree
     */
    private BSTNode<T> get(BSTNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("Data does not exist");
        } else if (curr.getData().compareTo(data) < 0) {
            return get(curr.getRight(), data);
        } else if (curr.getData().compareTo(data) > 0) {
            return get(curr.getLeft(), data);
        } else {
            return curr;
        }
    }

    /**
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is Null");
        }
        return contains(root, data);
    }
    
    /**
     * @param curr current node
     * @param data target data
     * @return if data exists
     */
    private boolean contains(BSTNode<T> curr, T data) {
        if (curr == null) {
            return false;
        } else if (curr.getData().compareTo(data) < 0) {
            return contains(curr.getRight(), data);
        } else if (curr.getData().compareTo(data) > 0) {
            return contains(curr.getLeft(), data);
        } else {
            return true;
        }
    }

    /**
     * @return the pre-order traversal of the tree
     */
    public List<T> preorder() {
        List<T> arr = new ArrayList<>();
        preorder(root, arr);
        return arr;
    }
    /**
     * Resursively adds elements to list in preorder.
     * @param curr current node
     * @param arr list that are being used as storage
     */
    private void preorder(BSTNode<T> curr, List<T> arr) {
        if (curr != null) {
            arr.add(curr.getData());
            preorder(curr.getLeft(), arr);
            preorder(curr.getRight(), arr);
        }
    }

    /**
     * @return the in-order traversal of the tree
     */
    public List<T> inorder() {
        List<T> arr = new ArrayList<>();
        inorder(root, arr);
        return arr;
    }
    
    /**
     * Resursively adds elements to list in inorder.
     * @param curr current node
     * @param arr list that are being used as storage
     */
    private void inorder(BSTNode<T> curr, List<T> arr) {
        if (curr != null) {
            inorder(curr.getLeft(), arr);
            arr.add(curr.getData());
            inorder(curr.getRight(), arr);
        }
    }

    /**
     * @return the post-order traversal of the tree
     */
    public List<T> postorder() {
        List<T> arr = new ArrayList<>();
        postorder(root, arr);
        return arr;
    }
    
    /**
     * @param curr current node
     * @param arr list that are being used as storage
     */
    private void postorder(BSTNode<T> curr, List<T> arr) {
        if (curr != null) {
            postorder(curr.getLeft(), arr);
            postorder(curr.getRight(), arr);
            arr.add(curr.getData());
        }
    }

    /**
     * @return the level-order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> arr = new ArrayList<>();
        Queue<BSTNode<T>> queue = new LinkedList<>();
        if (root == null) {
            return arr;
        }
        queue.add(root);
        BSTNode<T> curr;
        while (!queue.isEmpty()) {
            curr = queue.remove();
            arr.add(curr.getData());
            if (curr.getLeft() != null) {
                queue.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                queue.add(curr.getRight());
            }
        }
        return arr;
    }

    /**
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (this.size == 0) {
            return -1;
        } else {
            return height(root);
        }
    }
    
    /**
     * @param curr current node
     * @return the height of the subtree
     */
    private int height(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        } else {
            int leftH = height(curr.getLeft());
            int rightH = height(curr.getRight());
            if (leftH > rightH) {
                return leftH + 1;
            } else {
                return rightH + 1;
            }
        }
    }
    
    /**
     * Clears the tree.
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * @return the list containing the max data of each level
     */
    public List<T> getMaxDataPerLevel() {
        List<T> arr = new ArrayList<T>();
        BSTNode<T> curr = root;
        getMaxDataPerLevel(curr, arr, 0);
        return arr;
    }
    
    /**
     * Resursively gets max data per level.
     * @param curr current node
     * @param container data container
     * @param count counter for comparison
     */
    private void getMaxDataPerLevel(BSTNode<T> curr, List<T> container, int count) {
        if (curr == null) {
            return;
        }
        if (container.size() == count) {
            container.add((T) curr.getData());
        } else {
            if (curr.getData().compareTo(container.get(count)) > 0) {
                container.set(count, curr.getData());
            } else {
                container.set(count, container.get(count));
            }
        }
        getMaxDataPerLevel(curr.getLeft(), container, (count + 1));
        getMaxDataPerLevel(curr.getRight(), container, (count + 1));
    }

    /**
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        return root;
    }

    /**
     * @return the size of the tree
     */
    public int size() {
        return size;
    }
}
