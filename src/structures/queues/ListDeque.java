package structures.queues;

import org.jetbrains.annotations.NotNull;
import structures.lists.LinkedList;

import java.util.Iterator;

public class ListDeque<T> implements Deque<T> {
    private LinkedList<T> list;
    @SafeVarargs
    public ListDeque(T... xs){
         list = LinkedList.of(xs);
    }

    /**
     * checks if the deque is empty
     * @return whether the deque is empty
     */
    @Override
    public Boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * adds element to front to deque
     * @param elem element to add
     */
    public void addFirst(T elem){
        list.prep(elem);
    }
    
    /**
     * adds element to rear to deque
     * @param elem element to add
     */
    public void addLast(T elem){
        list.add(elem);
    }

    /**
     * removes element from front of deque
     * @return front element
     */
    public T removeFirst(){
        return list.pop();
    }

    /**
     * removes element from rear of deque
     * @return last element
     */
    public T removeLast(){
        return list.popLast();
    }

    /**
     * peeks at last element, akin to stack peek
     * @return last element
     */
    @Override
    public T peekLast() {
        return list.last();
    }

    /**
     * peeks at first element, akin to Queue peek
     * @return first element
     */
    @Override
    public T peek() {
        return list.head();
    }

    /**
     * inserts an element at the end of the queue
     * @param elem element to insert
     */
    @Override
    public void enqueue(T elem) {
        addFirst(elem);
    }

    /**
     * removes an element from the front of the deque
     * @return the front queue element
     * @see #removeFirst
     */
    @Override
    public T dequeue() {
        return removeFirst();
    }

    /**
     * pushes an element to the end (top) of the deque
     * @param elem element to push
     * @see #removeFirst
     */
    @Override
    public void push(T elem) {
        addLast(elem);
    }

    /**
     * removes and returns the last element of deque (top)
     * @return the top of the stack
     * @see #removeLast
     */
    @Override
    public T pop() {
        return removeLast();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
