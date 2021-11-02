class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
	List<Integer> result = new LinkedList<>();
	LinkedList<TreeNode> stack = new LinkedList<>();

	if (root == null)
	    return result;
	stack.add(root);
	while (!stack.isEmpty()) {
	    TreeNode node = stack.removeFirst();

	    result.add(node.val);
	    if (node.right != null)
		stack.addFirst(node.right);
	    if (node.left != null)
		stack.addFirst(node.left);
	}
	return result;
    }
}
