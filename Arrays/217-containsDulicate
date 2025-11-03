// LeetCode #217 Contains Duplicate 
// Approach: Brute force O(n^2), then HashSet iteration O(n)
// Date: 20251101

import java.util.HashSet;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }

        return false; 
    }
}