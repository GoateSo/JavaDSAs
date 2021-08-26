package algos;

import structures.graphs.IGraph;
import structures.queues.ListStack;

import java.util.HashMap;
import java.util.HashSet;

public class DepthFirstSearch<T> {
    private final HashSet<T> marked;
    private final HashMap<T, T> edgeTo;
    private final IGraph<T> g;

    public DepthFirstSearch(IGraph<T> graph, T start) {
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
        g = graph;
        edgeTo.put(start, start);
        dfs(start);
    }

    private void dfs(T node) {
        marked.add(node);
        for (var neighbor : g.adj(node)) {
            if (!marked.contains(neighbor)) {
                edgeTo.put(neighbor, node);
                dfs(neighbor);
            }
        }
    }

    public int V() {
        return marked.size();
    }

    public boolean contains(T node) {
        return marked.contains(node);
    }

    public Iterable<T> vertices() {
        return marked;
    }

    public Iterable<T> pathTo(T node) {
        if (!contains(node)) throw new IllegalArgumentException("node not in path");
        var stk = new ListStack<T>();
        var cur = node;
        while (!edgeTo.get(cur).equals(cur)) {
            stk.push(cur);
            cur = edgeTo.get(cur);
        }
        stk.push(cur);
        return stk;
    }
}
