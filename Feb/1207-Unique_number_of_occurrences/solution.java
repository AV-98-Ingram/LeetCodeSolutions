class Solution {
    public boolean uniqueOccurrences(int[] arr) {
	Map<Integer, Integer> map = new HashMap<>();
	Set<Integer> set = new HashSet<>();
	
	for (int i : arr)
	    map.merge(i, 1, Integer::sum);
	set.addAll(map.values());
	return set.size() == map.size();
    }
}
