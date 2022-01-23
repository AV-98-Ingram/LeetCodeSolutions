class Solution {
    public String smallestFromLeaf(TreeNode root) {
	if (root == null)
	    return "";
	return smallest(root, new StringBuffer());
    }

    private String smallest(TreeNode node, StringBuffer suffix) {
	assert node != null;

	suffix = suffix.insert(0, (char)(node.val + 'a'));
	if (node.left == null && node.right == null) {	    
	    return suffix.toString();
	} else if (node.left == null)
	    return smallest(node.right,  suffix);
	else if (node.right == null)
	    return smallest(node.left,  suffix);
	else {
	    StringBuffer newSuffix = new StringBuffer(suffix.toString());
	    String leftStr = smallest(node.left,  suffix);
	    String rightStr = smallest(node.right,  newSuffix);

	    if (leftStr.compareTo(rightStr) > 0)
		return rightStr;
	    return leftStr;
	}
    }
}
