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

	taken.set(0, numCourses, true);
	for (int[] preq : prerequisites) {
	    List<Integer> l = enables.computeIfAbsent(preq[1],
						      k -> new LinkedList<>());

	    l.add(preq[0]); // preq[1] (partially) enables preq[0]
	    taken.set(preq[0], false);
	}

	int[] results = new int[numCourses];
	int[] rp = new int[] { numCourses - 1 };
	List<Integer> starters = new LinkedList<>();
	BitSet visited = new BitSet(numCourses);

	// initialize the starters:
	for (int i = taken.nextSetBit(0); i >= 0; i = taken.nextSetBit(i + 1))
	    starters.add(i);
	taken.clear();
	// DFS:
	for (int i : starters)
	    if (!dfs(i, enables, visited, taken, results, rp))
		return new int[0];
	if (rp[0] == 0)
	    return results;
	return new int[0];
    }

    private boolean dfs(Integer course, Map<Integer, List<Integer>> enabled,
			BitSet grey, BitSet black, int[] out, int[] pos) {
	if (grey.get(course))
	    return false;
	if (black.get(course))
	    return true;
	grey.set(course);
	for (int next : enabled.getOrDefault(course, emptyList))
	    if (!dfs(next, enabled, grey, black, out, pos))
		return false;
	grey.clear(course);
	black.set(course);
	out[pos[0]--] = course;
	return true;
    }

    public static void main(String[] args) {
	new Solution().findOrder(2, new int[][] { { 1, 0 }, { 0, 1 } });
    }
}
