import java.util.HashMap;
import java.util.Map;

/**
 * The symmertic version of "105. Construct Binary Tree from Preorder and
 * Inorder Traversal"
 * 
 */

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

    public TreeNode buildTree(int[] inorder, int[] postorder) {
	int i = inorder.length - 1;
	int j = postorder.length - 1;
	TreeNode root = new TreeNode(postorder[j]);
	Map<Integer, TreeNode> postVisited = new HashMap<>();
	TreeNode parent = root;

	postVisited.put(postorder[j--], parent);
	while (j >= 0) {
	    if (inorder[i] == parent.val) {
		// create left tree:
		do {
		    parent = postVisited.get(inorder[i--]);
		} while (i >= 0 && postVisited.containsKey(inorder[i]));
		if (i < 0) {
		    assert j < 0;
		    continue;
		}
		parent.left = new TreeNode(postorder[j]);
		postVisited.put(postorder[j--], parent.left);
		parent = parent.left;
	    } else {
		// create right tree
		parent.right = new TreeNode(postorder[j]);
		postVisited.put(postorder[j--], parent.right);
		parent = parent.right;
	    }
	}
	return root;
    }

    public static void main(String[] args) {
	new Solution().buildTree(new int[] { 9, 3, 15, 20, 7 },
				 new int[] { 9, 15, 7, 20, 3 });
    }
}
