import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

class WordFilter {

	class PrefixTree {
		Map<Character, PrefixTree> children = new HashMap<>();
		BitSet ids = new BitSet();
	}

	Map<Character, PrefixTree> prefixTrees = new HashMap<>();
	Map<Character, PrefixTree> suffixTrees = new HashMap<>();
	String[] words;

	public WordFilter(String[] words) {
		this.words = words;
		buildTree();
	}

	private void buildTree() {
		int id = 0;
		for (String word : words) {
			PrefixTree nextTree = prefixTrees.computeIfAbsent(word.charAt(0),
					k -> new PrefixTree());
			final int len = word.length();

			nextTree.ids.set(id);
			for (int i = 1; i < len; i++) {
				char c = word.charAt(i);

				nextTree = nextTree.children.computeIfAbsent(c,
						k -> new PrefixTree());
				nextTree.ids.set(id);
			}
			nextTree = suffixTrees.computeIfAbsent(word.charAt(len - 1),
					k -> new PrefixTree());
			nextTree.ids.set(id);
			for (int i = len - 2; i >= 0; i--) {
				char c = word.charAt(i);

				nextTree = nextTree.children.computeIfAbsent(c,
						k -> new PrefixTree());
				nextTree.ids.set(id);
			}
			id++;
		}
	}

	public int f(String prefix, String suffix) {
		final int plen = prefix.length();
		final int slen = suffix.length();
		PrefixTree ptree = prefixTrees.get(prefix.charAt(0));

		for (int i = 1; i < plen; i++)
			if (ptree == null)
				return -1;
			else
				ptree = ptree.children.get(prefix.charAt(i));

		PrefixTree stree = suffixTrees.get(suffix.charAt(slen - 1));

		for (int i = slen - 2; i >= 0; i--)
			if (stree == null)
				return -1;
			else
				stree = stree.children.get(suffix.charAt(i));

		if (ptree == null || stree == null)
			return -1;

		BitSet intersect = (BitSet) ptree.ids.clone();

		intersect.and(stree.ids);
		if (intersect.isEmpty())
			return -1;
		return intersect.length() - 1;
	}

	public static void main(String[] args) {
		new WordFilter(new String[] { "testtest", "tesapplest", "others" })
				.f("tes", "est");
	}
}

