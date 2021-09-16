#define T int
#define SWAP_T(x, y) {T tmp = (x); (x) = (y); (y) = tmp;}

int partition(T * a, int start, int end, int pivot) {
  int left = pivot, right = pivot; 
  
  while (left >= start || right < end) {
    if (left < start) {
      if (a[right] < a[pivot]) {
	if (pivot + 1 < right)
	  SWAP_T(a[pivot], a[pivot+1]);	  
	SWAP_T(a[pivot], a[right]);
	pivot++;
      }
      right++;
    } else if (right >= end) {
      if (a[left] > a[pivot]) {
	if (pivot - 1 > left)
	  SWAP_T(a[pivot], a[pivot-1]);
	SWAP_T(a[pivot], a[left]);
	pivot--;
      }
      left--;
    } else if (a[left] > a[pivot] && a[right] < a[pivot]) {      
      // swap left and right:
      SWAP_T(a[left], a[right]);
      left--; right++;
    } else if (a[left] > a[pivot]) 
      right++;
    else if (a[right] < a[pivot]) 
      left--;
    else {
      left--; right++;
    }
  }
  return pivot;
}

void quick_sort(T * a, int start, int end) {
  if (start + 1 >= end)
    return;
  
  int pivot = (end-start)/2 + start;
  
  pivot = partition(a, start, end, pivot);
  quick_sort(a, start, pivot);
  quick_sort(a, pivot, end);  
}


/*
  A sequence of numbers is in MAX lexicographical order if it is in
  NON-ASCENDING order.

  BASE: For the last two numbers {i, j}, if !isMax({i, j}), switch i
  and j then we have isMax({j, i}) and we are done.

  STEP: Hypothesis: The last n-1 numbers {a_{n-2}, a_{n-3}, ..., a_0}
        is MAX or we have been done.
	
        If we are not done, we switch a_{n-1} with the smallest one in
        the last n-1 numbers such that it is strictly greater than
        a_{n-1}.  Then we sort the last n-1 numbers then we are done.
        e.g., {3, 4, 2, 1}.  For last 3 numbers, they are MAX. But the
        last 4 numbers are not MAX, so we switch 3 and 4 and sort the
        last 3 numbers, obtain {4, 1, 2, 3}.  It is one step greater
        than {3, 4, 2, 1} in lexicographical order.

	If the whole sequence of numbers are MAX, we simply sort them
	to be the MIN one in lexicographical order.
 */


// switch nums[switchee] with the last one in the sequence
// nums[switchee+1, ...] that is GREATER THAN nums[switchee].
// Such one that is GREATER THAN nums[switchee] must exist.
void switch_nums(int * nums, int switchee, int numsSize) {
  int snum = nums[switchee];
  int last;
  
  for (int i = switchee+1; i < numsSize; i++)
    if (nums[i] > snum)
      last = i;
  int tmp = nums[last];
  
  nums[last] = snum;
  nums[switchee] = tmp;
}

// test max with assumption that nums[start+1, ...] is MAX
char is_max(int * nums, int start) {
  return nums[start] >= nums[start+1];
}

void nextPermutation(int* nums, int numsSize){
  if (numsSize == 1)
    return;
  if (!is_max(nums, numsSize-2)) {
    switch_nums(nums, numsSize-2, numsSize);
    return;
  }
  
  for (int i = numsSize-3; i >=0; i--) {
    if (is_max(nums, i))
      continue;
    else {
      switch_nums(nums, i, numsSize);
      quick_sort(nums, i+1, numsSize);
      return;
    }
  }
  quick_sort(nums, 0, numsSize);
  return;
}
