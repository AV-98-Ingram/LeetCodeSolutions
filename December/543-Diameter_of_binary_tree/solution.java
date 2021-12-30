class Solution {

    Map<TreeNode, Integer> depths = new IdentityHashMap<>();
    
    public int diameterOfBinaryTree(TreeNode root) {
	if (root == null)
	    return 0;
	
	int result = Math.max(diameterOfBinaryTree(root.left),
			      diameterOfBinaryTree(root.right));
	int d = depth(root.left) + depth(root.right);

	return Math.max(result, d);
    }

    private int depth(TreeNode root) {
	if (root == null)
	    return 0;

	Integer cached = depths.get(root);

	if (cached != null)
	    return cached;
	
	int d = Math.max(depth(root.left), depth(root.right)) + 1;

	depths.put(root, d);
	return d;
    }
}
