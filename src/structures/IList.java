package structures;

public interface IList<T> extends Iterable<T>{
    boolean isEmpty();
    int size();
    T get(int i);
    T remove(int i);
    T pop();
    void add(T v);
}
