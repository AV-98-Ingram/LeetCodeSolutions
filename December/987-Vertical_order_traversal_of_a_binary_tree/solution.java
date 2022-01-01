class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
	Map<Integer, List<int[]>> table = new HashMap<>();	
	int minMax = collect(0, 0, root, table);
	List<List<Integer>> result = new LinkedList<>();
	
	for (int i = minMax[0]; i <= minMax[1]; i++) {
	    List<int[]> lst = table.get(i);

	    Collections.sort(lst,
			     (a, b) -> (a[0] != b[0] ? Integer.compare(a[0], b[0])
					: Integer.compare(a[1], b[1])));
	    result.add(lst.stream().map((a) -> (a[1])).collect(Collectors.toList()));
	}
	return result;
    }

    private int[] collect(int r, int c, TreeNode root, Map<Integer, List<int[]>> table) {
	if (root == null)
	    return new int[] {c, c};
	
	int min = c, max = c;
	
	table.computeIfAbsent(c, k->new ArrayList<>()).add(new int[]{r, root.val});
	
	int[] ret = collect(r+1, c-1, root.left, table);

	min = Math.min(ret[0], min);
	max = Math.max(ret[1], max);	
	ret = collect(r+1, c+1, root.right, table);
	ret[0] = Math.min(ret[0], min);
	ret[1] = Math.max(ret[1], max);
	return ret;
    }
}
