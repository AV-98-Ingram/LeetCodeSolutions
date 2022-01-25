import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Solution {
    // assume each user visit a website a time
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
	final int len = username.length;
	Map<String, Integer> patterns = new HashMap<>();
	Map<String, ArrayList<Integer>> userHist = new HashMap<>();

	for (int i = 0; i < len; i++)
	    userHist.computeIfAbsent(username[i], k -> new ArrayList<>()).add(i);

	for (ArrayList<Integer> hist : userHist.values()) {
	    Collections.sort(hist, (a, b) -> (Integer.compare(timestamp[a], timestamp[b])));
	    final int size = hist.size();
	    Set<String> seen = new HashSet<>();

	    for (int i = 0; i < size; i++)
		for (int j = i + 1; j < size; j++)
		    for (int k = j + 1; k < size; k++) {
			String str = website[hist.get(i)] + "," + website[hist.get(j)] + "," + website[hist.get(k)];

			if (seen.add(str))
			    patterns.merge(str, 1, Integer::sum);
		    }
	}

	int max = 0;
	String maxString = "" + (char) 255;

	for (Entry<String, Integer> entry : patterns.entrySet()) {
	    if (entry.getValue() > max) {
		max = entry.getValue();
		maxString = entry.getKey();
	    } else if (entry.getValue() == max) {
		if (entry.getKey().compareTo(maxString) < 0)
		    maxString = entry.getKey();
	    }
	}
	return Arrays.asList(maxString.split(","));
    }
}
