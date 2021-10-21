import java.util.LinkedList;
import java.util.List;

class Solution {
    
    public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	
	TreeNode() {
	}
	
	TreeNode(int val) {
	    this.val = val;
	}
	
	TreeNode(int val, TreeNode left, TreeNode right) {
	    this.val = val;
	    this.left = left;
	    this.right = right;
	}
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
	List<List<Integer>> results = new LinkedList<>();
	
	if (root == null)
	    return results;
	for (List<Integer> l : f(root))
	    if (l.get(0) == targetSum) {
		l.remove(0);
		results.add(l);
	    }
	return results;
    }
    
    private List<List<Integer>> f(TreeNode tree) {
	List<List<Integer>> results = new LinkedList<>();
	
	if (tree.left == null && tree.right == null) {
	    List<Integer> l = new LinkedList<>();
	    
	    l.add(tree.val);
	    l.add(tree.val);
	    results.add(l);
	    return results;
	}
	if (tree.left != null)
	    for (List<Integer> vals : f(tree.left)) {
		vals.add(1, tree.val);
		vals.set(0, vals.get(0) + tree.val);
		results.add(vals);
	    }
	if (tree.right != null)
	    for (List<Integer> vals : f(tree.right)) {
		vals.add(1, tree.val);
		vals.set(0, vals.get(0) + tree.val);
		results.add(vals);
	    }
	return results;
    }
}
