package structures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @param <T> type of values in List
 */
public class LinkedList<T> implements Iterable<T>{

    private class Node{
        T value;
        Node next;
        Node prev;

        public Node(T v){ value = v; }
    }
    int length;
    Node head;
    Node tail;

    /**
     * creates empty linked list;
     */
    public LinkedList(){
        length = 0;
        head = tail = null;
    }

    /**
     * creates a linked list with a given list of elements
     * @param xs list of elements to insert into list
     */
    @SafeVarargs
    public LinkedList(T... xs){
        head = new Node(xs[0]);
        length = xs.length;
        Node cur = head;
        for (int i = 1; i < length; i++){
            var n = new Node(xs[i]);
            cur.next = n;
            n.prev = cur;
            cur = n;
        }
        tail = cur;
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
        return head.value;
    }

    /**
     * gets the value at the end of the list
     * @return the tail of the list
     */
    public T tail(){
        return tail.value;
    }

    /**
     * gets the ith element from the linked list by performing a linear search
     * @param i index of element
     * @return ith element
     */
    public T get(int i){
        if (i < 0 || i >= length)
            throw new NoSuchElementException("attempted to get element outside list bounds");
        Node cur = head;
        while (i-- > 0){ cur = cur.next; }
        return cur.value;
    }

    public T peek(){
        if (length < 1)
            throw new NoSuchElementException("empty list has no head");
        return head.value;
    }

    public T peekLast(){
        if (length < 1)
            throw new NoSuchElementException("empty list has no tail");
        return tail.value;
    }

    /**
     * removes first element from linked list and returns it;
     * @return first element
     */
    public T pop(){
        if (length < 1)
            throw new NoSuchElementException("attempted to remove from empty list");
        var v =  head.value;
        if (length == 1){ head = tail = null; }
        else{
            head = head.next;
            head.prev = null;
        }
        return v;
    }

    /**
     * removes last element from linked list and returns it;
     * @return last element
     */
    public T popLast(){
        if (length < 1)
            throw new NoSuchElementException("attempted to remove from empty list");
        var v = tail.value;
        if (length == 1){ head = tail = null; }
        else{
            tail = tail.prev;
            tail.next = null;
        }
        return v;
    }

    /**
     * checks whether the linked list is empty
     * @return a boolean representing whether the list is empty
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     * prepends a value to the start of the list
     * @param x value to prepend
     */
    public void prep(T x){
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
    public void add(T x){
        var h = new Node(x);
        h.prev = tail;
        tail.next = h;
        tail = h;
        length++;
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

    @Override public Iterator<T> iterator() {
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
}
