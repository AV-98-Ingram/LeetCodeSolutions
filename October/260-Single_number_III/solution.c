int* singleNumber(int* nums, int numsSize, int* returnSize){
  int * result = (int*)malloc(sizeof(int) * 2);
  unsigned int mask = 1;
  unsigned int xor = 0;
  
  *returnSize = 2;
  for (int i = 0; i < numsSize; i++)
    xor ^= nums[i];
  mask = xor & ~(xor - 1); // get the left-most set bit in "xor"
  result[0] = 0;
  result[1] = 0;
  for (int i = 0; i < numsSize; i++) {
    if ((nums[i] & mask) != 0) 
      result[0] ^= nums[i];
    else
      result[1] ^= nums[i];
  }
  return result;
}
