class Solution {
    Map<Integer, Set<Integer>> freq2sums = new HashMap<>();
    Map<Integer, Integer> sum2freq = new HashMap<>();
    int freqMax = 0;
    
    public int[] findFrequentTreeSum(TreeNode root) {
	f(root);
	
	Set<Integer> set = freq2sums.get(freqMax);
	
	// assert set != null;
	
	int[] result = new int[set.size()];
	int k = 0;
	
	for (int i : set)
	    result[k++] = i;
	return result;
    }
    
    private int f(TreeNode root) {
	if (root == null)
	    return 0;
	
	int sum = root.val + f(root.left) + f(root.right);
	int newFreq = sum2freq.merge(sum, 1, Integer::sum);
	Set<Integer> oldFreqSet = freq2sums.get(newFreq - 1);
	
	if (oldFreqSet != null)
	    oldFreqSet.remove(sum);
	freq2sums.computeIfAbsent(newFreq, k->new HashSet<>()).add(sum);
	freqMax = Math.max(newFreq, freqMax);
	return sum;
    }
}
