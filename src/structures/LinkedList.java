package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @param <T> type of values in List
 */
public class LinkedList<T> implements IList<T>{

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
     * creates empty linked list;
     */
    public LinkedList(){
        length = 0;
        head = last = null;
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
        return head.value;
    }

    /**
     * gets the value at the end of the list
     * @return the tail of the list
     */
    public T last(){
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

    public T peek(){
        if (isEmpty())
            throw new NoSuchElementException("empty list has no head");
        return head.value;
    }

    public T peekLast(){
        if (isEmpty())
            throw new NoSuchElementException("empty list has no tail");
        return last.value;
    }

    /**
     * removes first element from linked list and returns it;
     * @return first element
     */
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

    public T remove(int i){
        var node = getNode(i);
        if (length == 1){ head = last = null; }
        else{
            Node n = node.next, p = node.prev;
            if (n != null){
                n.prev = node.prev;
            }
            if (p != null){
                p.next = node.next;
            }
        }
        length--;
        return node.value;
    }
    /**
     * checks whether the linked list is empty
     * @return a boolean representing whether the list is empty
     */
    public boolean isEmpty(){
        return length == 0;
    }

    public int size(){
        return length;
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
        h.prev = last;
        last.next = h;
        last = h;
        length++;
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

    private Node getNode(int i){
        if (i < 0 || i >= length)
            throw new NoSuchElementException("attempted to get element outside list bounds");
        Node cur = head;
        while (i-- > 0){ cur = cur.next; }
        return cur;
    }
}
