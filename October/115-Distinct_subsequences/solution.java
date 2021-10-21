import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    Integer cache[][];

    public int numDistinct(String s, String t) {
	Map<Character, List<Integer>> places = new HashMap<>();
	char[] sArr = s.toCharArray();
	char[] tArr = t.toCharArray();

	cache = new Integer[t.length()][s.length() + 1];
	for (char c : tArr)
	    places.computeIfAbsent(c, k -> new LinkedList<>());
	for (int i = 0; i < sArr.length; i++) {
	    List<Integer> l = places.get(sArr[i]);

	    if (l != null)
		l.add(i);
	}
	return f(tArr, places, -1, 0, sArr.length);
    }

    private int f(char t[], Map<Character, List<Integer>> places, int prevPlace,
		  int pos, int sLen) {
	if (pos == t.length)
	    return 1;

	Integer cached = cache[pos][prevPlace + 1];
	int ret = 0;

	if (cached != null)
	    return cached;
	for (int place : places.get(t[pos])) {
	    if (sLen - place < t.length - pos)
		// key optimization to pass all LC tests but still not fast enough
		break;
	    if (place > prevPlace)
		ret += f(t, places, place, pos + 1, sLen);
	}
	cache[pos][prevPlace + 1] = ret;
	return ret;
    }

    public static void main(String[] args) {
	System.out.println(
			   new Solution().numDistinct("aaaaaaaaaab", "aaaaaaaaaa"));
    }
}
