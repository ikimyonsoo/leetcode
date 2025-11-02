class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] prev = new int[n];
        int[] curr = new int[n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - i; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    curr[j] = grid[i][j]; 
                    continue; 
                }

                int right = Integer.MAX_VALUE; 
                int down = Integer.MAX_VALUE; 

                if (i + 1 < m) { down = prev[i]; }
                if (j + 1 < n) { right = curr[j+1]; }

                curr[j] = grid[i][j] + Math.min(right, down); 
            }

            prev = curr.clone();
        }

        return prev[0];
        
    }
}