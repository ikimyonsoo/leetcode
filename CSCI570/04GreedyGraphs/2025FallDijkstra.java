    public int minTime(int n, int[][] T, int k) {
        //pattern: layering  

        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>(); 

        // for (int[] e : highways) {
        //     int u = e[0], v = e[1], w = e[2]; 
        //     adj[u].add(new int[]{v, w});
        //     adj[v].add(new int[]{u, w});            
        // }

        int[][] dist = new int[n + 1][k];
        for (int i = 0; i <= n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE); 

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); //cost

        //super node connect

        for (int i = 0; i < k; i++) {
            dist[0][i] = 0;
            pq.add(new int[]{0, dist[0][i], i}); // v, dist, lane 
        }

        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); 
            int v = curr[0], currTime = curr[1], currLane = curr[2]; 

            if (currTime > dist[v][currLane]) continue;
            if (v == n) return currTime;
            
            //driver time for currLane from i to i + 1 
            int driveTime = T[v][currLane];

            //option 1 horizontal move: drive forward to p from v to v + 1 in the current lane 
            if (dist[v][currLane] + driveTime < dist[v + 1][currLane]) {
                dist[v + 1][currLane] = dist[v][currLane] + driveTime; 
                pq.add(new int[]{v + 1, dist[v + 1][currLane], currLane}); 
            }

            int[] neighborLanes = {currLane - 1, currLane + 1};

            //option 2: switch lanes from p of i to neighbors 
            for (int neiLane : neighborLanes) {
                if (neiLane >= 0 && neiLane < k) {
                    int switchTime = 2;
                    if (dist[v][currLane] + switchTime < dist[v][neiLane]) {
                        dist[v][neiLane] = dist[v][currLane] + switchTime;
                        pq.add(new int[]{v, dist[v][neiLane], neiLane}); 
                    }
                }
            }
        }

        return -1; 

}

//we have (n + 1) * k states -> O(E log V) becomes O(nk log nk)
//E >> we have 1 forward edges and 2 lane-switch edges ~= 3 nk 