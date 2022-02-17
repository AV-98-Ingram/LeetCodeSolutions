import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

class Solution {

    // parallel version
	
    ConcurrentMap<String, List<String>> table = new ConcurrentHashMap<>(); // file contents -> file paths

    public List<List<String>> findDuplicate(String[] paths) {

	Arrays.stream(paths).parallel().forEach(s -> populate(s));
	return table.values().parallelStream().filter(l -> l.size() > 1).collect(Collectors.toList());
    }

    private void populate(String path) {
	String[] groups = path.split(" ");
	String absPath = groups[0];

	for (int i = 1; i < groups.length; i++) {
	    String file = groups[i];
	    StringBuffer name = new StringBuffer(), content = new StringBuffer();
	    int j = 0;
	    final int len = file.length();

	    while (j < len) {
		char c = file.charAt(j++);

		if (c == '(')
		    break;
		name.append(c);
	    }
	    while (j < len) {
		char c = file.charAt(j++);

		if (c == ')')
		    break;
		content.append(c);
	    }
	    table.computeIfAbsent(content.toString(), key -> new LinkedList<>()).add(absPath + "/" + name.toString());
	}
    }
}
