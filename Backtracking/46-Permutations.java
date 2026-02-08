class Solution {
    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> res = new ArrayList<>();
      int n = nums.length;

      boolean[] visited = new boolean[n];
      backtrack(nums, res, new ArrayList<>(n), visited);
      return res;  
    }

    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> li, boolean[] visited) {
      int n = nums.length; 
      if (li.size() == nums.length) res.add(new ArrayList<>(li));
      
      for (int i = 0 ; i < n; i++) {
         if (visited[i]) continue;
         
         visited[i] = true;
         li.add(nums[i]);
         
         backtrack(nums, res, li, visited);
         
         li.remove(li.size()-1);
         visited[i] = false;

         }
         continue; 
         
      }

    }
}