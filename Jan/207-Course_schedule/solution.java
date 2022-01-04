class Solution {
    static final List<Integer> emptyList = new LinkedList<>();
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
	Set<Integer> visited = new HashSet<>(numCourses);
	Map<Integer, List<Integer>> edges = new HashMap<>();
	int[] indegrees = new int[numCourses];
	BitSet startSet = new BitSet(numCourses);

	startSet.flipAll();
	for (int[] prereq : prerequisites) {
	    edges.computeIfAbsent(prereq[1], k -> new LinkedList<>()).add(prereq[0]);
	    startSet.setClear(prereq[0]);
	    indegrees[prereq[0]]++;
	}

	LinkedList<Integer> que = new LinkedList<>();

	for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i+1))
	    que.add(i);
	while (!que.isEmpty()) {
	    int c = que.removeFirst();

	    if (!visited.add(c))
		return false;
	    for (int nxt : edges.getOrDefault(i, emptyList)) {
		indegrees[nxt]--;
		if (indegrees[nxt] == 0)
		    que.add(nxt);
		if (indegrees[nxt] < 0)
		    return false;
	    }
	}
	return visited.size() == numCourses;
    }
}
