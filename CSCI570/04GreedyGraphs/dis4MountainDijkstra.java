import java.util.*;

class Solution {

    private static final long INF = (long) 4e18;

    public long shortestMountainPath(int k, int[][] roads, int[] elev, int s, int t) {

        // Build uphill directed graph:
        // edge goes from lower elevation -> higher elevation
        List<int[]>[] adj = new ArrayList[k];
        for (int i = 0; i < k; i++) adj[i] = new ArrayList<>();

        for (int[] r : roads) {
            int u = r[0], v = r[1], w = r[2];

            if (elev[u] == elev[v]) continue; // should not happen based on problem statement

            if (elev[u] < elev[v]) {
                adj[u].add(new int[]{v, w});
            } else {
                adj[v].add(new int[]{u, w});
            }
        }

        long[] distS = dijkstra(adj, s);
        long[] distT = dijkstra(adj, t);

        long ans = INF;

        // peak = intersection point p
        for (int p = 0; p < k; p++) {
            if (distS[p] >= INF || distT[p] >= INF) continue;
            ans = Math.min(ans, distS[p] + distT[p]);
        }

        return ans >= INF ? -1 : ans;
    }

    private long[] dijkstra(List<int[]>[] adj, int src) {
        int n = adj.length;

        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.add(new long[]{src, 0}); // {node, dist}

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long currDist = curr[1];

            if (currDist > dist[u]) continue;

            for (int[] nei : adj[u]) {
                int v = nei[0], int w = nei[1];

                long newDist = currDist + w;

                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.add(new long[]{v, newDist});
                }
            }
        }

        return dist;
    }
}
