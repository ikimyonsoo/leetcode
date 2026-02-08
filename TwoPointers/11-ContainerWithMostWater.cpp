// LeetCode # 11 Container With Most Water 
// Approach: two pointer 
// Date: 20241209 


class Solution {
public:
    int maxArea(vector<int>& height) {
      int l = 0;
      int r = height.size() - 1; 
      long long area = 0; 
      long long maxArea = 0; 

      while (l < r) {
         long long area = (r - l) * min(height[l], height[r]);
         maxArea = max(area, maxArea);

         if (height[l] < height[r]) {
            l++;
         } else {
            r--;
         }
      }

      return maxArea; 
    }
};
