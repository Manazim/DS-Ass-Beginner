package Navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Navigation {

    public static void main(String[] args) {

        HashMap< String, Integer> place = new HashMap< String, Integer>();
        int i = 0;
        Scanner sc = new Scanner(System.in);
        int num_rails = sc.nextInt();
        sc.nextLine();

        Graph2 graph = new Graph2(num_rails);
        String[] trains = new String[num_rails];
        for (int count = 0; count < num_rails; count++) {

            trains[count] = sc.nextLine();
        }

        for (int j = 0; j < trains.length; j++) {

            String[] words = trains[j].split(" => ");
            //gune graph concept kat sini

            if (words.length == 2) {

                for (int k = 0; k < 2; k++) {

                    if (place.containsKey(words[0]) && place.containsKey(words[1])) {
                        graph.addEdge(place.get(words[0]), place.get(words[1]));
                        continue;
                    } else {
                        if (place.containsKey(words[0])) {
                            place.put(words[1], i);
                            graph.addEdge(place.get(words[0]), place.get(words[1]));
                            i++;

                        } else if (place.containsKey(words[1])) {
                            place.put(words[0], i);
                            graph.addEdge(place.get(words[0]), place.get(words[1]));
                            i++;
                        } else {
                            place.put(words[0], i);
                            place.put(words[1], ++i);
                            graph.addEdge(place.get(words[0]), place.get(words[1]));
                            i++;
                        }
                    }
                }

            }
        }

        System.out.println("Enter number of queries");
        int queries = sc.nextInt();
        sc.nextLine();
        String[] path = new String[queries]; //total path source-destination that exist
        ArrayList<String> source = new ArrayList<>();
        ArrayList<String> destination = new ArrayList<>();

        for (int k = 0; k < queries; k++) {
            path[k] = sc.nextLine();
        }

        for (int l = 0; l < path.length; l++) {
            String[] source_destination = path[l].split(" -> "); //split to source and destination
            source.add(source_destination[0]);
            destination.add(source_destination[1]);

            graph.BFS(place.get(source_destination[0]), place.get(source_destination[1]));

            for (int k = 0; k < graph.getPath().size(); k++) {
                for (Map.Entry<String, Integer> entry : place.entrySet()) {

                    if (k == graph.getPath().size() - 1) {
                        if (entry.getValue() == graph.getPath().get(k)) {
                            System.out.print(entry.getKey());
                            break;
                        }
                    } else {
                        if (entry.getValue() == graph.getPath().get(k)) {
                            System.out.print(entry.getKey() + "->");
                            break;
                        }
                    }
                }
            }
        }

        System.out.println("Enter source and detination");
        String user_path = sc.nextLine();

        String[] split = user_path.split(" -> ");

        if (!source.contains(split[0])) {
            System.out.println("This path doesnt start at the starting station!");
        }

        if (!destination.contains(split[1])) {
            System.out.println("This path doesnt end at the destination!");
        }

    }

    public static class Graph2 {

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
}
