class Solution {

    public int lengthLongestPath(String input) {
	return dfs(input.split("\n"), 0, -1)[1];
    }

    private int[] dfs(String[] words, int pos, int parentLevel) {
	if (pos >= words.length)
	    return new int[] { pos, 0 };

	int max = 0;
	int i;

	for (i = pos; i < words.length; i++) {
	    String word = words[i];
	    int level = wordLevel(word);

	    if (level == parentLevel + 1) {
		// child of parent:
		int[] ret = dfs(words, i + 1, level);
		int len;

		i = ret[0] - 1;
		if (ret[1] > 0) {
		    // this is a dir:
		    len = ret[1] + word.length() - level + 1; // add one for the
		    // '/' character
		} else {
		    // this may be a file:
		    len = word.contains(".") ? word.length() - level : 0;
		}
		if (len > max)
		    max = len;
	    } else if (level <= parentLevel) {
		break;
	    }
	}
	return new int[] { i, max };
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
	System.out.print(new Solution().lengthLongestPath(
							  "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    }
}
