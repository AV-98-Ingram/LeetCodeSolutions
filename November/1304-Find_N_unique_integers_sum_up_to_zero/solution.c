int* sumZero(int n, int* returnSize){
  int * ret = (int*)malloc(sizeof(int) * n);
  int left, right;
  int v = 1;
  
  if (n % 2 == 1) {
    ret[n/2] = 0;
  }
  left = 0;
  right = n-1;  
  while (left < right) {
    ret[left] = v;
    ret[right] = -v;
    v++;
    left++;
    right--;
  }
  *returnSize = n;
  return ret;
}
