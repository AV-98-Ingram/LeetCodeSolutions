import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    Map<String, List<String>> table = new HashMap<>(); // file contents -> file paths

    public List<List<String>> findDuplicate(String[] paths) {
	for (String str : paths)
	    populate(str);

	List<List<String>> result = new LinkedList<>();

	for (List<String> val : table.values())
	    if (val.size() > 1)
		result.add(val);
	return result;
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
