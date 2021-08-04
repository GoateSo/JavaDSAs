package structures.queues;

import org.jetbrains.annotations.NotNull;
import structures.lists.LinkedList;

import java.util.Iterator;
/**
 * wrapper over LinkedList which only exposes stack methods
 * @param <T> type of value in ListStack
 */
public class ListStack<T> implements Stack<T> {
    private final LinkedList<T> list;

    @SafeVarargs
    public ListStack(T... xs) {
        list = LinkedList.of(xs);
    }

    /**
     * pushes an element to the top of the stack
     *
     * @param elem element to push
     */
    @Override
    public void push(T elem) {
        list.add(elem);
    }

    /**
     * checks if the stack is empty
     *
     * @return whether the stack is empty
     */
    @Override
    public Boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * removes and returns the top of the stack
     *
     * @return the top of the stack
     */
    @Override
    public T pop() {
        return list.popLast();
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
