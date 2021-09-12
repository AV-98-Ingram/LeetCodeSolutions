int removeElement(int* nums, int numsSize, int val){
  int move_ahead = 0;

  for (int i = 0; i < numsSize; i++) {
    if (nums[i] == val) {
      move_ahead++;     
    } else {
      nums[i-move_ahead] = nums[i];
    }    
  }
  return numsSize - move_ahead;
}
