package structures.maps;

/**
 * datatype for Map Entries
 * @param <K> type of key
 * @param <V> type of value
 */
public record Entry<K,V>(K key, V val) { }
