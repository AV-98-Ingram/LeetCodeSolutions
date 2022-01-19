import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
	TreeSet<int[]> slots2Tree = new TreeSet<>((a, b) -> Integer.compare(a[0], b[0]));
	List<Integer> ans = new LinkedList<>();

	Arrays.sort(slots1, (a, b) -> Integer.compare(a[0], b[0]));
	for (int[] slot2 : slots2)
	    slots2Tree.add(slot2);
	for (int[] slot1 : slots1) {
	    if (slot1[1] - slot1[0] < duration)
		continue;

	    int[] slot2 = slots2Tree.lower(slot1);
	    int start, end;

	    if (slot2 != null) {
		start = slot1[0];
		end = Math.min(slot2[1], slot1[1]);
		if (end - start >= duration) {
		    ans.add(start);
		    ans.add(start + duration);
		    return ans;
		}
	    }
	    slot2 = slots2Tree.ceiling(slot1);
	    while (slot2 != null) {
		start = Math.max(slot1[0], slot2[0]);
		end = Math.min(slot1[1], slot2[1]);
		if (end - start >= duration) {
		    ans.add(start);
		    ans.add(start + duration);
		    return ans;
		}
		if (slot2[1] >= slot1[1])
		    break;
		slot2 = slots2Tree.ceiling(new int[] { slot2[0] + 1, 0 }); // make sure it returns the next slot if it
		// exists
	    }
	}
	return ans;
    }
}
