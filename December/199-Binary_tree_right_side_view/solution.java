class Solution {
    /* Idea:        
       Traverses the tree in root -> right -> left order in companion
       with a saw-node list.  Whenever the current depth is beyond the
       size of the saw-node list, the visiting node shall be added to
       the list.       
     */
    public List<Integer> rightSideView(TreeNode root) {
	List<Integer> result = new LinkedList<>();

	f(result, root, 0);
	return result;
    }


    private void f(List<Integer> saw, TreeNode root, int depth) {
	if (root == null)
	    return;
	if (depth >= saw.size())
	    saw.add(root.val);
	f(saw, root.right, depth+1);
	f(saw, root.left, depth+1);
    }
}
