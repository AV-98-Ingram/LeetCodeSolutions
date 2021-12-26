class Solution {

    /* Basic idea:
    
       Define a function LCA: Tree -> Tree by

       Let LCA(root) = root, if the root is p or q.  Why directly
       return once root is p or q?  If the root is the LCA, then we
       are fine.  If the root is not the LCA, we are informing that
       this tree contains p or q by returning p or q.

       Otherwise, we check both children: left and right recursively.
       - If LCA(left) != null && LCA(right) != null, LCA(root) = root.
       - If either LCA(Left) or LCA(right) is non-null, returning the
         non-null value because it is either the case that LCA exists in
         one of the children or one of them is informing the
         containedness of p or q.
       - If LCA(left) = LCA(right) = null, return null, informing that nothing in this tree.
       
     */    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	return LCA(root, p, q);
    }

    private TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
	if (root == null)
	    return null;

	TreeNode node;
	
	if (root == p || root == q) {
	    return root;
	} else {	    
	    node = LCA(root.left, p, q);
	    if (node == null)
		return LCA(root.right, p, q);
	    
	    TreeNode node2 = LCA(root.right, p, q);

	    if (node2 != null)
		return root;
	    return node;
	}
    }
}
