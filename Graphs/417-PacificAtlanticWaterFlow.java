// LeetCode #
// Approach: 
// Date:

import java.util.*;



class Solution {
   private int[] delCol = {0, 1, 0, -1};
   private int[] delRow = {-1, 0, 1, 0};


    public List<List<Integer>> pacificAtlantic(int[][] height) {
      int m = height.length;
      int n = height[0].length; 

      //it is better to use two boolean[][] arrays instead of a Set because
      //1. it is space efficient 
      //1 bit per a cell of bool[][] vs 80-120 bytes per coordinate of set
      //2. time efficiency is not that different 
      //remember we always call if (boolean[r][c]) -> O(1)
      //The extra full grid scan at the end to make res array is negligible
      //in comparison to DFS work
      //and set have much heavier operations >> one set operation contains dozens of array checks checks
      //a.k.a 10,000 Set operations are slower than 40,000 boolean checks

      //The total runtime is dominated by DFS traversal, not the final scan.

      //“Even though we scan the whole grid once, 
      // the DFS already explores most cells. 
      // The boolean array scan is cache-friendly and much cheaper than Set hashing 
      // and object creation, so it’s faster overall.”

      boolean pacific[][] = new boolean[m][n];
      boolean atlantic[][] = new boolean[m][n];

      
      //m and n mixed -> remember m always for row and n always for col 
      for (int i = 0; i < m; i++) {
         dfs(i, 0, height, pacific);
         dfs(i, n - 1, height, atlantic);
      }

      for (int j = 0; j < n; j++) {
         dfs(0, j, height, pacific);
         dfs(m - 1, j, height, atlantic);
      }

      List<List<Integer>> res = new ArrayList<>();
      for (int r = 0; r < m; r++) {
         for (int c = 0; c < n; c++) {
            if (pacific[r][c] && atlantic[r][c]) {
               res.add(Arrays.asList(r, c));
            }
         }
      }
      return res; 
      
   }

   private void dfs(int r, int c, int[][] height, boolean[][] visited) {
      visited[r][c] = true; 

      int m = height.length;
      int n = height[0].length; 

      for (int i = 0; i < 4; i++) {
         int newR = r + delRow[i];
         int newC = c + delCol[i];

         if (isValid(newR, newC, m, n) && !visited[newR][newC] && height[newR][newC] >= height[r][c]) {
            dfs(newR, newC, height, visited);
         }
      }
   }

   private boolean isValid(int r, int c, int m, int n) {
      if (r < 0 || r >= m) return false;
      if (c < 0 || c >= n) return false;
      return true; 
   }
}