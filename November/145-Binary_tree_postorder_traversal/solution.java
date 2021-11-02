/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
	List<Integer> result = new LinkedList<>();
	LinkedList<TreeNode> stack = new LinkedList<>();

	if (root != null)
	    stack.add(root);
	while (!stack.isEmpty()) {
	    root = stack.remove(0);
	    if (root.left == null && root.right == null) {
		result.add(root.val);
	    } else {
		stack.addFirst(root);
		if (root.right != null)
		    stack.addFirst(root.right);
		if (root.left != null)
		    stack.addFirst(root.left);
		root.left = null;
		root.right = null;
	    }
	}
	return result;
    }
}
