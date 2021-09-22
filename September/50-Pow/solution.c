double myPow(double x, int n){
  if (n == 0)
    return 1.0;
  if (x == 1.0)
    return 1.0;
  if (x == -1.0)
    return n % 2 == 0 ? -x : x;
  if (x == 0.0)
    return 0.0;
  
  double result = 1.0;
  long nn = n > 0 ? n : -((long)n);
  
  while (nn > 0)
    if (nn % 2 == 0) {
      x *= x;
      nn = nn / 2;
    } else {
      result *= x;
      nn--;
    }
  return n > 0 ? result : 1/result;
}
