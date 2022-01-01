class Solution {
    public Node lowestCommonAncestor(Node p, Node q) { 
	Node root = p;
	
	while (root.parent != null) {
	    root = root.parent;
	}
	return lca(root, p, q);
    }

    // requires that p and q must exist in the whole tree;
    private Node lca(Node root, Node p, Node q) {
	if (root == null)
	    return null;
	if (root == p || root == q)
	    return root;
	
	Node left = lca(root.left, p, q);
	Node right = lca(root.right, p, q);

	if (left != null && right != null)
	    return root;
	if (left != null)
	    return left;
	if (right != null)
	    return right;
	return null;
    }
}
