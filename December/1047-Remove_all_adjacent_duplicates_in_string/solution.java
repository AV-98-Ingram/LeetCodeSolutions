class Solution {
    public String removeDuplicates(String s) {
	final int slen = s.length();
	StringBuffer sb = new StringBuffer();

	for (int i = 0; i < slen;) {
	    char c = s.charAt(i++);
	    int len = sb.length();

	    if (len > 0 && sb.charAt(len - 1) == c) {
		sb.delete(len - 1, len);
	    } else
		sb.append(c);
	}
	return sb.toString();
    }
}
