class Solution {
        
    public int maxCoins(int[] nums) {
	int[] exNums = new int[nums.length + 2];
	Integer[][] cache = new Integer[nums.length+2][nums.length+2];
	
	System.arraycopy(nums, 0, exNums, 1, nums.length);
	exNums[0] = 1;
	exNums[nums.length + 1] = 1;
	return divideAndConquer(exNums, 1, nums.length, cache);
    }

    private int divideAndConquer(int[] nums, int left, int right, Integer[][] cache) {
	if (left > right)
	    return 0;

	if (cache[left][right] != null)
	    return cache[left][right];

	int max = 0;
	
	for (int i = left; i <= right; i++) {
	    int coins = nums[i] * nums[left - 1] * nums[right+1];

	    coins += divideAndConquer(nums, left, i-1, cache) +
		divideAndConquer(nums, i+1, right, cache);
	    max = Math.max(coins, max);
	}
	cache[left][right] = max;
	return max;
    }
}
