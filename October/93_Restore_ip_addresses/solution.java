import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    Map<Integer, List<String>> cache = new HashMap<>();

    public List<String> restoreIpAddresses(String s) {
	char[] sArr = s.toCharArray();

	return f(sArr, 0, 4);
    }

    private List<String> f(char[] s, int start, int countdown) {
	List<String> results = new LinkedList<>();

	if (start >= s.length && countdown == 0) {
	    results.add("");
	} else if (start >= s.length || countdown == 0)
	    return results;

	List<String> cached = cache
	    .get(Integer.valueOf(countdown * 3000 + start));
	final int choices;

	if (cached != null)
	    return cached;
	choices = Integer.min(3, s.length - start);
	for (int i = 1; i <= choices; i++) {
	    int val = val(s, start, start + i);
	    String prefix;

	    if (val > 255)
		break;
	    prefix = String.valueOf(val);
	    if (countdown > 1)
		prefix += ".";

	    List<String> suffixes = f(s, start + i, countdown - 1);

	    for (String suffix : suffixes)
		results.add(prefix + suffix);
	    if (s[start] == '0')
		break;
	}
	cache.put(Integer.valueOf(countdown * 3000 + start), results);
	return results;
    }

    private int val(char[] s, int start, int end) {
	int val = 0;

	for (int i = start; i < end; i++) {
	    val = val * 10 + (s[i] - '0');
	}
	return val;
    }

    public static void main(String[] args) {
	for (String s : new Solution().restoreIpAddresses("25525511135"))
	    System.out.println(s);
    }
}
