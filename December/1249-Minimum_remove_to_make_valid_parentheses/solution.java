class Solution {
    public String minRemoveToMakeValid(String s) {
        int closes = 0;
	LinkedList<Integer> opens = new LinkedList<>();
	final int len = s.length();

	for (int i = 0; i < len; i++) {
	    char c = s.charAt(i);

	    if (c == ')') {
		if (!opens.isEmpty())
		    opens.removeFirst();
		else 
		    closes++;
	    }
	    else if (c == '(')
		opens.addFirst(i);
	}
	// closes and opens now are the minimum extra close and open
	// parenthesis needed to remove
	StringBuffer sb = new StringBuffer();

	for (int i = 0; i < len; i++) {
	    if (!opens.isEmpty() && i == opens.getLast()) {
		opens.removeLast();
		continue;
	    }

	    char c = s.charAt(i);

	    if (c == ')' && closes > 0) {
		closes--;
		continue;
	    }
	    sb.append(c);
	}		
	return sb.toString();
    }
}
