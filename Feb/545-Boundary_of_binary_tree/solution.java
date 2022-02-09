class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
	LinkedList<Integer> lbs = new LinkedList<>(),
	    rbs = new LinkedList<>(),
	    leaves = new LinkedList<>(),
	    results = new LinkedList<>();

	if (root == null)
	    return results;
	if (root.left != null)
	    collect(root.left, true, false, lbs, rbs, leaves);
	if (root.right != null)
	    collect(root.right, false, true, lbs, rbs, leaves);
	results.add(root.val);
	results.addAll(lbs);
	results.addAll(leaves);	
	results.addAll(rbs);
	return results;
    }

    private void collect(TreeNode root, boolean isLB, boolean isRB,
			 List<Integer> lbs, LinkedList<Integer> rbs, List<Integer> leaves) {
	if (root.left == null && root.right == null) {
	    leaves.add(root.val);
	    return;
	}
	if (isLB) 
	    lbs.add(root.val);
	if (isRB)
	    rbs.addFirst(root.val);
	if (root.left != null)
	    collect(root.left, isLB, isRB && root.right == null, lbs, rbs, leaves);
	if (root.right != null)
	    collect(root.right, isLB && root.left == null, isRB, lbs, rbs, leaves);		
    }
}
