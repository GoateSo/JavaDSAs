package structures.graphs;

import structures.Pair;
import structures.lists.LinkedList;

import java.util.HashMap;

/**
 * undirected graph implemented with adjacency lists
 *
 * @param <T> type of node values
 */
public class Graph<T> implements IGraph<T> {
    private final HashMap<T, LinkedList<T>> adjList;
    private int edges;

    public Graph() {
        adjList = new HashMap<>();
        edges = 0;
    }

    @SafeVarargs
    public Graph(Pair<T, T>... edges) {
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

        if (adjList.containsKey(v2)) adjList.get(v2).prep(v1);
        else adjList.put(v2, LinkedList.of(v1));

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
}
