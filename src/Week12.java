import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Week12 {

    // Connected Cells in a Grid
    public static List<Integer> cellConnect(List<List<Integer>> matrix, boolean[] check, int query) {
        List<Integer> arr = new ArrayList<>();
        int queryI = query / matrix.get(0).size();
        int queryJ = query % matrix.get(0).size();
        for (int i = queryI - 1; i <= queryI + 1; i++) {
            if (i >= 0 && i < matrix.size()) {
                for (int j = queryJ - 1; j <= queryJ + 1; j++) {
                    int index = i * matrix.get(0).size() + j;
                    if (j >= 0 && j < matrix.get(0).size()) {
                        if (matrix.get(i).get(j) != 0 && !check[index]) {
                            arr.add(index);
                            check[index] = true;
                        }
                    }
                }
            }
        }
        return arr;
    }

    public static int connectedCell(List<List<Integer>> matrix) {
    // Write your code here
        int maxCount = 0;
        
        boolean[] check = new boolean[matrix.size() * matrix.get(0).size()];
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                int index = i * matrix.get(i).size() + j;
                if (matrix.get(i).get(j) == 1 && !check[index]) {
                    check[index] = true;
                    int count = 0;
                    ArrayDeque<Integer> arr = new ArrayDeque<>();
                    arr.add(index);
                    while (!arr.isEmpty()) {
                        int query = arr.remove().intValue();
                        for (Integer n : cellConnect(matrix, check, query)) {
                            arr.add(n);
                        }
                        count++;
                    }
                    maxCount = Math.max(count, maxCount);
                }
            }
        }
        return maxCount;
    }

    // Breadth First Search: Shortest Reach
    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        // Write your code here
        int[] result = new int[n];
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        ArrayList<List<Integer>> graph = graph(n, edges);
        dq.add(s - 1);
        while(!dq.isEmpty()) {
            int query = dq.remove().intValue();
            for (Integer a : graph.get(query)) {
                if (result[a] == 0) {
                    result[a] = result[query] + 6;
                    dq.add(a);
                }
            }
        }
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i != s - 1) {
                if (result[i] == 0) {
                    arr.add(-1);
                } else {
                    arr.add(result[i]);
                }
            }
        }
        return arr;
    }
    
    public static ArrayList<List<Integer>> graph(int n, List<List<Integer>> edges) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            int node1 = 0;
            int node2 = 0;
            for (Integer i : edge) {
                node2 = node1;
                node1 = i.intValue() - 1;
            }
            result.get(node1).add(node2);
            result.get(node2).add(node1);
        }
        return result;
    }

    // Roads and Libraries
    public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
        long cost = 0;
        if (c_lib <= c_road) {
            cost = ((long) c_lib) * n;
        } else {
            int[] uf = new int[n];
            HashMap<Integer, Integer> hs = new HashMap<>();
            long road = 0;
            long lib = 0;
            for (int i = 0; i < n; i++) {
                uf[i] = i;
                hs.put(i, 1);
            }
            for (List<Integer> edge : cities) {
                int node1 = 0;
                int node2 = 0;
                for (Integer i : edge) {
                    node2 = node1;
                    node1 = i.intValue() - 1;
                }
                union(uf, hs, node1, node2);
            }
            Set<Integer> keySet = hs.keySet();
            for (Integer key : keySet) {
                lib++;
                road = road + hs.get(key) - 1;
            }
            cost = lib * c_lib + road * c_road;
        }
        return cost;
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

    public static long roadsAndLibraries2(int n, int c_lib, int c_road, List<List<Integer>> cities) {
        // Write your code here
        long cost = 0;
        if (c_lib < c_road) {
            cost = ((long) c_lib) * n;
        } else {
            ArrayList<List<Integer>> graph = graph(n, cities);
            boolean[] connected = new boolean[n];
            long road = 0;
            long lib = 0;
            do {
                int query = maxDegree(graph, connected);
                ArrayDeque<Integer> dq = new ArrayDeque<>();
                dq.add(query);
                connected[query] = true;
                while(!dq.isEmpty()) {
                    query = dq.remove().intValue();
                    for (Integer a : graph.get(query)) {
                        if (!connected[a]) {
                            road++;
                            connected[a] = true;
                            dq.add(a);
                        }
                    }
                }
                lib++;
            } while (!citiesConnected(connected));
            cost = lib * c_lib + road * c_road;
        }
        return cost;
    }
        
    public static int maxDegree(ArrayList<List<Integer>> graph, boolean[] connected) {
        int degree = -1;
        int node = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).size() > degree && !connected[i]) {
                degree = graph.get(i).size();
                node = i;
            }
        }
        return node;
    }
    
    public static boolean citiesConnected(boolean[]connected) {
        for (int i = 0; i < connected.length; i++) {
            if (!connected[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(10);
        int m = scanner.nextInt();
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> a = new ArrayList<>();
            a.add(scanner.nextInt());
            a.add(scanner.nextInt());
            edges.add(a);
        }
        int s = scanner.nextInt();
        for (Integer a : bfs(n, m, edges, s)) {
            System.out.println(a);
        }
        scanner.close();
    }
}