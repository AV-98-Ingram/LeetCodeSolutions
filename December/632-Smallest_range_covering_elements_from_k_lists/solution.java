import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {

    class MyList {
	List<Integer> lst;
	int id;

	MyList(List<Integer> lst, int id) {
	    this.lst = lst;
	    this.id = id;
	}
    }

    public int[] smallestRange(List<List<Integer>> nums) {
	// first do a merge sort:
	ArrayList<int[]> all = new ArrayList<>();
	PriorityQueue<MyList> pq = new PriorityQueue<>((l1, l2) -> (Integer.compare(l1.lst.get(0), l2.lst.get(0))));
	int id = 0;

	for (List<Integer> lst : nums)
	    pq.add(new MyList(lst, id++));
	while (!pq.isEmpty()) {
	    MyList ml = pq.poll();
	    int min = ml.lst.remove(0);

	    all.add(new int[] { min, ml.id });
	    if (!ml.lst.isEmpty())
		pq.add(ml);
	}
	// then do a variable window sliding
	final int k = nums.size();
	final int len = all.size();
	Map<Integer, Integer> window = new HashMap<>();
	int[] min = null;
	int l = 0, r = 1;

	window.merge(all.get(0)[1], 1, Integer::sum);
	while (r < len || window.size() == k) {
	    if (window.size() < k) {
		// move right bound right:
		window.merge(all.get(r)[1], 1, Integer::sum);
		r++;
	    } else {
		// move left bound right:
		int L = l, R = r;

		while (window.size() == k) {
		    L = l;
		    R = r;
		    if (window.compute(all.get(l)[1], (key, v) -> v - 1) == 0)
			window.remove(all.get(l)[1]);
		    l++;
		}
		if (min == null)
		    min = new int[] { all.get(L)[0], all.get(R - 1)[0] };
		else
		    min = min(min, new int[] { all.get(L)[0], all.get(R - 1)[0] });
	    }
	}
	return min;
    }

    private int[] min(int[] range1, int[] range2) {
	if ((range1[1] - range1[0]) == (range2[1] - range2[0]))
	    return range1[0] < range2[0] ? range1 : range2;
	return (range1[1] - range1[0]) < (range2[1] - range2[0]) ? range1 : range2;
    }

    public static void main(String[] args) {
	int[][] in = new int[][] { { 4, 10, 15, 24, 26 }, { 0, 9, 12, 20 }, { 5, 18, 22, 30 } };
	List<List<Integer>> ll = new LinkedList<>();

	for (int[] ini : in) {
	    List<Integer> l = new LinkedList<>();

	    for (int i : ini)
		l.add(i);
	    ll.add(l);
	}
	System.out.print(new Solution().smallestRange(ll));
    }
}
