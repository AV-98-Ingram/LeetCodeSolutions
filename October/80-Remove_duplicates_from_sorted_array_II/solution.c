

int removeDuplicates(int* nums, int numsSize){
  int count = 1;
  int shift = 0;
  int prev = nums[0];
  
  for (int i = 1; i < numsSize; i++) {
    if (nums[i] == prev) {
      count++;
      if (count > 2) {
	shift++;
	continue;
      }     
    } else {
      prev = nums[i];
      count = 1;      
    }
    nums[i-shift] = nums[i];
  }
  return numsSize - shifts;
}
