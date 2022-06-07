package Navigation;

import java.util.*;
import java.util.HashMap;

public class Graph2 {

    private int V;
    private LinkedList<Integer> adj[];
    private List<Integer> path;

    int size = 0;

    // Create a graph
    Graph2(int v) {
        V = v;
        path = new ArrayList<Integer>();
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    public List<Integer> getPath() {
        return path;
    }

    // Add edges to the graph
    void addEdge(int v, int w) {
        adj[v].add(w);

    }

    // BFS algorithm
    void BFS(int s, int d) {

        boolean visited[] = new boolean[V];

        LinkedList<Integer> queue = new LinkedList();

        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();

            path.add(s);

            if (s == d) {
                break;
            }

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }

    }

}
