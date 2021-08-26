package structures.graphs;

public interface IGraph<T> {

    /**
     * add an edge between two vertices
     *
     * @param v1 first vertex
     * @param v2 second vertex
     */
    void addEdge(T v1, T v2);

    /**
     * gets all vertices adjacent to a vertex
     *
     * @param v1 a vertex
     * @return an iterable of adjacent vertices
     */
    Iterable<T> adj(T v1);

    /**
     * gets number of vertices in graph
     *
     * @return number of vertices
     */
    int vertices();

    /**
     * gets number of edges in graph
     *
     * @return number of edges
     */
    int edges();
}
