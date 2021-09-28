int b_search(int x, int lb, int ub);

int mySqrt(int x){
  if (x == 0)
    return 0;
  if (x == 1)
    return 1;
  
  long ub = 2;
  int lb = 1;
  
  while (ub * ub < x) {
    lb = ub;
    ub *= ub;
  }
  if (ub * ub == x)
    return ub;
  return b_search(x, lb, ub);
}

int b_search(int x, int lb, int ub) {
  if (lb == ub-1)
    return lb;
  
  int mid = (ub-lb)/2 + lb;
  long midmid = mid;

  midmid *= midmid;
  if (midmid == x)
    return mid;
  if (midmid < x)
    return b_search(x, mid, ub);
  else
    return b_search(x, lb, mid);    
}
