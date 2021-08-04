package structures.queues;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * resizing array implemented queue to achieve all queue operations in amortized constant time
 * @param <T> type of elements in ArrayQueue
 */
public class ArrayQueue<T> implements Queue<T> {
    private T[] arr;
    private int size;
    private int capacity;
    private int head;
    private int tail;


    @SafeVarargs
    @SuppressWarnings("unchecked")
    public ArrayQueue(T... xs) {
        int cap =  1 << (int)(Math.log(xs.length)/Math.log(2)+1);
        arr = (T[]) new Object[cap];
        System.arraycopy(xs, 0, arr, 0, xs.length);
        capacity = cap;
        size = xs.length;
        head = 0;
        tail = size-1;
    }

    /**
     * inserts an element at the end of the queue
     * @param elem element to insert
     */
    @Override
    public void enqueue(T elem) {
        if (elem == null)
            throw new IllegalArgumentException();
        if (size == capacity)
            resize(capacity * 2);
        arr[tail] = elem;
        tail = (tail + 1) % capacity;
        size++;
    }

    /**
     * checks whether the queue is empty
     * @return whether the queue is empty
     */
    @Override
    public Boolean isEmpty() {
        return size == 0;
    }

    /**
     * removes an element from the front of the queue
     * @return the front queue element
     */
    @Override
    public T dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (size <= capacity/4)
            resize(capacity/2);
        T elem = arr[head];
        arr[head] = null;
        size--;
        head = (head + 1) % capacity;
        return elem;
    }

    /**
     * gets the element at the front of the queue
     * @return the element at the front of the queue
     */
    @Override
    public T peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return arr[head];
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Iterator<T> iterator() {
        T[] xs = (T[]) new Object[size];
        for (int i = 0; i < size; i++){
            xs[i] = arr[(head + i)%capacity];
        }
        return new Iterator<>() {
            private int i = 0;
            @Override
            public boolean hasNext() {
                return i < xs.length;
            }

            @Override
            public T next() {
                return xs[i++];
            }
        };
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCap) {
        T[] nArr = (T[]) new Object[newCap];
        int x=0;
        for (int i = head; i < head + size; i++){
            nArr[x] = arr[i % capacity];
            x++;
        }
        head = 0;
        tail = size;
        capacity = newCap;
        arr = nArr;
    }
}
