class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
	if (p.right != null)
	    return next(p.right);
	if (root == p)
	    return null;
	
	TreeNode result =  inorder(root, p);

	if (result == null || result == p)
	    return null;
	return result;
    }
    
    private TreeNode inorder(TreeNode root, TreeNode p) {
	if (root == null)
	    return null;

	TreeNode result = inorder(root.left, p);
	
	if (result == p) // left contains p but not p's successor
	    return root;
	else if (result != null)
	    return result; // left contains p's successor
	if (root == p)
	    return p; // we know that p has no right children
	return inorder(root.right, p);
    }

    private TreeNode next(TreeNode node) {
	if (node.left != null)
	    return next(node.left);
	return node;
    }
}
