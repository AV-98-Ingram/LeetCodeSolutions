import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
   Idea: keep the track of the collected leaves' parent nodes.
 */
class Solution {
	// TreeNode is defined by the problem description:
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

	Map<TreeNode, TreeNode> parents = new IdentityHashMap<>();

	public List<List<Integer>> findLeaves(TreeNode root) {
		List<TreeNode> leaves = new LinkedList<>();
		List<List<Integer>> results = new LinkedList<>();

		traverse(root, leaves);
		while (!leaves.isEmpty()) {
			List<TreeNode> nexts = new LinkedList<>();
			List<Integer> result = new LinkedList<>();

			for (TreeNode leaf : leaves) {
				result.add(leaf.val);

				TreeNode parent = parents.get(leaf);

				if (parent != null) {
					if (parent.left == leaf)
						parent.left = null;
					else if (parent.right == leaf)
						parent.right = null;
					if (parent.left == null && parent.right == null)
						nexts.add(parent);
				}
			}
			results.add(result);

			List<TreeNode> tmp = leaves;

			leaves = nexts;
			nexts = tmp;
			nexts.clear();
		}
		return results;
	}

	// requires tree != null && leaves != null;
	private void traverse(TreeNode tree, List<TreeNode> leaves) {
		if (tree.left == null && tree.right == null) {
			leaves.add(tree);
			return;
		}
		if (tree.left != null) {
			traverse(tree.left, leaves);
			parents.put(tree.left, tree);
		}
		if (tree.right != null) {
			traverse(tree.right, leaves);
			parents.put(tree.right, tree);
		}
	}
}
