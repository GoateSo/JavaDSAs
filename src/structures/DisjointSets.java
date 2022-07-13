package structures;


import algos.Misc;

import java.util.HashMap;
import java.util.Objects;

public class DisjointSets<K>{
    private class Node{
        private K parent;
        private int size;
        public Node(K val){
            parent = val;
            size = 1;
        }
    }

    private HashMap<K, Node> mapping;

    private int sets;

    // make forest with N distinct sets only containing 1 element:
    public DisjointSets(K... entries){
        sets = 0;
        mapping = new HashMap<>(entries.length);
        for (var entry : entries){
            if (!mapping.containsKey(entry)) {
                sets++;
                mapping.put(entry, new Node(entry));
            }
        }
    }

    public void add(K val){
        if (!mapping.containsKey(val)) {
            mapping.put(val, new Node(val));
            sets++;
        }
    }

    // union by size
    public void union(K k1, K k2){
        // check for trivial equality
        if (k1 == k2) return;
        // check for keys outside the forest
        if (!mapping.containsKey(k1))
            mapping.put(k1, new Node(k1));
        if (!mapping.containsKey(k2))
            mapping.put(k2, new Node(k2));

        // actual algorithm
        K a = find(k1);
        K b = find(k2);

        // already in same set
        if (Objects.equals(a, b)) return;

        Node roota = mapping.get(a);
        Node rootb = mapping.get(b);

        if (roota.size < rootb.size){
            roota.parent = b;
            rootb.size += roota.size;
        }else{
            rootb.parent = a;
            roota.size += rootb.size;
        }

        sets--;
    }

    // do path compression
    private K find(K key){
        if (!mapping.containsKey(key))
            throw new IllegalArgumentException("key not in forest");

        var node = mapping.get(key);
        if (node.parent != key){ // root check
            node.parent = find(node.parent);
        }
        return node.parent;
    }

    public boolean same(K k1, K k2){
        if (!mapping.containsKey(k1) || !mapping.containsKey(k2))
            throw new IllegalArgumentException("key not in any sets");
        return find(k1).equals(find(k2));
    }


    public static void main(String... args){
        DisjointSets<Integer> dset = new DisjointSets<>(1,2,4,3,5,6);
    }
}
