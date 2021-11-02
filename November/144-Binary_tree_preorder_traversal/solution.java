class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
	List<Integer> result = new LinkedList<>();

	if (root == null)
	    return result;
	result.add(root.val);
	result.addAll(preorderTraversal(root.left));
	result.addAll(preorderTraversal(root.right));
	return result;
    }
}
