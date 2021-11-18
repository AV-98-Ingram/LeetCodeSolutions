import java.util.ArrayList;
import java.util.Random;

/**
 * A generic AVL tree implementation.
 * 
 * <p>
 * An interesting lesson I learned: Suppose one is about to insert 6 into a
 * given AVL tree <code>
 *         3
 *        / \
 *       1   8
 *          / \
 *         5   9
 * </code>, I thought there are two ways to insert it. One is the typical way,
 * where 6 is inserted as a new leaf node and is the right child of 5. The
 * "other" is to insert 6 in between the 3 and 8, forming <code>
 *        3
 *       / \
 *      1   6
 *           \
 *            8
 *           / \
 *          5   9
 * </code>. Obviously, the latter way is wrong as it is not necessarily the case
 * that all nodes under 8 are greater than 6.
 * </p>
 * 
 * @author ziqing
 *
 * @param <T>
 */
class AVLTree<T extends Comparable<T>> {
	private AVLTree<T> left;
	private AVLTree<T> right;
	private T val;

	AVLTree(AVLTree<T> left, AVLTree<T> right, T val) {
		this.left = left;
		this.right = right;
		this.val = val;
	}

	/**
	 * @return the height of this tree
	 */
	public int height() {
		if (left == null && right == null)
			return 1;
		if (left == null)
			return right.height() + 1;
		if (right == null)
			return left.height() + 1;
		return Math.max(left.height() + 1, right.height() + 1);
	}

	/**
	 * @return the left tree
	 */
	public AVLTree<T> left() {
		return this.left;
	}

	/**
	 * @return the right tree
	 */
	public AVLTree<T> right() {
		return this.right;
	}

	/**
	 * 
	 * @return the value associated with the root node of the tree
	 */
	public T getVal() {
		return this.val;
	}

	public AVLTree<T> insert(T val) {
		int comp = this.val.compareTo(val);

		if (comp < 0) {
			// insert at right:
			if (this.right == null)
				this.right = new AVLTree<>(null, null, val);
			else
				this.right = this.right.insert(val);
		} else {
			// insert at left:
			if (this.left == null)
				this.left = new AVLTree<>(null, null, val);
			else
				this.left = this.left.insert(val);
		}
		return selfBalance();
	}

	/**
	 * TODO: test
	 * 
	 * @return either the tree such that
	 *         <code>tree.val.compareTo(val) == 0</code> or null
	 */
	public AVLTree<T> get(T val) {
		int comp = val.compareTo(val);

		if (comp == 0)
			return this;
		if (comp < 0) {
			if (this.right != null)
				return this.right.get(val);
			return null;
		} else {
			if (this.left != null)
				return this.left.get(val);
			return null;
		}
	}

	// TODO: test
	public AVLTree<T> delete(T val) {
		int comp = this.val.compareTo(val);

		if (comp == 0) {
			if (left != null) {
				AVLTree<T> leftRight = left.right;

				if (right != null) {
					left.right = right;

					AVLTree<T> rightTree = right;

					// append leftRight to the leftmost of the right tree:
					while (rightTree.left != null)
						rightTree = rightTree.left;
					rightTree.left = leftRight;
				} else
					left.right = leftRight;
				return left.selfBalance();
			} else if (right != null)
				return right.selfBalance();
			return null;
		} else if (comp > 0) {
			if (left != null)
				return left.delete(val);
			return this;
		} else {
			if (right != null)
				return right.delete(val);
			return this;
		}
	}

	/**
	 * <code>
	     b              a
	    / \    =>      / \ 
	   a   c          d   b
	  / \                / \
	 d   e              e   c
	 </code>
	 * 
	 * requires tree != null && tree.left() != null;
	 */
	private AVLTree<T> rRotate() {
		AVLTree<T> newRoot = this.left;

		this.left = newRoot.right;
		newRoot.right = this;
		return newRoot;
	}

	/**
	 * <code>
	     b                c
	    / \              / \
	   a   c   =>       b   e
	      / \          / \
	     d   e        a   d 
	 </code>
	 * 
	 * requires tree != null && tree.right() != null;
	 */
	private AVLTree<T> lRotate() {
		AVLTree<T> newRoot = this.right;

		this.right = newRoot.left;
		newRoot.left = this;
		return newRoot;
	}

	/**
	 * self-balance spec: If <code>
	 *  1                           
	 *   \      left-rotate(1)    2 
	 *    2          =>          / \
	 *     \                    1   3
	 *      3
	 *  </code>
	 * 
	 * If <code>
	 *    1                               1                                          4
	 *     \                               \                                        / \
	 *      5        right-rotate(5)        4          left-rotate(1)              1   5 
	 *     / \            =>               / \              =>                    /     \
	 *    4   6                           3   5                                  3       6
	 *   /                                     \
	 *  3                                       6
	 *  
	 *  </code>
	 * 
	 * @return
	 */
	private AVLTree<T> selfBalance() {
		int bal = this.balance();

		while (bal <= -2 || bal >= 2) {
			if (bal >= 2) {
				int leftBal = this.left.balance();

				if (leftBal >= 0) {
					return this.rRotate();
				} else {
					this.left = this.left.lRotate();
					return this.rRotate();
				}
			} else if (bal <= -2) {
				int rightBal = this.right.balance();

				if (rightBal >= 0) {
					this.right = this.right.rRotate();
					return this.lRotate();
				} else {
					return this.lRotate();
				}
			}
		}
		return this;
	}

	/**
	 * @return <code>height(tree.left) - height(tree.right)</code>
	 */
	private int balance() {
		int lh = this.left == null ? 0 : this.left.height();
		int rh = this.right == null ? 0 : this.right.height();
		return lh - rh;
	}

	public String toString() {
		return print("");
	}

	private String print(String prefix) {
		String result = val.toString() + "\n";

		if (left == null && right == null)
			return result;
		if (left != null) {
			result += prefix + "| " + left.print(prefix + "|");
		} else
			result += prefix + "| " + "\n";
		if (right != null) {
			result += prefix + "| " + right.print(prefix + "|");
		} else
			result += prefix + "| " + "\n";
		return result;
	}

	/* *********************** testers *************************** */
	public static void main(String[] args) {
		Random random = new Random(10088);
		AVLTree<Integer> tree = new AVLTree<>(null, null,
				random.nextInt() % 10000);

		for (int i = 0; i < 10000; i++) {
			if (!checkInvariant(tree)) {
				System.err.println("error!");
				System.out.print(tree);
				checkInvariant(tree);
				return;
			}
			tree = tree.insert(random.nextInt() % 1000);
		}
	}

	// invariants:
	static private <D extends Comparable<D>> boolean checkInvariant(
			AVLTree<D> tree) {
		return balanced(tree) && ordered(tree);
	}

	static private boolean balanced(AVLTree<?> tree) {
		if (tree == null)
			return true;

		int b = tree.balance();

		if (-1 <= b && b <= 1)
			return balanced(tree.left) && balanced(tree.right);
		return false;
	}

	static private <D extends Comparable<D>> boolean ordered(AVLTree<D> tree) {
		ArrayList<D> out = new ArrayList<>();

		inOrderVisit(tree, out);

		final int ub = out.size();

		for (int i = 1; i < ub; i++)
			if (out.get(i - 1).compareTo(out.get(i)) > 0)
				return false;
		return true;
	}

	static private <D extends Comparable<D>> void inOrderVisit(AVLTree<D> tree,
			ArrayList<D> out) {
		if (tree == null)
			return;
		inOrderVisit(tree.left, out);
		out.add(tree.val);
		inOrderVisit(tree.right, out);
	}
}
