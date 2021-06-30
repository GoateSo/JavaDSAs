package structures.lists;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Dynamic array using a resizing array with lengths which are powers of two.
 * impl. doubles capacity of the array when list is full, while halving capacity when list is only quarter full.
 * this is done in order to minimize the number of cases where successive insert + remove operations
 * trigger array resizing, nullifying the amortized constant time benefits.
 * @param <T> type of values in array
 */
public class DynArray<T> implements IList<T> {
    private T[] xs;
    private int size;
    private int capacity;

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
     * creates an empty dynamic array with initial capacity greater or equal to the given length
     * @param len length
     */
    @SuppressWarnings("unchecked")
    public DynArray(int len){
        capacity = 1 << (int)(Math.log(len)/Math.log(2)+1);
        size = 0;
        xs = (T[]) new Object[capacity];
//        for (int i = 0;i < len;  i++){
//            xs[i] = null;
//        }
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
        System.arraycopy(xs, 0, this.xs, 0, xs.length);
    }
    /**
     * creates a dynamic array with initial capacity equal to the next power of 2 > the length of the input
     * @param xs array of values to insert
     * @param <T> type of values to insert
     * @return a dynamic array with the given values
     */
    @SafeVarargs
    public static <T> DynArray<T> of(T... xs){
        return new DynArray<>(xs);
    }

    /**
     * checks whether the array is empty
     * @return whether the array is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * getter for array size
     * @return the size of the dynamic array
     */
    public int size(){
        return size;
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

    /**
     * sets an index within the array to a value
     * @param i index
     * @param v new value
     */
    public void set(int i, T v){
        //if (i < 0 || i >= xs.length) throw new ArrayIndexOutOfBoundsException();
        xs[i] = v;
    }

    /**
     * swaps two indexes within the array
     * @param i first index
     * @param j second index
     */
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
     * maps an operation on all elements of the list and returns the resulting list
     *
     * @param f function to apply
     * @return new list
     */
    @Override
    public <U> IList<U> map(Function<T, U> f) {
        var arr = new DynArray<U>(capacity);
        for (var x : this)
            arr.add(f.apply(x));
        return arr;
    }

    /**
     * maps an operation on all elements of the list and flattens the resulting list before returning it
     *
     * @param f function to apply
     * @return new list
     */
    @Override
    public <U> DynArray<U> flatMap(Function<T, ? extends IList<U>> f) {
        var arr = new DynArray<U>(capacity);
        for (var elem : this)
            for (var nelem : f.apply(elem))
                arr.add(nelem);
        return arr;
    }

    /**
     * returns a list of all elements of the previous list which fulfill a given predicate
     *
     * @param pred given predicate
     * @return new list
     */
    @Override
    public IList<T> filter(Predicate<T> pred) {
        var arr = new DynArray<T>();
        for (var x : this)
            if (pred.test(x))
                arr.add(x);
        return arr;
    }

    /**
     * reduces a list with a binary operation and an initial value going from left to right
     *
     * @param init initial value
     * @param f    binary operation to apply
     * @return result of reduction
     */
    @Override
    public <U> U foldl(U init, BiFunction<U, T, U> f) {
        for (var x : this)
            init = f.apply(init,x);
        return init;
    }

    /**
     * reduces a list using its first element as the initial value, erroring if empty
     *
     * @param f binary operation to apply
     * @return result of reduction
     */
    @Override
    public T reduce(BiFunction<T, T, T> f) {
        if (size < 1) throw new NoSuchElementException();
        var init = xs[0];
        for (int i = 1; i < size; i++) {
            init = f.apply(init, xs[i]);
        }
        return init;
    }

    /**
     * inserts an element in an arbitrary index within the array
     * @param x element to insert
     * @param i index to insert at
     */
    @SuppressWarnings("ManualArrayCopy")
    public void insert(T x, int i){
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
     * moves all elements which fulfill a given predicate
     * @param pred predicate to fulfill
     */
    @Override
    public void removeAll(Predicate<T> pred){
        for (int i = size-1; i >= 0; i-- ){
            if (pred.test(xs[i])){
                remove(i);
                i--;
            }
        }
    }
    /**
     * appends all elements of the array to the end of the underlying array.
     * Resizes to fit the array
     * @param xs array to append
     */
    @SafeVarargs
    public final void addAll(T... xs){
        int newCap = capacity;
        while(xs.length + size > newCap) newCap *= 2; //double new capacity until it can fit the other array
        resize(newCap);
        System.arraycopy(xs, 0, this.xs, size, xs.length);
        size += xs.length;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCap){
        T[] nxs = (T[]) new Object[newCap];
        capacity = newCap;
        System.arraycopy(xs, 0, nxs, 0, size);
        xs = nxs;
    }

    @Override
    public String toString() {
        return "Seq"+Arrays.toString(xs).replaceAll(", null","");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynArray<?> dynArray = (DynArray<?>) o;
        if (size != dynArray.size()) return false;
        boolean p = true;
        for (int i = 0; i < size; i++){
            p = p && (xs[i] == dynArray.get(i));
        }
        return p;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(xs);
        return result;
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
