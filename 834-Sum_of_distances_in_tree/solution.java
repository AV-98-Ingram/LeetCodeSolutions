import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * I didn't think of this solution. The idea of this solution is that to compute
 * the answer[i] for a node i takes O(n). answer[i] can be used to compute the
 * rest of nodes. For example: <code>
 *    ... -  0 - 1 - 2 - ...
 *                \ 3 - 4 - ...  //tree(3, 1) rooted at 3 contains descendant 4.
 * </code> If we know answer[1], answer[3] is defined by <code>
 *      answer[3] = answer[1] + (n - sizeof(tree(3, 1))) - sizeof(tree(3, 1)).
 * </code>, where tree(3, 1) is the tree that is rooted at 3 and contains no 1.
 * 
 * So if we first pick one node i and compute answer[i], every rest of the nodes
 * j can be considered a sub-tree tree(j, i). The sizes of each tree(j, i) can
 * be computed along the computation of answer[i]. Then we propagate answer[j]
 * of every neighbor j of i using answer[i] recursively.
 * 
 * @author ziqing
 */
class Solution {

	public int[] sumOfDistancesInTree(int n, int[][] edges) {
		Map<Integer, List<Integer>> neighbors = new HashMap<>();

		if (n == 1)
			return new int[] { 0 };

		for (int[] edge : edges) {
			List<Integer> neibs = neighbors.computeIfAbsent(edge[0],
					(k) -> new LinkedList<>());

			neibs.add(edge[1]);
			neibs = neighbors.computeIfAbsent(edge[1],
					(k) -> new LinkedList<>());
			neibs.add(edge[0]);
		}

		int root = edges[0][0];
		int[] answers = new int[n];
		/*
		 * numNodes: numNodes[i] is the size of the sub-tree rooted at i. Note
		 * that root is an ancestor of i so root is not in the sub-tree.
		 */
		int[] treeSizes = new int[n];

		answers[root] = getFirstAnswer(root, neighbors, treeSizes);
		propagate(root, -1, neighbors, treeSizes, answers);
		return answers;
	}

	private void propagate(int currNode, int fromNode,
			Map<Integer, List<Integer>> neighbors, int numNodes[],
			int[] answers) {
		// answers[node.root] is known:
		for (int neib : neighbors.get(currNode)) {
			if (neib == fromNode)
				continue;

			answers[neib] = answers[currNode] - numNodes[neib]
					+ (numNodes.length - numNodes[neib]);
			propagate(neib, currNode, neighbors, numNodes, answers);
		}
	}

	private int getFirstAnswer(int root, Map<Integer, List<Integer>> neighbors,
			int[] numNodes) {
		int sum = 0;

		for (int neib : neighbors.get(root)) {
			int result = traverse(neib, root, neighbors, numNodes);
			// ensures numNodes[neib] has been filled
			sum += result;
		}
		return sum;
	}

	// returns sum from descendants to root;
	// ensures numNodes[root] == #nodes in the sub-tree of root (inclusive)
	private int traverse(int root, int parent,
			Map<Integer, List<Integer>> neighbors, int[] numNodes) {
		int sum = 0;

		numNodes[root] = 0;
		for (int neib : neighbors.get(root)) {
			if (neib == parent)
				continue;
			int result = traverse(neib, root, neighbors, numNodes);

			sum += result;
			numNodes[root] += numNodes[neib];
		}
		numNodes[root] += 1;
		return sum + numNodes[root];
	}

	public static void main(String[] args) {
		int n = 6;
		int[][] in = new int[][] { { 0, 1 }, { 0, 2 }, { 2, 3 }, { 2, 4 },
				{ 2, 5 } };
		int[] sum = new Solution().sumOfDistancesInTree(n, in);

		for (int i = 0; i < n; i++)
			System.out.printf("%3d", sum[i]);
	}
}
