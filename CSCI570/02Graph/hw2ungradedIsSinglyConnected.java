package CSCI570;

public class hw2ungradedIsSinglyConnected {

    public isCycle(int V, List<Integer>[] adj) {
        boolean[] inStack = new boolean[V];
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!dfs(i, visited, inStack, adj)) return false;
            
        }
        return true; 
    }
    
    private boolean dfs(int v, boolean[] visited, boolean[] inStack, List<Integer> adj
    ) {
        visited[v] = true;
        inStack[v] = true; 

        for (int neighbor : adj[v]) {
            if (!inStack[neighbor] && visited[v]) return true; 

            if (!visited[neighbor]) {
                if (dfs(neighbor, visited, inStack, adj)) return true; 
            }
        }
        inStack[v] = false; 
        return false; 
    }
}
