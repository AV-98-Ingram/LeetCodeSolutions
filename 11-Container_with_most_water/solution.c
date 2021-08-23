/*  
      Basic algorithm:
      for each (i, a_i)
        for each (j, a_j)  j > i
           max(curr_max, area((i, a_i), (j, a_j)))

      Optimization:
      1. When a_i == 0, skip
      2. Let m be the current max.  For (i, a_i), let d = m / a_i.  We
         do not need to look at any such (j, a_j) that j - i < d,
         since the area will not be greater than m.  Note that when
         fix (i, a_i), the maximal height of the vertical bar is a_i.
 */
#define min(x, y)  ((x)>(y)?(y):(x))

int maxArea(int* height, int heightSize) {
  int max = 0;
  
  for (int i = 0; i < heightSize; i++) {
    if (height[i] == 0)
      continue;
    
    int j_lb = max / height[i] + i;
    
    // in reverse order as the longer x-coord length the more possible to find the max
    for (int j = heightSize-1; j > j_lb; j--) { 
      if (height[j] == 0)
	continue;
      
      int area = (j - i) * min(height[i], height[j]);
      
      if (area > max)
	max = area;
    }
  }
  return max;
}
