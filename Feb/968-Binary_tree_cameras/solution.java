import java.util.LinkedList;

class Solution {
    
    public int minCameraCover(TreeNode root) {
	LinkedList<TreeNode> cams = new LinkedList<>();
	
	if (monitored(root, cams))
	    return cams.size();
	else
	    return cams.size() + 1;
    }
    
    private boolean monitored(TreeNode root, LinkedList<TreeNode> cams) {
	if (root == null)
	    return true;
	if (root.left == null && root.right == null)
	    return false;
	
	LinkedList<TreeNode> leftCams = new LinkedList<>();
	LinkedList<TreeNode> rightCams = new LinkedList<>();
	boolean leftMonitored = monitored(root.left, leftCams);
	boolean rightMonitored = monitored(root.right, rightCams);
	
	cams.addAll(leftCams);
	cams.addAll(rightCams);
	if (leftMonitored && rightMonitored) {
	    if ((!leftCams.isEmpty() && leftCams.getLast() == root.left)
		|| (!rightCams.isEmpty() && rightCams.getLast() == root.right))
		return true;
	    else
		return false;
	} else {
	    cams.add(root);
	    return true;
	}
    }
}
