import java.util.*;

public class CareerPathOptimizer {
    public static int findMinEffort(int n, List<int[]>[] adj) {
        int[] inDegree = new int[n];
        int[] outDegree = new int[n];
        
        // 1. Calculate degrees to identify Entry (in=0) and Top (out=0) positions
        for (int i = 0; i < n; i++) {
            if (adj[i] == null) continue;
            for (int[] edge : adj[i]) {
                int neighbor = edge[0];
                inDegree[neighbor]++;
                outDegree[i]++;
            }
        }

        // 2. Dijkstra setup
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        // PriorityQueue stores int[] {node_id, current_dist}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // Initialize Entry Level positions with 0 effort
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                dist[i] = 0;
                pq.add(new int[]{i, 0});
            }
        }

        // 3. Dijkstra core logic
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int d = curr[1];

            if (d > dist[u]) continue;
            if (adj[u] == null) continue;

            for (int[] edge : adj[u]) {
                int v = edge[0];
                int weight = edge[1];
                
                if (d + weight < dist[v]) {
                    dist[v] = d + weight;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        // 4. Extract result from Top Positions (sink nodes)
        int minEffort = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0) {
                minEffort = Math.min(minEffort, dist[i]);
            }
        }

        return (minEffort == Integer.MAX_VALUE) ? -1 : minEffort;
    }
}