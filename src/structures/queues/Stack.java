package structures.queues;

public interface Stack<T> extends Iterable<T> {
    /**
     * pushes an element to the top of the stack
     *
     * @param elem element to push
     */
    void push(T elem);

    /**
     * checks if the stack is empty
     *
     * @return whether the stack is empty
     */
    Boolean isEmpty();

    /**
     * removes and returns the top of the stack
     *
     * @return the top of the stack
     */
    T pop();

    /**
     * looks at the top of the stack, but doesn't return it
     *
     * @return the top of the stack
     */
    default T peek() {
        if (isEmpty()) throw new NullPointerException();
        var x = pop();
        push(x);
        return x;
    }
}
