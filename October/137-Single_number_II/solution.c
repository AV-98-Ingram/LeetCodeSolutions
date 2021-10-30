
int singleNumber(int* nums, int numsSize){
  int b0 = 0, b1 = 0;
  
  for (int i = 0; i < numsSize; i++) {
    int new_b0 = (~nums[i] & b0) | (nums[i] & ~(b0 ^ b1));
    int new_b1 = (~nums[i] & b1) | (nums[i] & b0);

    b0 = new_b0;
    b1 = new_b1;
  }
  return b0;
}
