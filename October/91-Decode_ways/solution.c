int * cache;

int numDecodings(char * s) {
  int n = strlen(s);
  int h[n];
  
  cache = h;
  memset(h, -1, sizeof(int) * n);
  return f(s, 0);
}

int f(char * s, int pos) {
  if (s[pos] == 0)
    return 1;
  if (s[pos] == '0')
    return 0;

  if (cache[pos] >= 0)
    return cache[pos];
  
  int d = s[pos] - '0';
  int ret = f(s, pos + 1);

  if (s[pos + 1] != 0) {
    d = d * 10 + s[pos + 1] - '0';
    if (d <= 26)
      ret += f(s, pos + 2);
  }
  cache[pos] = ret;
  return ret;
}


