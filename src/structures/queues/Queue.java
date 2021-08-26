package structures.queues;

public interface Queue<T> extends Iterable<T> {
    /**
     * inserts an element at the end of the queue
     *
     * @param elem element to insert
     */
    void enqueue(T elem);

    /**
     * checks whether the queue is empty
     *
     * @return whether the queue is empty
     */
    Boolean isEmpty();

    /**
     * removes an element from the front of the queue
     *
     * @return the front queue element
     */
    T dequeue();

    /**
     * gets the element at the front of the queue
     *
     * @return the element at the front of the queue
     */
    T peek();
}
