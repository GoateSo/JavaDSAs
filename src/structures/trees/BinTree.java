package structures.trees;

public interface BinTree<K,V>{
    /**
     * checks whether binary tree is empty
     * @return whether tree is empty
     */
    boolean isEmpty();

    /**
     * checks size of tree
     * @return size of tree
     */
    int size();

    /**
     * checks if whether the tree contains a certain key
     * @param key key in question
     * @return whether the key is in the tree
     */
    boolean contains(K key);

    /**
     * inserts a key into the tree with an associated value, if it isnt already there. If it is, the method overrides the value
     * @param key key in question
     * @param value value in question
     */
    void insert(K key, V value);

    /**
     * deletes a key from the tree, if it is not on the tree, do nothing
     * @param key key in question
     */
    void delete(K key);
}
