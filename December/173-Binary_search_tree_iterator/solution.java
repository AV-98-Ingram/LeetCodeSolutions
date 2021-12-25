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
class BSTIterator {

    LinkedList<TreeNode> stack = new LinkedList<>();
    
    public BSTIterator(TreeNode root) {      
	TreeNode tmp = root;
	
	do {
	    stack.addFirst(tmp);
	    tmp = tmp.left;
	} while (tmp != null);       
    }
    
    public int next() {
	TreeNode left = stack.removeFirst();
	int val = left.val;

	left = left.right;
	while (left != null) {
	    stack.addFirst(left);
	    left = left.left;
	}
	return val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
