package structures.queues;

public interface Deque<T> extends Stack<T>, Queue<T>{
    /**
     * peeks at last element, akin to stack peek
     * @return last element
     */
    default T peekLast(){
        return Stack.super.peek();
    }

    /**
     * peeks at first element, akin to Queue peek
     * @return first element
     */
    default T peek(){
        var x = dequeue();
        enqueue(x);
        return x;
    }
}
