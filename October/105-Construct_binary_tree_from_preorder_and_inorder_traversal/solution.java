import java.util.HashMap;
import java.util.Map;

/**
 * Basic idea: <code>
 *  Let p be the pointer to the pre-order input and i be the pointer to the in-order input.
 *  
 *  1. Starting from root = mk(*p)
 *  
 *  2. Recursively making left child of parent along p (increment p) until the last created tree node matches *i.
 *     Note that at this point *(p-1) = *i.
 *  
 *  3. Now it's now to go back as the in-order traversal starts from the leftmost node which means the last created one is leftmost.
 *     Move along i (increment i) until (a) a node matches *i has not been created; or (b) i terminates.
 *     3.a. It means that *(i - 1) is the parent, for which we are going to create a right tree. 
 *          Note that *i belongs to the incoming right tree. *p is the root of the incoming right tree.
 *     3.b. It means we reaches the end of the algorithm.
 *  
 *  4. Continued from 3.a, we create the root node of the right tree under the proper parent.  
 *     Then recursively, we go back to step 2 from the newly created node.
 * </code>
 * 
 * 
 * @author ziqing
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

	public String toString() {
	    String leftStr = left == null ? "nil" : left.toString();
	    String rightStr = right == null ? "nil" : right.toString();

	    return "{ " + val + ", " + leftStr + ", " + rightStr + " }";
	}
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
	Map<Integer, TreeNode> map = new HashMap<>();
	TreeNode root = new TreeNode(preorder[0]);

	map.put(preorder[0], root);
	f(preorder, 1, inorder, 0, map, root);
	return root;
    }

    private void f(int[] preord, int i, int[] inord, int j,
		   Map<Integer, TreeNode> preordVisited, TreeNode parent) {
	if (parent.val == inord[j]) {
	    TreeNode newParent;

	    do {
		newParent = preordVisited.get(inord[j++]);
	    } while (j < inord.length && preordVisited.containsKey(inord[j]));
	    if (j == inord.length) {
		assert i == preord.length;
		return;
	    }
	    newParent.right = new TreeNode(preord[i++]);
	    preordVisited.put(newParent.right.val, newParent.right);
	    f(preord, i, inord, j, preordVisited, newParent.right);
	} else {
	    parent.left = new TreeNode(preord[i++]);
	    preordVisited.put(parent.left.val, parent.left);
	    f(preord, i, inord, j, preordVisited, parent.left);
	}
    }

    public static void main(String[] args) {
	new Solution().buildTree(new int[] { 1, 2 }, new int[] { 2, 1 });
    }
}
