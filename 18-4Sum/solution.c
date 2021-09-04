/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */

/* Again, use the "the pointers" approach. But we need a list. */

#define T int

typedef struct List {
  T val;
  struct List * next;
} List;

List * add_to_list(List * tail, T val) {
  List * nxt = (List *)malloc(sizeof(List));
  
  nxt->val = val;
  nxt->next = NULL;
  if (tail == NULL)
    tail = nxt;
  else
    tail->next = nxt;
  return nxt;
}

int** fourSum(int* nums, int numsSize, int target, int* returnSize, int** returnColumnSizes) {
  List * l, l_tail;

  for (int i = 0; i < numsSize-3; i++)
    for (int j = i+1; j < numsSize-2; j++) {
      int left = j+1, right = numsSize - 1;
      int ij = nums[i] + nums[j];

      while (left != right) {
	int lr = nums[left] + nums[right];

	if (lr + ij == target) {
	  
	}
	  
      }
    }  
}

[,,[387,421,435,439],[387,421,435,439],[333,439,440,470],[347,408,435,492],[333,387,470,492],[333,387,470,492],[314,435,441,492],[361,408,421,492],[361,408,421,492],[356,421,435,470],[310,439,441,492],[408,414,421,439],[387,414,440,441],[387,414,440,441],[306,414,470,492],[367,435,439,441],[334,421,435,492]]

Expected:
[[306,414,470,492],[310,439,441,492],[314,435,441,492],,[333,387,470,492],[333,439,440,470],[334,421,435,492],[347,408,435,492],[356,421,435,470],,[361,408,421,492],[367,435,439,441],[387,414,440,441],[387,421,435,439],[408,414,421,439]]
