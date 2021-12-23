class Solution {    
    public int maxEnvelopes(int[][] envelopes) {
	Map<Integer, ArrayList<int[]>> groupByWidth = new HashMap<>();

	for (int[] env : envelopes)
	    groupByWidth.computeIfAbsent(env[0], k->new ArrayList<>()).add(env);

	ArrayList<ArrayList<int[]>> envs = new ArrayList<>();
	
	for (Entry<Integer, ArrayList<int[]>> group : groupByWidth.entrySet()) {
	    envs.add(group);
	    Collections.sort(group, (a, b) -> (Integer.compare(a[1], b[1])));
	}
	Collections.sort(envs, (a, b)->Integer.compare(a.get(0)[0], b.get(0)[0]));

    
	final int size = envs.size();
	// find the longest chain then:
	int max = 0;
	int[][] edges = new int[size][size]; // edges[i][j]: i to j if height is < cache[i][j]
	
	for (int i = 0; i < size; i++)
	    max = Math.max(max, dfs(i, envs.get(i).get(0)[1], envs, 1));
	return max;
    }

    private int dfs(int id, int height, ArrayList<ArrayList<int[]>> envs, int numEnvs) {
	final int size = envs.size();
	int max = 0;
	
	for (int i = id+1; i < size; i++) {
	    ArrayList<int[]> group = envs.get(i);
	    
	    for (int[] member : group) {
		if (member[1] > height) {
		    max = Math.max(max, dfs(i, member[1], envs, numEnvs + 1));
		    break;
		}		
	    }
	}
	return max;
    }
}
