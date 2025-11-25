// LeetCode #238 Product of Array Except Self 
// Approach: Brute force O(n^2), then iteration O(n) with sc O(n), then sc O(1)
// Date:20251124 

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        //default value for prefix of nums[0] 
        res[0] = 1; 
        for (int i = 1; i < n; i++){
            // multiply i - 1 to calculate prefix of i 
            res[i] = res[i - 1] * nums[i - 1];
        }

        //default value 
        int suffix = 1;

        for (int i = n - 1; i >= 0; i--){
            res[i] = res[i] * suffix;
            suffix *= nums[i];
            
        }
        return res;
        
        
    }
}