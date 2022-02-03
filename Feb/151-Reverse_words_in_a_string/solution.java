class Solution {
    public String reverseWords(String s) {
	StringBuffer sb = new StringBuffer();
	final int len = s.length();
	int start = 0;
	
	while (start < len) {
	    int[] word = nextWord(s, start, len);

	    if (word != null) {
		sb.insert(0, s.substring(word[0], word[1]) + " ");
		start = word[1];
	    } else
		break;
	}
	sb.deleteCharAt(sb.length() - 1);
	return sb.toString();
    }
    
    private int[] nextWord(String s, int start, final int len) {
	while (start < len && s.charAt(start) == ' ')
	    start++;
	if (start == len)
	    return null;
	
	int[] ret = new int[2];
	
	ret[0] = start;
	while (start < len && s.charAt(start) != ' ')
	    start++;
	ret[1] = start;
	return ret;
    }
}
