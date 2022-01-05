class Solution {
    public int firstUniqChar(String s) {
	int alphabet[] = new int[26];
	final int len = s.length();
	
	for (int i = 0; i < len; i++) {
	    char c = s.charAt(i);
	    
	    alphabet[c-'a']++;
	}
	for (int i = 0; i < len; i++) {
	    char c = s.charAt(i);
	    
	    if (alphabet[c-'a'] == 1)
		return i;
	}
	return -1;
    }
}
