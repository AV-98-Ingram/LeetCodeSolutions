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

    public TreeNode sortedArrayToBST(int[] nums) {
	int mid = nums.length / 2;
	TreeNode root = new TreeNode(nums[mid]);

	root.left = mkBSTree(nums, 0, mid);
	root.right = mkBSTree(nums, mid + 1, nums.length);
	return root;
    }

    private TreeNode mkBSTree(int[] nums, int start, int end) {
	if (end <= start)
	    return null;
	if (end - start == 1)
	    return new TreeNode(nums[start]);

	int mid = (end - start) / 2 + start;
	TreeNode root = new TreeNode(nums[mid]);

	root.left = mkBSTree(nums, start, mid);
	root.right = mkBSTree(nums, mid + 1, end);
	return root;
    }
}
