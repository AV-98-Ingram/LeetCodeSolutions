import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    /* Intuitive approach:
       
       Starting from the first element, then match as far as possible
       until reaches a number n that cannot be added to the current
       sequence.  Since there is nothing before n where n can be
       appended to, n must self lead a new sequece otherwise, our test
       fails.  We put the tail element of the valid sequence in a
       dictionary so that we will know if any number later can be
       appended to this sequence.

       Then, in general, we start with a number n at some
       position. All numbers before have formed valid sequences and
       their tail elements are stored in a dictionary for loop up.  
       We keep match and proceed.

       Eventually, we may reach a number m that cannot be appended to
       the current sequence (starting with n), if the current sequence
       is already valid, we can simply add it to the dictionary;
       otherwise, the current sequence is too short.  If we can append
       the short sequence to an exisiting sequence in dictionary, we
       are fine.  Otherwise, our test fails.  Remark that, when
       appending the short sequence s to an exisiting sequence s', we
       over-write the tail element of s' by s.  This over-write is
       safe because the given array is ordered so that there is no
       number appearing after s can append to s'.  But of course, they
       may have chance to be appended to s.
       
       Then we start from m and repeat the predescribed process.
     */
    
	public boolean isPossible(int[] nums) {
		LinkedList<Integer> lst = new LinkedList<>();
		LinkedList<Integer> next = new LinkedList<>();
		Map<Integer, Integer> tailCount = new HashMap<>();

		for (int i : nums)
			lst.add(i);
		while (!lst.isEmpty()) {
			int head, curr;

			head = curr = lst.remove(0);
			while (!lst.isEmpty()) {
				int nxt = lst.remove(0);

				if (nxt == curr + 1) {
					curr = nxt;
				} else
					next.add(nxt);
				if (!next.isEmpty()) {
					if (curr - head >= 2)
						break;
					if (appendToExist(head, curr, tailCount)) {
						curr = head - 1;
						break;
					}
				}
			}
			if (curr - head >= 2)
				tailCount.merge(curr, 1, Integer::sum);
			else if (curr - head >= 0)
				if (!appendToExist(head, curr, tailCount))
					return false;
			while (!next.isEmpty())
			    lst.addFirst(next.removeLast());
		}
		return true;
	}

	private boolean appendToExist(int head, int curr, Map<Integer, Integer> tailCount) {
		Integer ct = tailCount.computeIfPresent(head - 1, (k, v) -> v - 1);

		if (ct != null) {
			if (ct == 0)
				tailCount.remove(head - 1);
			tailCount.merge(curr, 1, Integer::sum);
			return true;
		} else
			return false;
	}

	public static void main(String[] args) {
		new Solution().isPossible(new int[] { 4, 5, 6, 7, 7, 8, 8, 9, 10, 11 });
	}
}
