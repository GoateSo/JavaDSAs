package structures.queues;

import org.jetbrains.annotations.NotNull;
import structures.lists.LinkedList;

import java.util.Iterator;

public class ListQueue<T> implements Queue<T>{
    private LinkedList<T> list;

    @SafeVarargs
    public ListQueue(T... xs) {
        list = LinkedList.of(xs);
    }

    /**
     * inserts an element at the end of the queue
     *
     * @param elem element to insert
     */
    @Override
    public void enqueue(T elem) {
        list.add(elem);
    }

    /**
     * checks whether the queue is empty
     *
     * @return whether the queue is empty
     */
    @Override
    public Boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * removes an element from the front of the queue
     * @return the front queue element
     */
    @Override
    public T dequeue() {
        return list.pop();
    }

    /**
     * gets the element at the front of the queue
     * @return the element at the front of the queue
     */
    @Override
    public T peek() {
        return list.head();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
