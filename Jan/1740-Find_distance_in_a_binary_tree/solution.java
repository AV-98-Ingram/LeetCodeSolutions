
class Solution {
    public int findDistance(TreeNode root, int p, int q) {
	if (p == q)
	    return 0;
	return dist(root, p, q, 0, false);
    }


    // returns the depth of p or q if one of them found under root
    // returns the distance of p and q if both are found under root
    // returns 0 if nothing found under root
    private int dist(TreeNode root, int p, int q, int depth, boolean foundOne) {
	if (root == null)
	    return 0;
	if (root.val == p || root.val == q) {
	    if (foundOne)
		return depth;
	    
	    int dep = dist(root.left, p, q, depth + 1, true);

	    if (dep > 0)
		return dep - depth;
	    dep = dist(root.right, p, q, depth + 1, true);
	    if (dep > 0)
		return dep - depth;
	    return depth;
	}

	int ld = dist(root.left, p, q, depth + 1, foundOne);

	if (ld > 0 && foundOne)
	    return ld;

	int rd = dist(root.right, p, q, depth + 1, foundOne);

	if (rd > 0 && foundOne)
	    return rd;
	if (ld > 0 && rd > 0)
	    return (ld - depth) + (rd - depth);
	return ld + rd;
    }
}
