import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int minimumCost(int n, int[][] highways, int discounts) {
        //pattern: layering  

        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>(); 

        for (int[] e : highways) {
            int u = e[0], v = e[1], w = e[2]; 
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});            
        }

        int[][] dist = new int[n][discounts + 1];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE); 

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2])); //cost
        pq.add(new int[]{0, 0, 0}); // v, discount, cost 

        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); 
            int v = curr[0], d = curr[1], currCost = curr[2]; 

            if (currCost > dist[v][d]) continue;
            if (v == n - 1) return currCost; 


            for (int[] neighbor : adj[v]) {
                int u = neighbor[0], w = neighbor[1], newCost = w + currCost; 

                if (newCost < dist[u][d]) {
                    dist[u][d] = newCost;
                    pq.add(new int[]{u, d, dist[u][d]}); // u, 0, w 
                }

                if (d < discounts) {
                    int disCost = w / 2 + currCost;
                    if (disCost < dist[u][d + 1]) {
                        dist[u][d + 1] = disCost; 
                        pq.add(new int[]{u, d + 1, dist[u][d + 1]}); // u, 1, w / 2 
                    }
                }
            }
        }
        return -1; 

}