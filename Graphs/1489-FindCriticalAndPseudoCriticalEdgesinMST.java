// LeetCode #
// Approach: 
// Date:

import java.util.Comparator;

class Solution {
    
    class DisjointSet {
        private int[] rank;
        private int[] parent; 
        private int[] size; 

        public DisjointSet(int n) {
            rank = new int[n + 1];
            parent = new int[n + 1];
            size = new int[n + 1];

            for (int i = 0; i <= n; i++) parent[i] = i; 

            Arrays.fill(size, 1); 
        
        }

        //help to determine if two nodes u and v are in the same component or not 
        public boolean find(int u, int v) {
            return findParent(u) == findParent(v); 
        }

        public void unionByRank(int u, int v) {
            int rootU = findParent(u), rootV = findParent(v); 

            if (rootU == rootV) return; 

            if (rank[rootV] > rank[rootU]) union(rootV, rootU); 

            else {
                union(rootU, rootV); 

                if (rank[rootU] == rank[rootV]) rank[rootU]++; 
            }
            
        }

        public boolean unionBySize(int u, int v) {
            int rootU = findParent(u), rootV = findParent(v); 

            if (rootU == rootV) return false; 

            if (size[rootV] > size[rootU]) { 
                union(rootV, rootU); 
                size[rootV] += size[rootU]; 
            }

            else {
                union(rootU, rootV); 
                size[rootU] += size[rootV]; 
            }
            return true;         
            
        }

        private void union(int u, int v) {
            parent[v] = u; 
        }
        
        private int findParent(int u) {
            if (u == parent[u]) return u; 
            //parent[7] = 4; 
            //parent[6] = 4; 
            return parent[u] = findParent(parent[u]); 

            //this is same as parent[u]
            //int root = findParent(parent[u]);
            //parent[u] = root;   // path compression
            //return root;
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
       int m = edges.length; 

       int[][] E = new int[m][4];

       //include edge number to the list {u, v, w, e}

       for (int i = 0; i < m; i++) {
        E[i][0] = edges[i][0];
        E[i][1] = edges[i][1];
        E[i][2] = edges[i][2];
        E[i][3] = i;
        }

        Arrays.sort(E, Comparator.comparingInt(a -> a[2]));

        int MST = modifiedKruskal(n, E, -1, -1); // no skipping no picking 

        List<Integer> critical = new ArrayList<>(); 
        List<Integer> pseudo = new ArrayList<>(); 

        for (int i = 0; i < m; i++) {
            int pickMST = modifiedKruskal(n, E, i, -1);
            int skipMST = modifiedKruskal(n, E, -1, i); 

            if (skipMST > MST) critical.add(E[i][3]);
            else if (pickMST == MST) pseudo.add(E[i][3]); 
        }


        return Arrays.asList(critical, pseudo); 

    }

    private int modifiedKruskal(int n, int[][] edges, int pick, int skip) {
        DisjointSet ds = new DisjointSet(n);
        int m = edges.length; 
        int totalWeight = 0, count = 0; 

    
        //pick e to find pseudo critical edges
        //force include one e first by
        //run kruskal's from e a.k.a. add e before starting kruskals tho it is not the cheapest 
        //if totalWeight == originalMST, it is pseudo critical 
        if (pick != -1) {
            int u = edges[pick][0], v = edges[pick][1], w = edges[pick][2]; 

            if (ds.unionBySize(u, v)) {
                totalWeight += w; 
                count++; 

            }
        }

        //skip e to find critical edges 
        //run kruskal's without e. See if the totalWeight goes up or not
        //if totalWeight > originalMST, it is critical 
        for (int i = 0; i < m; i++) {
            int u = edges[i][0], v = edges[i][1], w = edges[i][2];
            if (i == skip) continue; 

            if (ds.unionBySize(u, v)) {
                totalWeight += w; 
                count++; 
            }
        }

        return (count == n - 1) ? totalWeight : Integer.MAX_VALUE; 

    }

    public int standardKruskal(int n, int[][] edges) {
    // 1. Sort edges by weight
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2])); 

        DisjointSet ds = new DisjointSet(n);
        int mstWeight = 0;
        int edgesCount = 0;

        for (int[] edge : edges) {
            // 2. Try to connect nodes. If they were already connected, skip (cycle).
            if (ds.unionBySize(edge[0], edge[1])) {
                mstWeight += edge[2];
                edgesCount++;
            }
        }

        // A valid MST must have exactly n - 1 edges
        return (edgesCount == n - 1) ? mstWeight : -1;
    }
    
}