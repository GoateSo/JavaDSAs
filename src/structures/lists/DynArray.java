package structures.lists;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Dynamic array using a resizing array with lengths which are powers of two.
 * impl. doubles capacity of the array when list is full, while halving capacity when list is only quarter full.
 * this is done in order to minimize the number of cases where successive insert + remove operations
 * trigger array resizing, nullifying the amortized constant time benefits.
 * @param <T> type of values in array
 */
public class DynArray<T> implements Iterable<T> {
    private T[] xs;
    private int size;
    private int capacity;

    /**
     * getter for array size
     * @return the size of the dynamic array
     */
    public int size(){
        return size;
    }
    /**
     * creates an empty dynamic array with initial capacity 2
     */
    @SuppressWarnings("unchecked")
    public DynArray(){
        xs = (T[]) new Object[2];
        capacity = 2;
        size = 0;
    }
    /**
     * creates a dynamic array with initial size len filled with a default value
     * @param len initial size
     * @param v value
     */
    @SuppressWarnings("unchecked")
    public DynArray(int len, T v){
        capacity = 1 << (int)(Math.log(len)/Math.log(2)+1);
        size = len;
        xs = (T[]) new Object[capacity];
        for (int i = 0;i < len;  i++){
            xs[i] = v;
        }
    }
    /**
     * creates a dynamic array with initial capacity equal to the next power of 2 > the length of the input
     * @param xs array of values to insert
     */
    @SuppressWarnings("unchecked")
    public DynArray(T... xs) {
        if (xs == null) throw new NullPointerException("expect an array, got null instead");
        capacity = 1 << (int)(Math.log(xs.length)/Math.log(2)+1);
        size = xs.length;
        this.xs = (T[]) new Object[capacity];
        System.out.println(xs.length+" "+this.xs.length);
        System.arraycopy(xs, 0, this.xs, 0, xs.length);
    }

    /**
     * gets the ith element of the array
     * @param i index
     * @return ith element
     */
    public T get(int i){
        //if (i < 0 || i >= size) throw new ArrayIndexOutOfBoundsException();
        return xs[i];
    }

    public void set(int i, T v){
        //if (i < 0 || i >= xs.length) throw new ArrayIndexOutOfBoundsException();
        xs[i] = v;
    }

    public void swap(int i, int j){
        T temp = xs[i];
        xs[i] = xs[j];
        xs[j] = temp;
    }

    /**
     * adds an element to the end of the array
     * if the array is full, it doubles the size
     * @param x value to append
     */
    public void add(T x){
        if (x == null) throw new NullPointerException("expected element to insert, got null instead");
        if (size >= capacity) resize(capacity * 2); //double size if full
        xs[size++] = x;
    }

    /**
     * inserts an element in an arbitrary index within the array
     * @param x element to insert
     * @param i index to insert at
     */
    public void insert(T x,int i){
        //if (i < 0 || i > size) throw new ArrayIndexOutOfBoundsException();
        if (x == null) throw new NullPointerException("expected element to insert, got null instead");
        if (size >= capacity) resize(capacity * 2); //double size if full
        for (int j = size; j > i; j--){
            xs[j] = xs[j-1];
        }
        xs[i] = x;
    }

    /**
     * removes last element from an array and returns it.
     * if the array is near empty (capacity / 4), the array is shrank by half
     * @return last element
     */
    public T pop(){
        if (size == 0) throw new NullPointerException("attempted to remove from empty array");
        if (size <= capacity/4) resize(capacity / 2); //halve if near empty
        var x = xs[--size];
        xs[size] = null;
        return x;
    }

    /**
     * removes the ith element and returns it.
     * if the array is near empty (capacity / 4), the array is shrank by half
     * @param i index
     * @return ith element
     */
    public T remove(int i){
        if (size == 0) throw new NullPointerException("attempted to remove from empty array");
        if (size <= capacity/4) resize(capacity / 2); //halve if near empty
        var x= xs[i];
        for (int j = i; i < size - 1; j++){
            xs[j] = xs[j+1];
        }
        xs[--size] = null;
        return x;
    }

    /**
     * appends all elements of the array to the end of the underlying array.
     * Resizes to fit the array
     * @param xs array to append
     */
    public void append(T[] xs){
        int newCap = capacity;
        while(xs.length + size > newCap) newCap *= 2; //double new capacity until it can fit the other array
        resize(newCap);
        System.arraycopy(xs, 0, this.xs, size, xs.length);
        size += xs.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(xs).replaceAll(", null","");
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCap){
        T[] nxs = (T[]) new Object[newCap];
        capacity = newCap;
        System.arraycopy(xs, 0, nxs, 0, size);
        xs = nxs;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int i = 0;
            @Override public boolean hasNext() {
                return i < size;
            }
            @Override public T next() {
                return xs[i++];
            }
        };
    }
}
