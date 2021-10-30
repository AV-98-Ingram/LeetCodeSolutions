int singleNumber(int* nums, int numsSize){
  int result = nums[0];
  
  for (int i = 1; i < numsSize; i++)
    result ^= nums[i];
  return result;
}
