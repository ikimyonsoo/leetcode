// LeetCode #
// Approach: 
// Date:

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    private int manhattanDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] {0, 0});

        int totalCost = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int v = curr[0], distV = curr[1];

            if (visited[v]) continue;

            visited[v] = true;
            totalCost += distV;

            for (int u = 0; u < n; u++) {
                if (!visited[u]) {
                    int distU = manhattanDistance(points[v], points[u]);
                    if (distU < dist[u]) {
                        dist[u] = distU;
                        pq.add(new int[]{u, dist[u]});
                }

                }
            }
            return totalCost;


        }

        



        
        
    }
}