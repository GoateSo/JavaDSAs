package structures.lists;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.*;

/**
 *
 * @param <T> type of values in List
 */
public final class LinkedList<T> implements IList<T>{

    private final class Node{
        T value;
        Node next;
        Node prev;

        public Node(T v){ value = v; }
    }
    private int length;
    private Node head;
    private Node last;

    /**
     * creates a linked list with a given list of elements
     * @param xs list of elements to insert into list
     */
    @SafeVarargs
    public LinkedList(T... xs){
        if (xs.length == 0) {
            length = 0;
            head = last = null;
            return;
        }
        head = new Node(xs[0]);
        length = xs.length;
        Node cur = head;
        for (int i = 1; i < length; i++){
            var n = new Node(xs[i]);
            cur.next = n;
            n.prev = cur;
            cur = n;
        }
        last = cur;
    }

    /**
     * creates a linked list with a given list of elements
     * @param xs list of elements to insert into list
     * @param <T> type of elements to insert
     * @return a linked list comprised of the given value
     */
    @SafeVarargs
    public static <T> LinkedList<T> of(T... xs){
        return new LinkedList<>(xs);
    }

    /**
     * gets the value at the front of the list
     * @return the head of the list
     */
    public T head(){
        if (isEmpty())
            throw new NoSuchElementException("empty list has no head");
        return head.value;
    }

    /**
     * gets the value at the end of the list
     * @return the tail of the list
     */
    public T last(){
        if (isEmpty())
            throw new NoSuchElementException("empty list has no tail");
        return last.value;
    }

    /**
     * gets the ith element from the linked list by performing a linear search
     * @param i index of element
     * @return ith element
     */
    public T get(int i){
        var n = getNode(i);
        return n.value;
    }

    /**
     * returns the first element of the list
     * @return the first element in the list
     * @see #head
     */
    public T peek(){
        return head();
    }

    /**
     * returns the last element of the list
     * @return the last element in the list
     * @see #last
     */
    public T peekLast(){
        return last();
    }

    /**
     * removes first element from linked list and returns it;
     * @return first element
     */
    @Override
    public T pop(){
        if (isEmpty())
            throw new NoSuchElementException("attempted to remove from empty list");
        var v =  head.value;
        if (length == 1){ head = last = null; }
        else{
            head = head.next;
            head.prev = null;
        }
        length--;
        return v;
    }

    /**
     * removes last element from linked list and returns it;
     * @return last element
     */
    public T popLast(){
        if (isEmpty())
            throw new NoSuchElementException("attempted to remove from empty list");
        var v = last.value;
        if (length == 1){ head = last = null; }
        else{
            last = last.prev;
            last.next = null;
        }
        length--;
        return v;
    }

    /**
     * removes the ith element in the list, and returns it
     * @param i index
     * @return ith element
     */
    @Override
    public T remove(int i){
        var node = getNode(i);
        removeNode(node);
        return node.value;
    }

    /**
     * moves all elements which fulfill a given predicate
     * @param pred predicate to fulfill
     */
    @Override
    public void removeAll(Predicate<T> pred) {
        Node cur = head;
        while (cur != null){
            if (pred.test(cur.value)){
                removeNode(cur);
            }
            cur = cur.next;
        }
    }

    /**
     * checks whether the linked list is empty
     * @return a boolean representing whether the list is empty
     */
    @Override
    public boolean isEmpty(){
        return length == 0;
    }

    /**
     * gives the size of the list
     * @return the size of the list
     */
    @Override
    public int size(){
        return length;
    }

    /**
     * prepends a value to the start of the list
     * @param x value to prepend
     */
    public void prep(T x){
        if (x == null) return;
        var h = new Node(x);
        h.next = head;
        head.prev = h;
        head = h;
        length++;
    }

    /**
     * appends a value to the end of the list
     * @param x value to append
     */
    @Override
    public void add(T x){
        if (x == null) return;
        if (isEmpty()){
            head = last = new Node(x);
        }else{
            var h = new Node(x);
            h.prev = last;
            last.next = h;
            last = h;
        }
        length++;
    }

    /**
     * maps an operation on all elements of the list and returns the resulting list
     *
     * @param f function to apply
     * @return new list
     */
    @Override
    public <U> LinkedList<U> map(Function<T, U> f) {
        var ret = new LinkedList<U>();
        for (var x : this)
            ret.add(f.apply(x));
        return ret;
    }

    /**
     * maps an operation on all elements of the list and flattens the resulting list before returning it
     *
     * @param f function to apply
     * @return new list
     */
    @Override
    public <U> LinkedList<U> flatMap(Function<T, ? extends IList<U>> f) {
        var ret = new LinkedList<U>();
        for (var elem : this)
            for (var newElem : f.apply(elem))
                ret.add(newElem);
        return ret;
    }

    /**
     * returns a list of all elements of the previous list which fulfill a given predicate
     *
     * @param pred given predicate
     * @return new list
     */
    @Override
    public LinkedList<T> filter(Predicate<T> pred) {
        var ret = new LinkedList<T>();
        for (var x : this)
            if (pred.test(x))
                ret.add(x);
        return ret;
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
     * reduces a list using its first element as the initial value, errors if empty
     *
     * @param f binary operation to apply
     * @return result of reduction
     */
    @Override
    public T reduce(BiFunction<T, T, T> f) {
        var accum = head.value;
        var cur = head.next;
        while (cur != null){
            accum = f.apply(accum,cur.value);
            cur = cur.next;
        }
        return accum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList<?> that = (LinkedList<?>) o;
        if (length != that.length) return false;
        var c1 = head;
        var c2 = that.iterator();
        boolean p = true;
        while (c1 != null){
            p = p && (c1.value.equals(c2.next()));
            c1 = c1.next;
        }
        return p;
    }

    @Override
    public int hashCode() {
        int res = 1;
        for (var x : this){
            res = 31 * res + x.hashCode();
        }
        return res;
    }

    @Override
    public String toString() {
        var ret = new StringBuilder("List[");
        var cur = head;
        while (cur != null){
            ret.append(", ").append(cur.value);
            cur = cur.next;
        }
        if (!isEmpty()){ ret.delete(5,7); }
        return ret.append("]").toString();
    }

    @Override public @NotNull Iterator<T> iterator() {
        return new Iterator<>() {
            Node cur = head;
            @Override public boolean hasNext() { return cur != null; }
            @Override public T next() {
                var val = cur.value;
                cur = cur.next;
                return val;
            }
        };
    }

    private void removeNode(Node node){
        if (length == 1){ head = last = null; }
        else{
            Node n = node.next, p = node.prev;
            if (n != null){
                n.prev = node.prev;
            }
            if (p != null){
                p.next = node.next;
            }
            if (node == head){
                head = n;
            }
            if (node == last){
                last = p;
            }
        }
        length--;
    }
    private Node getNode(int i){
        if (i < 0 || i >= length)
            throw new NoSuchElementException("attempted to get element outside list bounds");
        Node cur = head;
        while (i-- > 0){ cur = cur.next; }
        return cur;
    }
}
