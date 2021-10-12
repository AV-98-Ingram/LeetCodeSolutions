import java.util.LinkedList;
import java.util.List;

class Solution {
    private class NodeList {
	private List<TreeNode> l;

	NodeList(List<TreeNode> l) {
	    this.l = l;
	}
    }

    static NodeList[][][] cache = new NodeList[9][10][10]; // make it static so that all runs of "generateTrees" can share the cache

    public List<TreeNode> generateTrees(int n) {
	List<TreeNode> results = new LinkedList<>();

	for (int i = 1; i <= n; i++)
	    results.addAll(f(i, 1, n));
	return results;
    }

    private List<TreeNode> f(int root, int lb, int ub) {
	NodeList cached = null;

	cached = cache[root][lb][ub];
	if (cached != null)
	    return cached.l;

	List<TreeNode> results = new LinkedList<>();
	List<TreeNode> leftNodes = new LinkedList<>();
	List<TreeNode> rightNodes = new LinkedList<>();

	for (int l = lb; l < root; l++)
	    leftNodes.addAll(f(l, lb, root - 1));
	if (leftNodes.isEmpty())
	    leftNodes.add(null);
	for (int r = root + 1; r <= ub; r++)
	    rightNodes.addAll(f(r, root + 1, ub));
	if (rightNodes.isEmpty())
	    rightNodes.add(null);
	for (TreeNode leftNode : leftNodes)
	    for (TreeNode rightNode : rightNodes) {
		TreeNode rootNode = new TreeNode(root);

		rootNode.left = leftNode;
		rootNode.right = rightNode;
		results.add(rootNode);
	    }
	cache[root][lb][ub] = new NodeList(results);
	return results;
    }

    public static void main(String args[]) {
	new Solution().generateTrees(3);
    }
}
