import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {

    class Wrapper {
	List<List<String>> parts = new LinkedList<>();
    }

    public List<List<String>> partition(String s) {
	char[] chs = s.toCharArray();
	Wrapper[] hist = new Wrapper[chs.length];

	p(chs, chs.length, hist);
	return hist[chs.length - 1].parts;
    }

    private void p(char[] chs, int end, Wrapper[] hist) {
	if (end == 1) {
	    hist[0] = new Wrapper();

	    List<String> part = new LinkedList<>();

	    part.add(String.valueOf(chs[0]));
	    hist[0].parts.add(part);
	    return;
	}

	p(chs, end - 1, hist);

	hist[end - 1] = new Wrapper();
	// propagate from chs[end-1] to chs[0]:
	for (int i = 0; i < end; i++)
	    if (isPalindrome(chs, i, end))
		if (i > 0)
		    hist[end - 1].parts.addAll(combine(hist[i - 1].parts,
						       new String(Arrays.copyOfRange(chs, i, end))));
		else {
		    List<String> part = new LinkedList<>();

		    part.add(new String(Arrays.copyOfRange(chs, i, end)));
		    hist[end - 1].parts.add(part);
		}
	return;
    }

    private boolean isPalindrome(char[] chs, int start, int end) {
	end = end - 1;
	while (start <= end) {
	    if (chs[start++] != chs[end--])
		return false;
	}
	return true;
    }

    private List<List<String>> combine(List<List<String>> ll, String newStr) {
	List<List<String>> results = new LinkedList<>();

	for (List<String> l : ll) {
	    List<String> newL = new LinkedList<>(l);

	    newL.add(newStr);
	    results.add(newL);
	}
	return results;
    }

    public static void main(String[] args) {
	new Solution().partition("aabaa");
    }
}
