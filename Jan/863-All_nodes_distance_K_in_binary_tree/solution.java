class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
	List<Integer> result = new LinkedList<>();

	f(root, target, -1, k, result);
	return result;
    }

    /* 
       Parameter d: the distance from target down to node if node is a
       descendant of target. -1 otherwise.    
     */
    /* 
       Returns the distance from target to node if node is on the path
       from target back to root. -1 otherwise.
    */
    private int f(TreeNode node, TreeNode target, int d, int k, List<Integer> result) {
	if (node == null)
	    return -1;
	if (d >= 0) {
	    if (d == k) {
		result.add(node.val);
		return -1;
	    }
	    f(node.left, target, d+1, k, result);
	    f(node.right, target, d+1, k, result);
	    return -1;
	} else if (node == target) {
	    f(node, target, 0, k, result);
	    return 0;
	} else {
	    int dist = f(node.left, target, d, k, result);

	    if (dist >= 0) {
		g(node.right, k - dist - 2, result);
	    } else {
		dist = f(node.right, target, d, k, result);
		if (dist >= 0)		
		    g(node.left, k - dist - 2, result);
	    }
	    if (dist >= 0 && dist + 1 == k)
		result.add(node.val);
	    return dist >= 0 ? dist + 1 : -1;
	}
    }

    // simply search down for d distance:
    private void g(TreeNode node, int d, List<Integer> result) {
	if (node == null || d < 0)
	    return;
	if (d == 0) {
	    result.add(node.val);
	    return;
	} else if (d > 0) {
	    g(node.left, d-1, result);
	    g(node.right, d-1, result);
	}	
    }
}
