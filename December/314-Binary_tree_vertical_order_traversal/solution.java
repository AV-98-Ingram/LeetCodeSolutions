class Solution {

    class Pair {
	TreeNode node;
	int col;

	Pair(TreeNode node, int col) {
	    this.node = node;
	    this.col = col;
	}
    }
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
	if (root == null)
	    return new LinkedList<>();
	
	Map<Integer, List<Integer>> map = new HashMap<>();
	int maxes[] = new int[] {0, 0}; // cmax, cmin
	LinkedList<Pair> que = new LinkedList<>();

	// BFS:
	que.add(new Pair(root, 0));
	while (!que.isEmpty()) {
	    Pair p = que.removeFirst();

	    if (p.node.left != null)
		que.add(new Pair(p.node.left, p.col-1));
	    if (p.node.right != null)
		que.add(new Pair(p.node.right, p.col+1));
	    map.computeIfAbsent(p.col, k->new LinkedList<>()).add(p.node.val);
	    maxes[0] = Math.max(maxes[0], p.col);
	    maxes[1] = Math.min(maxes[1], p.col); 
	}
	
	List<List<Integer>> result = new LinkedList<>();

	for (int i = maxes[1]; i <= maxes[0]; i++)
	    result.add(map.get(i));
	return result;
    }

    private void f(TreeNode root, Map<Integer, PriorityQueue<int[]>> map, int[] maxes, int col, int row) {
	if (root == null)
	    return;
	
	maxes[0] = Math.max(maxes[0], col);
	maxes[1] = Math.min(maxes[1], col);
	map.computeIfAbsent(col,
			    k->new PriorityQueue<>((a,b)->(a[0] < b[0] ? -1 : 1))).add(new int[]{row, root.val});
	f(root.left, map, maxes, col-1, row + 1);
	f(root.right, map, maxes, col+1, row + 1);
    }
}
