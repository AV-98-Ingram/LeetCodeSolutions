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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
	if (root == null)
	    return new LinkedList<>();
	return f(Arrays.asList(new TreeNode[] { root }));
    }

    private List<List<Integer>> f(List<TreeNode> nodes) {
	if (nodes.isEmpty())
	    return new LinkedList<>();

	List<Integer> thisLevel = new LinkedList<>();
	List<TreeNode> nxtLevelNodes = new LinkedList<>();

	for (TreeNode node : nodes) {
	    if (node.left != null)
		nxtLevelNodes.add(node.left);
	    if (node.right != null)
		nxtLevelNodes.add(node.right);
	    thisLevel.add(node.val);
	}

	List<List<Integer>> results = f(nxtLevelNodes);

	results.add(thisLevel);
	return results;
    }
}
