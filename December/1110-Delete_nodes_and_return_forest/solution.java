class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
	Set<Integer> toD = new HashSet<>();

	for (int d : to_delete)
	    toD.add(d);
	
	List<TreeNode> result = new LinkedList<>();

	delete(root, toD, null, result);
	return result;
    }

    void delete(TreeNode root, Set<Integer> toDelete, TreeNode parent, List<TreeNode> out) {
	if (parent == null)
	    if (!toDelete.contains(root.val))
		out.add(root);

	boolean deleteRoot = false;
	
	if (toDelete.contains(root.val))
	    deleteRoot = true;
	if (root.left != null)
	    delete(root.left, toDelete, deleteRoot ? null : root, out);
	if (root.right != null)
	    delete(root.right, toDelete, deleteRoot ? null : root, out);
	if (parent != null && deleteRoot) {
	    if (parent.left == root)
		parent.left = null;
	    if (parent.right == root)
		parent.right = null;
	}
    }
}
