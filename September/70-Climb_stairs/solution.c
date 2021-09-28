int * cache;

int climbStairs(int n){
  if (n == 1)
    return 1; //DUMB LC compiler can't take zero size array
  
  int c[n-1];
  
  cache = c;
  memset(c, 0, sizeof(int) * (n-1));  
  return f(0, n);
}

int f(int start, int n) {
  if (start + 1 >= n)
    return 1;
  else if (start + 2 >= n)
    return 2;

  if (cache[start] > 0)
    return cache[start];
  
  int steps = f(start + 1, n) + f(start + 2, n);

  cache[start] = steps;
  return steps;
}
