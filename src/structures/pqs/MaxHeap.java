package structures.pqs;

import structures.lists.DynArray;

/**
 * Maxheap implemented with resizing array
 *
 * @param <T> the type of comparable elements to be contained within the heap
 */
public class MaxHeap<T extends Comparable<T>> {
    private final DynArray<T> xs; // treat like base 1 array for easier children / parent math
    private int size;

    /**
     * initializes empty heap
     */
    public MaxHeap() {
        int size = 0;
        xs = new DynArray<>(1, null);
    }

    /**
     * Builds binary heap from array of elements by copying all elements to the heap
     * then sinking to map-heapify
     *
     * @param xs elements to build heap from
     */
    @SafeVarargs
    public MaxHeap(T... xs) {
        size = xs.length;
        (this.xs = new DynArray<>(1, null)).addAll(xs);
        for (int i = size / 2; i >= 1; i--) {
            sink(i);
        }
    }

    /**
     * Builds binary heap from array of elements by copying all elements to the heap
     * then sinking to map-heapify
     *
     * @param xs  elements to build heap from
     * @param <T> type of elements in heap
     * @return a new maxheap
     */
    @SafeVarargs
    public static <T extends Comparable<T>> MaxHeap<T> of(T... xs) {
        return new MaxHeap<>(xs);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * gets the size of the heap, subtracts one from underlying array size since it's being treated as a 1 base array
     *
     * @return the size of the heap
     */
    public int size() {
        return size;
    }

    /**
     * inserts element into maxheap
     *
     * @param x element to insert
     */
    public void insert(T x) {
        size++;
        xs.add(x);
        swim(xs.size());
    }

    /**
     * deletes largest (first) element of maxheap and returns it.
     * switches head with last element, removes last from heap, and sinks first element to restore
     * heap property
     *
     * @return the largest elements
     */
    public T delmax() {
        xs.swap(1, size);
        size--;
        T x = xs.pop();
        sink(1);
        return x;
    }

    /**
     * similar to sink, but going up-heap as opposed to down-heap
     *
     * @param i index of swimming
     */
    private void swim(int i) {
        while (i > 1 && less(i / 2, i)) { //while there is a parent and parent < child do
            xs.swap(i, i / 2);
            i /= 2;
        }
    }

    /**
     * swapping smaller node largest child in order to restore heap property
     *
     * @param i index of sinking
     */
    private void sink(int i) {
        while (2 * i <= size) {
            int j = 2 * i;
            if (j < size && less(j, j + 1)) j++; //max of children
            if (less(j, i)) break; //operation done, heap property satisfied
            xs.swap(i, j); //swap to heapify
            i = j; //next layer
        }
    }

    private boolean less(int a, int b) {
        return xs.get(a).compareTo(xs.get(b)) < 0;
    }

}
