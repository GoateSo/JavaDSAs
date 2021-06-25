package structures.lists;

import java.util.Iterator;

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

    public void prep(T x){
        var h = new Node(x);
        h.next = head;
        head.prev = h;
        head = h;
        length++;
    }

    public void add(T x){
        var h = new Node(x);
        h.prev = tail;
        tail.next = h;
        tail = h;
        length++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node cur = head;
            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public T next() {
                var val = cur.value;
                cur = cur.next;
                return val;
            }
        };
    }
}
