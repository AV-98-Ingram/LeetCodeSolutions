import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class Solution {

    static Set<Integer> emptySet = new TreeSet<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
	BitSet taken = new BitSet(numCourses); // courses have been taken
	Map<Integer, Set<Integer>> enables = new HashMap<>();
	Map<Integer, Set<Integer>> depends = new HashMap<>();

	taken.set(0, numCourses, true);
	for (int[] preq : prerequisites) {
	    Set<Integer> l = enables.computeIfAbsent(preq[1],
						     k -> new HashSet<>());

	    l.add(preq[0]); // preq[1] (partially) enables preq[0]
	    l = depends.computeIfAbsent(preq[0], k -> new HashSet<>());
	    l.add(preq[1]); // preq[0] depends on preq[1]
	    taken.set(preq[0], false);
	}

	int[] results = new int[numCourses];
	int rp = 0;
	List<Integer> workList = new LinkedList<>();

	// initialize the starters:
	for (int i = taken.nextSetBit(0); i >= 0; i = taken.nextSetBit(i + 1))
	    workList.add(i);
	// BFS:
	while (!workList.isEmpty()) {
	    int c = workList.remove(0);

	    results[rp++] = c;
	    for (int nxt : enables.getOrDefault(c, emptySet)) {
		// remove edge c->nxt for nxt
		Set<Integer> nxtDepends = depends.get(nxt);

		nxtDepends.remove(c);
		if (nxtDepends.isEmpty())
		    workList.add(nxt);
	    }
	}
	if (rp == numCourses)
	    return results;
	return new int[0];
    }

    public static void main(String[] args) {
	new Solution().findOrder(3, new int[][] { { 1, 0 }, { 1, 2 } });
    }
}
