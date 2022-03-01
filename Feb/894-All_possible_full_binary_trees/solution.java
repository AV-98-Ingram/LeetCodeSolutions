class Solution {
    public List<TreeNode> allPossibleFBT(int n) {
	List<TreeNode> result = new LinkedList<>();

	if (n < 1 || (n & 1) == 0)
	    return result;	
	if (n == 1) {
	    result.add(new TreeNode(0));
	    return result;
	}
	
	assert n > 2;

	for (int i = 1; i < n-1; i++) {
	    List<TreeNode> lefts = allPossibleFBT(i);
	    List<TreeNode> rights = allPossibleFBT(n-1-i);
	    
	    for (TreeNode left : lefts)
		for (TreeNode right : rights)
		    result.add(new TreeNode(0, left, right));	    
	}
	return result;
    }
}
