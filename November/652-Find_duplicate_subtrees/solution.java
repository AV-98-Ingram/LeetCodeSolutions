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

    Map<String, List<TreeNode>> subtreeDecoding = new HashMap<>();
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        build(root);

	List<TreeNode> result = new LinkedList<>();
	
	for (List<TreeNode> dups : subtreeDecoding.values())
	    if (dups.size() > 1)
		result.add(dups.get(0));
	return result;
    }

    private String build(TreeNode root) {
	String encoding = String.valueOf(root.val);

	if (root.left == null)
	    encoding += ",nil";
	else
	    encoding += "," + build(root.left);
	if (root.right == null)
	    encoding += ",nil";
	else
	    encoding += "," + build(root.right);
	subtreeDecoding.computeIfAbsent(encoding, k->(new LinkedList<>())).add(root);
	return encoding;
    }
}

