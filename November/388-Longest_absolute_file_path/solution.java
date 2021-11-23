import java.util.LinkedList;
import java.util.List;

class Solution {
    /* I first parse the string to a tree and use the tree to find the longest file path.
       Could be improved by on-the-fly tree traversal (DFS) I think (see solution2).
     */
	class Tree {
		List<Tree> children;
		final String name;

		Tree(String name) {
			this.name = name;
			this.children = new LinkedList<>();
		}

		void addChild(Tree child) {
			this.children.add(child);
		}

		int maxPath() {
			int nameLength = name == null ? 0 : name.length();

			if (!children.isEmpty()) {
				int max = 0;
				for (Tree child : children) {
					int childMax = child.maxPath();

					if (childMax > max)
						max = childMax;
				}
				if (max > 0) {
					if (nameLength > 0)
						nameLength++;// for the '/' character
					return nameLength + max;
				}
				return 0;
			}
			if (name != null && name.contains("."))
				return nameLength;
			return 0;
		}
	}

	public int lengthLongestPath(String input) {
		Tree root = parse(input);

		return root.maxPath();
	}

	private Tree parse(String input) {
		String[] words = input.split("\\n");
		Tree root = new Tree(null);

		parseWorker(words, 0, root, -1);
		return root;
	}

	// return the position i in "words" such that words[0 .. (i-1)]
	// have all been parsed already.
	private int parseWorker(String[] words, int pos, Tree parent,
			int parentLevel) {
		if (pos >= words.length)
			return words.length;

		Tree tree = null;

		for (int i = pos; i < words.length; i++) {
			String word = words[i];
			int wordLevel = wordLevel(word);

			if (wordLevel == parentLevel + 1) {
				String name = word.substring(wordLevel);

				tree = new Tree(name);
				parent.addChild(tree);
			} else if (wordLevel > parentLevel + 1) {
				assert tree != null;
				i = parseWorker(words, i, tree, parentLevel + 1);
				i--; // next iteration we process words[i]
			} else if (wordLevel <= parentLevel)
				return i;
		}
		return words.length;
	}

	private int wordLevel(String word) {
		final int ub = word.length();
		int level = 0;

		for (int i = 0; i < ub; i++) {
			if (word.charAt(i) == '\t')
				level++;
			else
				break;
		}
		return level;
	}

	public static void main(String[] agrs) {
		System.out.print(new Solution()
				.lengthLongestPath("file1.txt\nfile2.txt\nlongfile.txt"));
	}
}
