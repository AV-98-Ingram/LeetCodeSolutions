void sortColors(int* nums, int numsSize) {
  int num_reds = 0;

  for (int i = 0; i < numsSize; i++)
    if (nums[i] == 0) {
      nums[i] = nums[num_reds];
      nums[num_reds] = 0;
      num_reds++;
    }
  
  int num_reds_whites = num_reds;
  
  for (int i = num_reds; i < numsSize; i++)
    if (nums[i] == 1) {
      nums[i] = nums[num_reds_whites];
      nums[num_reds_whites] = 1;
      num_reds_whites++;
    }  
}
