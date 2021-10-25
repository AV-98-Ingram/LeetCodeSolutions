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

    private int max;

    public int maxPathSum(TreeNode root) {
	max = root.val;
	p(root);
	return max;
    }

    private int p(TreeNode tree) {
	if (tree.left == null && tree.right == null)
	    return tree.val;

	Integer leftMax = null, rightMax = null;

	if (tree.left != null)
	    leftMax = p(tree.left);
	if (tree.right != null)
	    rightMax = p(tree.right);

	int ret = max(add(tree.val, leftMax), add(tree.val, rightMax),
		      tree.val);

	max = max(ret, max, add(add(tree.val, leftMax), rightMax));
	if (leftMax != null)
	    max = max(max, leftMax);
	if (rightMax != null)
	    max = max(max, rightMax);
	return ret;
    }

    private int add(int a, Integer b) {
	return b == null ? a + 0 : a + b;
    }

    private int max(int... nums) {
	int max = nums[0];

	for (int i = 1; i < nums.length; i++)
	    if (max < nums[i])
		max = nums[i];
	return max;
    }
}
