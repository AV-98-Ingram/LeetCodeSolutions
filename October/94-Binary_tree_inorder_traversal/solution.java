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
    public List<Integer> inorderTraversal(TreeNode root) {
	List<Integer> results =  new LinkedList<>();

	if (root != null)
	    f(root, results);
	return results;
    }
    
    private void f(TreeNode node, List<Integer> results) {
	if (node.left != null) 
	    f(node.left, results);
	results.add(node.val);
	if (node.right != null) 
	    f(node.right, results);
    }
}
