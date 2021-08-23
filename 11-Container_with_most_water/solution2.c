/* I did not think of this approach honestly.  This solution is
   provided by LeetCode.  What I can learn from this approach? Maybe
   two things: 1. do not easily give up on finding O(n) approaches
   2. how to find the best solution? Starting from a generic case and
   observe how does it change to the next iterative case.

   Does this approach vaguely belong to some category? I will call it
   the Moving Window Pattern.
 */

#define min(x, y)  ((x)>(y)?(y):(x))
int maxArea(int* height, int heightSize) {
  int l = 0, r = heightSize-1;
  int max = (r-l) * min(height[l], height[r]);

  do {
    if (height[l] > height[r])
      r--;
    else
      l++;
    
    int area = (r-l) * min(height[l], height[r]);
    
    if (area > max)
      max = area;
  } while (l < r);
  return max;
}
