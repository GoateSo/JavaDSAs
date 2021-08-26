package structures.graphs;

import structures.Pair;
import structures.lists.LinkedList;

import java.util.HashMap;

public class Digraph<T> implements IGraph<T> {
    private final HashMap<T, LinkedList<T>> adjList;
    private int edges;

    public Digraph() {
        adjList = new HashMap<>();
        edges = 0;
    }

    @SafeVarargs
    public Digraph(Pair<T, T>... edges) {
        this();
        for (var edge : edges) {
            addEdge(edge.first(), edge.second());
        }
    }

    /**
     * add an edge between two vertices
     *
     * @param v1 first vertex
     * @param v2 second vertex
     */
    @Override
    public void addEdge(T v1, T v2) {
        if (adjList.containsKey(v1)) adjList.get(v1).prep(v2);
        else adjList.put(v1, LinkedList.of(v2));
        edges++;
    }

    /**
     * gets all vertices adjacent to a vertex
     *
     * @param v1 a vertex
     * @return an iterable of adjacent vertices
     */
    @Override
    public Iterable<T> adj(T v1) {
        if (!adjList.containsKey(v1)) return null;
        return adjList.get(v1);
    }

    /**
     * gets number of vertices in graph
     *
     * @return number of vertices
     */
    @Override
    public int vertices() {
        return adjList.size();
    }

    /**
     * gets number of edges in graph
     *
     * @return number of edges
     */
    @Override
    public int edges() {
        return edges;
    }

    public Digraph<T> reverse() {
        var g2 = new Digraph<T>();
        for (var entry : adjList.entrySet()) {
            var list = entry.getValue();
            var node = entry.getKey();

            for (var n : list) {
                g2.addEdge(n, node);
            }
        }
        return g2;
    }
}
