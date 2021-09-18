/*  Intuition: to find the first missing positive, we focus on the
    sequence [0, n), where n >= 0.  
    
    To keep memory space constant, we re-use the given array.  We scan
    the numbers and swap the number x to the cell [x-1] if x <=
    numsSize.  Note that if all numbers are greater than numsSize, the
    first missing positive is 1.
    
    Then we just need a second round scan to find the first cell that
    has not been placed "correctly".
    
    I didn't think of this solution because I missed the fact that
    there must be n (n >= 0) numbers in the given array are in the
    range [1, n], otherwise the missive positive is 1.

    I'm surprised that I didn't write the swapping right.  I wrote the
    way that the scan keeps proceed no matter it makes a swap or not.
    However, for example, given [3 4 -1 1], at index 0, it swaps 3 and
    -1, obtaining [-1, 4, 3, 1]; then at index 1, it swaps 4 and 1,
    obtaining [-1, 1, 3, 4]. So on and so forth 1 will never get the
    chance to be swapped to the right place.  The right way to do it
    is if swapping, the scan shall not proceed.  This is because the
    newly swapped number must also be tested and swapped if necessary.

      
    Finally, be careful with infinite swap, e.g., [1, 1].
 */
void swap_if_need(int *nums, int numsSize, int i);

int firstMissingPositive(int* nums, int numsSize) {
  for (int i = 0; i < numsSize;)
    if (swap_if_need(nums, numsSize, i))
      i++;
  
  int i = 0;
  for (; i < numsSize; i++) {
    if (nums[i] != i + 1)
      break;
  }
  return i + 1;
}

char swap_if_need(int *nums, int numsSize, int i) {
  if (nums[i] != i + 1) 
    if (1 <= nums[i] && nums[i] <= numsSize && nums[i] != nums[nums[i]-1]) {
      int tmp = nums[i];	
      
      nums[i] = nums[tmp-1];
      nums[tmp-1] = tmp;
      return 0;
    }
  return 1;
}
