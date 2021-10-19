import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    private static char[] ACTG = new char[] { 'A', 'C', 'T', 'G' };

    public int minMutation(String start, String end, String[] bank) {
	Map<String, String> theBank = new HashMap<>();

	for (String g : bank)
	    theBank.putIfAbsent(g, g);

	String theEnd = theBank.get(end);
	String theStart = theBank.get(start);

	if (theEnd == null)
	    return -1;
	else
	    end = theEnd;
	if (theStart != null)
	    start = theStart;

	int step = 0;
	List<String> workList = new LinkedList<>();
	List<String> nextList = new LinkedList<>();

	workList.add(start);
	while (!workList.isEmpty()) {
	    while (!workList.isEmpty()) {
		String g = workList.remove(0);
		String nxt;
		char[] gchars;

		if (g == end)
		    return step;
		gchars = g.toCharArray();
		// BFS next genes:
		for (int i = 0; i < gchars.length; i++) {
		    char c = gchars[i];

		    for (int j = 0; j < ACTG.length; j++)
			if (ACTG[j] == c)
			    continue;
			else {
			    gchars[i] = ACTG[j];
			    nxt = theBank.get(new String(gchars));
			    if (nxt != null)
				nextList.add(nxt);
			    gchars[i] = c;
			}
		}
		theBank.remove(g);
	    }
	    step++;

	    List<String> tmp = workList;

	    workList = nextList;
	    nextList = tmp;
	}
	return -1;
    }
}
