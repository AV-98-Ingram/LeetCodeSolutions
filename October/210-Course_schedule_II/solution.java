import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    static List<Integer> emptyList = new LinkedList<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
	BitSet taken = new BitSet(numCourses); // courses have been taken
	// course a (partially) enables courses {b, c, d, ...}:
	Map<Integer, List<Integer>> enables = new HashMap<>();
	// course a depends on courses {b, c, d, ...}:
	Map<Integer, List<Integer>> depends = new HashMap<>();

	taken.set(0, numCourses, true);
	for (int[] preq : prerequisites) {
	    List<Integer> l = enables.computeIfAbsent(preq[1],
						      k -> new LinkedList<>());

	    l.add(preq[0]); // preq[1] (partially) enables preq[0]
	    l = depends.computeIfAbsent(preq[0], k -> new LinkedList<>());
	    l.add(preq[1]); // preq[0] depends on preq[1]
	    taken.set(preq[0], false);
	}

	int[] results = new int[numCourses];
	int rp = 0;
	List<Integer> candidates = new LinkedList<>();
	List<Integer> nextCandidates = new LinkedList<>();

	// initialize candidates and results with courses depending on nothing:
	for (int i = taken.nextSetBit(0); i >= 0; i = taken.nextSetBit(i + 1)) {
	    results[rp++] = i;
	    for (int cand : enables.getOrDefault(i, emptyList))
		if (!taken.get(cand))
		    candidates.add(cand);
	}

	boolean updated = true;

	while (updated) { // fix-point computation:
	    updated = false;
	    while (!candidates.isEmpty()) {
		int cand = candidates.remove(0);
		boolean allDependantsTaken = true;

		if (taken.get(cand))
		    continue;
		for (int candDepend : depends.get(cand)) {
		    if (!taken.get(candDepend)) {
			allDependantsTaken = false;
			break;
		    }
		}
		if (allDependantsTaken) {
		    updated = true;
		    taken.set(cand);
		    results[rp++] = cand;
		    for (int nxtCand : enables.getOrDefault(cand, emptyList))
			candidates.add(nxtCand);
		} else // add back
		    nextCandidates.add(cand);
	    }

	    List<Integer> tmp = candidates;

	    candidates = nextCandidates;
	    nextCandidates = tmp;
	    tmp.clear();
	}

	if (rp == numCourses)
	    return results;
	return new int[0];
    }

    public static void main(String[] args) {
	new Solution().findOrder(3,
				 new int[][] { { 1, 0 }, { 1, 2 }, { 0, 1 } });
    }
}
