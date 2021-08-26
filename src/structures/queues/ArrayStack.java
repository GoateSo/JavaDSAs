package structures.queues;

import org.jetbrains.annotations.NotNull;
import structures.lists.DynArray;

import java.util.Iterator;

/**
 * wrapper over DynArray which only exposes stack methods
 *
 * @param <T> type of value in array stack
 */
public class ArrayStack<T> implements Stack<T> {
    private final DynArray<T> arr;

    @SafeVarargs
    public ArrayStack(T... xs) {
        arr = DynArray.of(xs);
    }

    /**
     * pushes an element to the top of the stack
     *
     * @param elem element to push
     */
    @Override
    public void push(T elem) {
        arr.add(elem);
    }

    /**
     * checks if the stack is empty
     *
     * @return whether the stack is empty
     */
    @Override
    public Boolean isEmpty() {
        return arr.isEmpty();
    }

    /**
     * removes and returns the top of the stack
     *
     * @return the top of the stack
     */
    @Override
    public T pop() {
        if (isEmpty()) throw new NullPointerException();
        return arr.pop();
    }

    /**
     * looks at the top of the stack, but doesn't return it
     *
     * @return the top of the stack
     */
    @Override
    public T peek() {
        if (isEmpty()) throw new NullPointerException();
        return arr.get(arr.size());
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return arr.iterator();
    }

    @Override
    public String toString() {
        return arr.toString();
    }
}
