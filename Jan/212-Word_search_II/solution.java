import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    class Trie {
	Map<Character, Trie> children = new HashMap<>();
	String word = null;

	Trie addChild(char c) {
	    return children.computeIfAbsent(c, k -> new Trie());
	}
    }

    public List<String> findWords(char[][] board, String[] words) {
	Trie root = new Trie();

	for (String w : words) {
	    Trie child = root;

	    for (char c : w.toCharArray())
		child = child.addChild(c);
	    child.word = w;
	}

	final int nr = board.length;
	final int nc = board[0].length;
	List<String> results = new LinkedList<>();

	for (int i = 0; i < nr; i++)
	    for (int j = 0; j < nc; j++)
		findWord(i, j, board, nr, nc, root, results);
	return results;
    }

    private void findWord(int i, int j, char[][] board, int nr, int nc, Trie root, List<String> words) {
	char c = board[i][j];

	if (c == 0)
	    return;

	Trie trie = root.children.get(c);

	if (trie == null)
	    return;
	if (trie.word != null) {
	    words.add(trie.word);
	    trie.word = null;
	}
	if (trie.children.isEmpty())
	    return;
	board[i][j] = 0;
	if (i > 0)
	    findWord(i - 1, j, board, nr, nc, trie, words);
	if (j > 0)
	    findWord(i, j - 1, board, nr, nc, trie, words);
	if (i < nr - 1)
	    findWord(i + 1, j, board, nr, nc, trie, words);
	if (j < nc - 1)
	    findWord(i, j + 1, board, nr, nc, trie, words);
	board[i][j] = c;
    }
}
