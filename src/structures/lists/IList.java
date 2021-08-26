package structures.lists;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;


public interface IList<T> extends Iterable<T>{
    /**
     * checks whether the list is empty
     * @return whether the list is empty
     */
    boolean isEmpty();

    /**
     * gives the size of the list
     * @return the size of the list
     */
    int size();

    /**
     * gets the ith element in the list
     * @param i index
     * @return the ith element
     */
    T get(int i);

    /**
     * removes the ith element in the list, and returns it
     * @param i index
     * @return ith element
     */
    T remove(int i);

    /**
     * moves all elements which fulfill a given predicate
     * @param pred predicate to fulfill
     */
    void removeAll(Predicate<T> pred);

    /**
     * removes the first element from the list and returns it
     * @return the first element
     */
    T pop();

    /**
     * adds a given element to the list
     * @param v element to add
     */
    void add(T v);

    /**
     * checks whether the list contains a given value
     * @param v value
     * @return whether the list contains the value
     */
    boolean contains(T v);

    //combinators and sequencers

    /**
     * maps an operation on all elements of the list and returns the resulting list
     * @param f function to apply
     * @param <U> return type of function
     * @return new list
     */
    <U> IList<U> map(Function<T,U> f);

    /**
     * maps an operation on all elements of the list and returns the resulting list after flattening
     * @param f function to apply
     * @param <U> type contained in function return type
     * @return new list
     */
    <U> IList<U> flatMap(Function<T, ? extends IList<U>> f);

    /**
     * returns a list of all elements of the previous list which fulfill a given predicate
     * @param pred given predicate
     * @return new list
     */
    IList<T> filter(Predicate<T> pred);

    /**
     * reduces a list with a binary operation and an initial value going from left to right
     * @param init initial value
     * @param f binary operation to apply
     * @param <U> type of initial and return value
     * @return result of reduction
     */
    <U> U foldl(U init, BiFunction<U,T,U> f);

    /**
     * reduces a list using its first element as the initial value, errors if empty
     * @param f binary operation to apply
     * @return result of reduction
     */
    T reduce(BiFunction<T,T,T> f);

    /**
     * checks whether there is at least one element in the list that fulfills a condition
     * @param cond condition to fulfill
     * @return whether the condition is fulfilled
     */
    default boolean exists(Predicate<T> cond){
        return foldl(false, (p, x) -> p || cond.test(x));
    }

    /**
     * checks whether all elements in the list that fulfill a condition
     * @param cond condition to fulfill
     * @return whether the condition is fulfilled
     */
    default boolean forall(Predicate<T> cond){
        return foldl(true, (p, x) -> p && cond.test(x));
    }
}
