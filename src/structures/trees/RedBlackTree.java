package structures.trees;

import structures.Pair;
import structures.queues.ListQueue;
import structures.queues.Queue;

import java.util.List;

public class RedBlackTree<K extends Comparable<K>, V> implements BinTree<K,V> {
    private class Node{
        K key;
        V value;
        Node left, right;
        boolean color;

        public Node(){}
        public Node(K key, V val, boolean color){
            this.key = key;
            value = val;
            this.color = color;
        }
    }

    private Node root;
    private int size;

    public RedBlackTree(){}
    /**
     * checks whether binary tree is empty
     * @return whether tree is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * checks size of tree
     * @return size of tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * basic Binary Search Tree accessing
     * @param key passed key
     * @return value associated with key or null if no such value exists
     */
    public V get(K key){
        Node temp = root;
        while (temp != null){
            int cmp = key.compareTo(temp.key);
            System.out.println(temp.key + " " + cmp);
            if      (cmp < 0) temp = temp.left;
            else if (cmp > 0) temp = temp.right;
            else    return temp.value;
        }
        return null;
    }
    /**
     * checks if whether the tree contains a certain key
     * @param key key in question
     * @return whether the key is in the tree
     */
    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    /**
     * inserts a key into the tree with an associated value, if it isnt already there. If it is, the method overrides the value
     * @param key key in question
     * @param value value in question
     */
    @Override
    public void insert(K key, V value) {
        // add red link w/ naive bst insertion, then balance
        root = insert(root, key, value);
        root.color = Black;
    }

    // from Sedgewick's Algorithms 4th edition
    private Node insert(Node h, K key, V val){
        // standard bst insertion first
        //System.out.println(h);
        if (h == null) return new Node(key, val, Red);
        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left = insert(h.left, key, val);
        else if (cmp > 0) h.right = insert(h.right, key, val);
        else              h.value = val;

        // red black balancing

        //right leaning red node
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        // 4 node with left tilt
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        // balanced 4 node
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        return h;
    }

    /**
     * deletes a key from the tree, if it is not on the tree, do nothing
     * @param key key in question
     */
    @Override
    public void delete(K key) {
        System.out.println("to do");
//        if (key == null) throw new IllegalArgumentException("expected value, got null");
//        if (!contains(key)) return;
//
//        if (!isRed(root.left) && !isRed(root.right))
//            root.color = Red;
//
//        if (!isEmpty()) root.color = Black;
    }


    public void bfs(){
        if (root == null) return;
        var queue = new ListQueue<>(root);
        while (!queue.isEmpty()){
            var node = queue.dequeue();
            System.out.print(List.of(node.key,node.value,node.color ? "red" : "black"));
            System.out.print(" ");
            queue.enqueue(node.left);
            queue.enqueue(node.right);
        }
        System.out.println();
    }

    public void goRight(){
        Node temp = root;
        while (temp != null){
            System.out.println(temp.key);
            temp = temp.right;
        }
    }
    //color checking and null handling for convenience
    private boolean isRed(Node n){
        if (n == null) return false;
        return n.color == Red;
    }
    /*
            x                                y
          /   \                            /   \
        a     y        ----------->      x      c
            /   \                      /   \
           b     c                    a     b
     */

    @SuppressWarnings("all")
    private Node rotateLeft(Node x){
        assert isRed(x.right);
        Node y = x.right;
        // change parentage
        x.right = y.left;
        y.left = x;
        // correct colors (x is now left of supposed 3-node, and y replaces x as new root for subtree)
        y.color = x.color;
        x.color = Red;
        return y;
    }

    /*
            x                                y
          /   \                            /   \
        a     y        <------------      x      c
            /   \                      /   \
           b     c                    a     b
     */
    @SuppressWarnings("all")
    private Node rotateRight(Node y){
        assert isRed(y.left);
        Node x = y.left;
        // change parentage
        y.left = x.right;
        x.right = y;
        // correct colors
        x.color = y.color;
        y.color = Red;
        return x;
    }

    // analogous to splitting of 4 node in a 2-3 tree, just implicitly
    private void flipColors(Node y){
        assert !isRed(y);
        assert isRed(y.left);
        assert isRed(y.right);
        y.color = Red;
        y.left.color = Black;
        y.right.color = Black;
    }

    private final static boolean Red = true;
    private final static boolean Black = false;
}
