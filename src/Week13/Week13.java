package Week13;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Week13 {
    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int w;
        
        public Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }

        @Override
        public int compareTo(Week13.Edge o) {
            return Integer.compare(this.w, o.w);
        }

        public int other(int v) {
            if (v == v1) {
                return v2;
            } else {
                return v1;
            }
        }
        
    }

    static ArrayList<List<Edge>> graph(int n, List<List<Integer>> edges) {
        ArrayList<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> l : edges) {
            int v1 = 0;
            int v2 = 0;
            int w = 0;
            for (Integer input : l) {
                v1 = v2;
                v2 = w;
                w = input;
            }
            v1--;
            v2--;
            Edge newEdge = new Edge(v1, v2, w);
            graph.get(v1).add(newEdge);
            graph.get(v2).add(newEdge);
        }
        return graph;
    }

    public static int find(int[] uf, int i) {
        int query = i;
        while (query != uf[query]) {
            uf[query] = uf[uf[query]];    // path compression by halving
            query = uf[query];
        }
        return query;
    }

    public static void union(int[] uf, Map<Integer, Integer> hs, int node1, int node2) {
        node1 = find(uf, node1);
        node2 = find(uf, node2);
        if (node1 != node2) {
            if (hs.get(node1) > hs.get(node2)) {
                uf[node2] = node1;
                hs.replace(node1, hs.get(node2) + hs.get(node1));
                hs.remove(node2);
            } else {
                uf[node1] = node2;
                hs.replace(node2, hs.get(node2) + hs.get(node1));
                hs.remove(node1);
            }
        }
    }

    // Prim's (MST) : Special Subtree
    public static int prims(int n, List<List<Integer>> edges, int start) {
        ArrayList<List<Edge>> graph = graph(n, edges);
        start = start - 1;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        HashSet<Integer> hs = new HashSet<>();
        int total = 0;
        for (Edge e : graph.get(start)) {
            pq.add(e);
        }
        hs.add(start);
        while (!pq.isEmpty() && hs.size() < n) {
            Edge e = pq.remove();
            if (hs.contains(e.v1) ^ hs.contains(e.v2)) {
                total += e.w;
                int v = e.v1;
                if (hs.contains(v)) {
                    v = e.v2;
                }
                for (Edge o : graph.get(v)) {
                    if (!hs.contains(o.other(v))) {
                        pq.add(o);
                    }
                }
                hs.add(v);
            }

        }
        return total;
    }

    // Dijkstra: Shortest Reach 2
    public static List<Integer> shortestReach(int n, List<List<Integer>> edges, int s) {
    // Write your code here
        ArrayList<List<Edge>> graph = graph(n, edges);
        Queue<Integer> q = new ArrayDeque<>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        s = s - 1;
        hm.put(s, 0);
        q.add(s);
        while (!q.isEmpty()) {
            int query = q.remove();
            for (Edge e : graph.get(query)) {
                int v = e.other(query);
                int dist = hm.get(query) + e.w;
                if (hm.containsKey(v)) {
                    if (hm.get(v) > dist) {
                        hm.replace(v, dist);
                        q.add(v);
                    }
                } else {
                    hm.put(v, dist);
                    q.add(v);
                }
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (hm.containsKey(i)) {
                if (hm.get(i) != 0) {
                    result.add(hm.get(i));
                } 
            } else {
                result.add(-1);
            }
        }
        return result;
    }

    // Kruskal (MST): Really Special Subtree
    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (int i = 0; i < gFrom.size(); i++) {
            edges.add(new Edge(gFrom.get(i) - 1, gTo.get(i) - 1, gWeight.get(i)));
        }
        int[] uf = new int[gNodes];
        HashMap<Integer, Integer> hs = new HashMap<>();
        for (int i = 0; i < gNodes; i++) {
            hs.put(i, 1);
            uf[i] = i;
        }
        int result = 0;
        while (hs.size() > 1) {
            Edge e = edges.remove();
            if (find(uf, e.v1) != find(uf, e.v2)) {
                result += e.w;
                union(uf, hs, e.v1, e.v2);
            }
        }
        return result;
    }

}