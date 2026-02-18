import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        //time and cost as constrains 

        int n = passingFees.length; 
        
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>(); 

        int[][] dist = new int[n][maxTime + 1];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        //Forgot to init 
        dist[0][0] = passingFees[0];

        for (int[] e : edges) {
            int u = e[0], v = e[1], t = e[2];
            adj[u].add(new int[]{v, t});
            adj[v].add(new int[]{u, t});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.add(new int[]{0, 0, passingFees[0]}); // i = v, j = t, val

        while (!pq.isEmpty()) {

            int[] curr = pq.poll(); 
            int v = curr[0], currT = curr[1], currCost = curr[2];

            if (currCost > dist[v][currT]) continue; 
            if (v == n - 1) return currCost; 

            for (int[] neighbor : adj[v]) {
                int u = neighbor[0], neighborT = neighbor[1];
                int newT = neighborT + currT; 
                if (newT > maxTime) continue; 

                int newCost = currCost + passingFees[u];
                if (newCost < dist[u][newT]) {
                    dist[u][newT] = newCost; 
                    pq.add(new int[]{u, newT, newCost});
                }
            }

        }
        return -1;
    }

  public int minCostOp(int maxTime, int[][] edges, int[] passingFees) {
                int n = passingFees.length; 
        
        //1. init adj list 
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>(); 

        for (int[] e : edges) {
            int u = e[0], v = e[1], t = e[2];
            adj[u].add(new int[]{v, t});
            adj[v].add(new int[]{u, t});
        }

        // //2.Reverse dijkstra on time 
        
        // int[] time = new int[n];
        // for (int i = 0; i < n; i++) Arrays.fill(time, Integer.MAX_VALUE);
        // //Forgot to init 
        // time[n - 1] = 0;

        // PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // pq.add(new int[]{n - 1, 0}); // v, t

        // while (!pq.isEmpty()) {

        //     int[] curr = pq.poll(); 
        //     int v = curr[0], vTime = curr[1];

        //     if (vTime > time[v]) continue; 

        //     for (int[] neighbor : adj[v]) {
        //         int u = neighbor[0], uTime = neighbor[1];
        //         int newT = uTime + vTime; 

        //         if (newT < time[u]) {
        //             time[u] = newT; 
        //             pq.add(new int[]{u, newT});
        //         }
        //     }
        // }
        // if (time[0] > maxTime) return -1;

        // //2. Dijkstra on cost (pq runs based on min cost) with prune
        int[] minTime = new int[n];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        //Forgot to init 
        minTime[0] = 0; 
        

        PriorityQueue<int[]> pqCost = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pqCost.add(new int[]{0, 0, passingFees[0]}); // i = v, j = t, k = cost

        while (!pqCost.isEmpty()) {

            int[] curr = pqCost.poll(); 
            int currNode = curr[0], currTime = curr[1], currCost = curr[2];

            // if (currTime > minTime[currNode]) continue; 
            if (currNode == n - 1) return currCost; 

            for (int[] neighbor : adj[currNode]) {
                int u = neighbor[0], newTime = neighbor[1] + currTime;

                if (newTime > maxTime) continue; 

                if (newTime < minTime[u]) {
                    minTime[u] = newTime; 
                    pqCost.add(new int[]{u, newTime, currCost + passingFees[u]});
                }
            }

        }
        return -1; 
    }
}