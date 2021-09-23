class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0, max_sum = Integer.MIN_VALUE;
        
        for(int i : nums){           
            if(sum < 0) sum = 0;
            sum += i;            
            if(sum > max_sum) max_sum = sum;           
        }        
        return max_sum;        
    }
}
