


typedef struct {
    int * nums;
    int * fixed;
    int size;
    int seed;
} Solution;


Solution* solutionCreate(int* nums, int numsSize) {
    Solution * obj = (Solution *)malloc(sizeof(Solution));
    
    obj->nums = (int *)malloc(sizeof(int) * numsSize);  
    obj->fixed = (int *)malloc(sizeof(int) * numsSize);  
    obj->size = numsSize;
    obj->seed = 0;
    memcpy(obj->nums, nums, sizeof(int) * numsSize);
    memcpy(obj->fixed, nums, sizeof(int) * numsSize);
    return obj;
}

/** Resets the array to its original configuration and return it. */
int* solutionReset(Solution* obj, int* retSize) {
    *retSize = obj->size;
    memcpy(obj->nums, obj->fixed, sizeof(int) * obj->size);
    return obj->nums;
}

/** Returns a random shuffling of the array. */
int* solutionShuffle(Solution* obj, int* retSize) {
  int * nums = obj->nums;
  int size = obj->size;
  int table_len = size;
    
  if (table_len > 0) {  
  int table[table_len];
  int out[size];
    
    *retSize = size;
    for (int i = 0; i < table_len; i++) table[i] = i;
    
    while (table_len > 1) {
        int pick = rand() % table_len;
        
        out[size - table_len] = nums[table[pick]];
        table[pick] = table[table_len - 1];
        table_len -= 1;
    }
    out[size-1] = nums[table[0]];
    memcpy(nums, out, sizeof(int) * size);
  }    
    return nums;
}

void solutionFree(Solution* obj) {
    free(obj->nums);
    free(obj->fixed);
    free(obj);
}

/**
 * Your Solution struct will be instantiated and called as such:
 * Solution* obj = solutionCreate(nums, numsSize);
 * int* param_1 = solutionReset(obj, retSize);
 
 * int* param_2 = solutionShuffle(obj, retSize);
 
 * solutionFree(obj);
*/
