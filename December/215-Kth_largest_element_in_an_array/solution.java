import java.util.TreeSet;

class Solution {
    public int findKthLargest(int[] nums, int k) {
	TreeSet<Integer> bag = new TreeSet<>((a, b) -> b < a ? -1 : 1);

	for (int i : nums) {
	    bag.add(i);
	    if (bag.size() > k) {
		bag.pollLast();
	    }
	}
	return bag.last();
    }

    public static void main(String[] args) {
	new Solution().findKthLargest(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4);
    }
}
