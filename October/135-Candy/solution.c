#include "stdio.h"
#include "string.h"

int f(int start, int bound, int* rts, int* candies);

int candy(int* ratings, int ratingsSize) {
  int n = ratingsSize;
  int candies[n];
  int ret = 0;
  
  if (n < 2) 
    return 1;
  memset(candies, 0, sizeof(int)*n);
  if (ratings[1] >= ratings[0])
    ret += f(0, n, ratings, candies) + 1;
  for (int i = 1; i < n-1; i++) 
    if (ratings[i-1] >= ratings[i] && ratings[i] <= ratings[i+1]) {
      ret += f(i, n, ratings, candies);
      ret += f(i, 0, ratings, candies) + 1;
    }
  if (ratings[n-2] >= ratings[n-1])
    ret += f(n-1, 0, ratings, candies) + 1;    
  return ret;
}

int f(int start, int bound, int* rts, int* candies) {
  int ret = 0;
  int candy = 2;
  
  candies[start] = 1;
  if (start < bound) {    
    for (int i = start+1; i < bound; i++) {
      if (candies[i] > 0) 
	break;
      if (rts[i] <= rts[i-1])
	break;      
      ret += candy;
      candies[i] = candy++;     
    }
  } else {
    for (int i = start-1; i >= 0; i--) {
      if (rts[i] <= rts[i+1])
	break;
      if (candies[i] > 0) {
	if (candy > candies[i]) 
	  ret += candy - candies[i];
	break;
      }
      ret += candy;
      candies[i] = candy++;
    }
  }
  return ret;
}

int main() {
  int x[5] = {4,2,3,4,1};

  printf("%d\n", candy(x, 5));
}
