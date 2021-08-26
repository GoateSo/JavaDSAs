package algos;

import structures.graphs.IGraph;
import structures.queues.ListQueue;
import structures.queues.ListStack;

import java.util.HashMap;
import java.util.function.Consumer;

public class BreadthFirstSearch<T> {
    private final HashMap<T, Integer> marked;
    private final IGraph<T> graph;
    private final HashMap<T, T> edgeTo;
    private final T root;

    public BreadthFirstSearch(IGraph<T> g, T node) {
        root = node;
        marked = new HashMap<>();
        edgeTo = new HashMap<>();
        graph = g;
        bfs(node, null);
    }

    public BreadthFirstSearch(IGraph<T> g, T node, Consumer<T> func) {
        root = node;
        marked = new HashMap<>();
        edgeTo = new HashMap<>();
        graph = g;
        bfs(node, func);
    }

    private void bfs(T node, Consumer<T> func) {
        var queue = new ListQueue<>(node);
        marked.put(node, 0);
        edgeTo.put(node, node);

        while (!queue.isEmpty()) {
            var x = queue.dequeue();
            if (func != null) func.accept(x);

            for (var neighbor : graph.adj(x)) {
                if (!marked.containsKey(neighbor)) {
                    marked.put(neighbor, marked.get(x) + 1);
                    edgeTo.put(neighbor, x);
                    queue.enqueue(neighbor);
                }
            }
        }
    }

    public boolean contains(T node) {
        return marked.containsKey(node);
    }

    public int distTo(T node) {
        return marked.get(node);
    }

    public Iterable<T> vertices() {
        return marked.keySet();
    }

    public Iterable<T> pathTo(T node) {
        if (!contains(node)) throw new IllegalArgumentException("node not in path");
        var stk = new ListStack<T>();
        var cur = node;
        do {
            stk.push(cur);
            cur = edgeTo.get(cur);
        } while (distTo(cur) > 0);
        return stk;
    }

}
