import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    class MyUF {
	LinkedList<Integer> val = null;
	MyUF equiv = null;
    }

    Map<Integer, MyUF> seqs = new HashMap<>();

    int max = 0;

    public int longestConsecutive(int[] nums) {
	for (int n : nums) {
	    if (seqs.get(n) != null)
		continue;

	    MyUF prev = seqs.get(n - 1);
	    MyUF next = seqs.get(n + 1);
	    boolean prevExists = false;

	    if (prev != null) {
		prev = getRoot(prev);
		assert prev.val.getLast() == n - 1;
		prev.val.add(n);
		seqs.put(n, prev);
		prevExists = true;
	    }
	    if (next != null) {
		next = getRoot(next);
		assert next.val.getFirst() == n + 1;
		if (prevExists) {
		    prev.val.addAll(next.val);
		    next.val = null;
		    next.equiv = prev;
		} else {
		    next.val.addFirst(n);
		    seqs.put(n, next);
		}
	    }

	    MyUF curr = seqs.get(n);

	    if (curr == null) {
		curr = new MyUF();
		curr.val = new LinkedList<>();
		curr.val.add(n);
		seqs.put(n, curr);
	    }
	    curr = getRoot(curr);
	    if (curr.val.size() > max)
		max = curr.val.size();
	}
	return max;
    }

    private MyUF getRoot(MyUF uf) {
	List<MyUF> updateRoot = new LinkedList<>();

	while (uf.val == null) {
	    updateRoot.add(uf);
	    uf = uf.equiv;
	}
	for (MyUF updatee : updateRoot)
	    updatee.equiv = uf;
	return uf;
    }
}
