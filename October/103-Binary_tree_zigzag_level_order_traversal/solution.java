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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	if (root != null) {
	    List<TreeNode> parents = new LinkedList<>();
	    LinkedList<List<Integer>> results;
	    List<Integer> rootLevel = new LinkedList<>();

	    parents.add(root);
	    results = f(parents, false);
	    rootLevel.add(root.val);
	    results.addFirst(rootLevel);
	    return results;
	} else
	    return new LinkedList<>();
    }

    private LinkedList<List<Integer>> f(List<TreeNode> parents,
					final boolean leftToRight) {
	LinkedList<Integer> level = new LinkedList<>();
	List<TreeNode> children = new LinkedList<>();

	for (TreeNode parent : parents) {
	    TreeNode child = parent.left;

	    if (child != null) {
		if (leftToRight)
		    level.add(child.val);
		else
		    level.addFirst(child.val);
		children.add(child);
	    }
	    child = parent.right;
	    if (child != null) {
		if (leftToRight)
		    level.add(child.val);
		else
		    level.addFirst(child.val);
		children.add(child);
	    }

	}

	LinkedList<List<Integer>> results;

	if (children.isEmpty())
	    results = new LinkedList<>();
	else {
	    results = f(children, !leftToRight);
	    results.addFirst(level);
	}
	return results;
    }
}
