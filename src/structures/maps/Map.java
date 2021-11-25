package structures.maps;

import structures.lists.LinkedList;

public interface Map <K,V>{
    //test
    /**
     * adds a key value pair into the Map, if the key is already bound to a value, then
     * that value is overridden
     * @param key key of entry
     * @param val value of entry
     */
    void put(K key, V val);

    /**
     * deletes a key value pair from the Map, if the key is not bound to a value,
     * it does nothing
     * @param key key of entry to delete
     */
    void delete(K key);

    /**
     * checks whether the map has no key value pairs
     * @return whether the Map is empty
     */
    boolean isEmpty();

    /**
     * gives the number of key value pairs in the Map
     * @return the size of the Map
     */
    int size();

    /**
     * gets the value corresponding to a key within the map;
     * if none is found, null is returned
     * @param key key of entry
     * @return associated value or null
     */
    V get(K key);

    /**
     * provides an collection which contains all key within the Map to iterate over.
     * @return iterable containing all keys in the Map
     */
    Iterable<K> keys();

    /**
     * checks whether the Map contains a value associated with a given key
     * @param key key of entry
     * @return whether there is a value associated with the key
     */
    default boolean contains(K key){
        return get(key) != null;
    }

    /**
     * provides an collection which contains all values within the Map to iterate over.
     * Internally defined to use the keys iterator.
     * @return an iterable containing all values within the Map
     */
    default Iterable<V> values(){
        var xs = new LinkedList<V>();
        for (var k : keys())
            xs.add(get(k));
        return xs;
    }

    /**
     * provides an iterable that contains all mappings within the map. Internally defined to use keys
     * @return an iterable containing all key-value pairs within the Map
     */
    default Iterable<Entry<K,V>> entries(){
        var xs = new LinkedList<Entry<K,V>>();
        for (var k : keys())
            xs.add(new Entry<>(k, get(k)));
        return xs;
    }
}
