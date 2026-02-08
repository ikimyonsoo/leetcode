class Solution {
    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> res = new ArrayList<>();
      int n = nums.length;
      backtrack(nums, res, new ArrayList<>(n), 0);
      return res;  
    }

    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> li, int i) {
      int n = nums.length; 
      if (li.size() == nums.length) res.add(new ArrayList<>(li));
      
      for (int j = i; j < n; j++) {
         li.add(nums[i]);
         backtrack(nums, res, li, i+1);
         li.remove(li.size()-1);
      }

    }
}