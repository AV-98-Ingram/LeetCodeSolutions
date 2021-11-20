class Solution {
    /*

      Backtrack with cached states.  A state is a pair (p, l), where
      'p' is the position in the given string 's' and 'l' is the size
      of the parenthesis matching stack.
      
      Time complexity:  O(n^2) as there are n^2 states total.
      
    */
    Boolean cache[][];
    
    public boolean checkValidString(String s) {
	char[] cs = s.toCharArray();
	
	cache = new Boolean[cs.length][cs.length];
	return f(cs, 0, 0);
    }
    
    private boolean f(char[] s, int pos, int stack) {
	if (pos == s.length)
	    return stack == 0;
	if (stack < 0)
	    return false;
	
	if (cache[pos][stack] != null)
	    return cache[pos][stack];
	
	boolean result;
	
	if (s[pos] == '(')
	    result = f(s, pos+1, stack+1);
	else if (s[pos] == ')') {
	    result = f(s, pos+1, stack-1);
	} else {
	    // three options:
	    result = f(s, pos+1, stack-1) || f(s, pos+1, stack+1) || f(s, pos+1, stack);
	}
	cache[pos][stack] = result;
	return result;
    }
}
