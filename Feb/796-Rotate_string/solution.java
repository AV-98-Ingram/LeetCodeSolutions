class Solution {
    public boolean rotateString(String s, String goal) {
	final int len = s.length();

	if (goal.length() != len)
	    return false;

	int head = 0;
	int i = 0, j = 0;

	while (i < len) {
	    if (s.charAt(i) != goal.charAt(j)) {
		if (j == 0)     // this branch is triky
		    head = ++i;		
		else
		    j = 0;
	    } else {
		i++;
		j++;
	    }
	}
	// checked s[head .. len) == goal[0 .. j)
	// next, check s[0 .. head) == goal[j .. len)
	i = 0;
	while (j < len)
	    if (s.charAt(i) != goal.charAt(j))
		return false;
	    else {
		i++;
		j++;
	    }
	return true;
    }
}
