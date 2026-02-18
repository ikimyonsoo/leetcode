class Solution {
    public class DisjointSet {
        private int[] size, parent;

        DisjointSet(int n){
            parent = new int[n + 1];
            size = new int[n + 1];
            
            for (int i = 0; i <= n; i++) {
                size[i] = 1;
                parent[i] = i; 
            }

        }
        private int findParent(int u){
            if (parent[u] == u) return u;
            return parent[u] = findParent(parent[u]);

        }

        private boolean unionBySize(int u, int v) {
            int rootU = findParent(u), rootV = findParent(v);
            if (rootU == rootV) return false;
            if (size[rootU] < size[rootV]) {
                parent[rootU] = rootV; 
                size[rootV] += size[rootU];
                
            } else {
                parent[rootV] = rootU; 
                size[rootU] += size[rootV];
            }
            return true;

        }

    }
    
    public int minimumCost(int n, int[][] connections) {
        // Kruskal: sort by weight
        //without sorting, not guranteed to get MST
        Arrays.sort(connections, (a, b) -> Integer.compare(a[2], b[2]));

        DisjointSet ds = new DisjointSet(n);
        int totalCost = 0; 
        int totalEdge = 0; 

        for (int[] e : connections) {
            int u = e[0], v = e[1], w = e[2]; 
            if (ds.unionBySize(u, v)) {
                totalCost += w;
                totalEdge++;

                if (totalEdge == n - 1) return totalCost;
            }
        }
        return -1; 




        
    }
}