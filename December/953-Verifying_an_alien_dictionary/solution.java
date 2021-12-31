class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] alphabet = new int[26];
	int i = 0;
	final int orderLen = order.length();
	
	while (i < orderLen) {
	    char c = order.charAt(i++);

	    alphabet[c - 'a'] = i;
	}
	i = 1;
	for (; i < words.length; i++)
	    if (compare(words[i-1], words[i], alphabet) > 0)
		return false;
	return true;
    }

    private int compare(String w1, String w2, int[] alphabet) {
	final int len = Math.min(w1.length(), w2.length());
	
	for (int i = 0; i < len; i++) {
	    char c1 = w1.charAt(i);
	    char c2 = w2.charAt(i);

	    if (alphabet[c1-'a'] != alphabet[c2-'a'])
		return Integer.compare(alphabet[c1-'a'], alphabet[c2-'a']);	    
	}
	return Integer.compare(w1.length(), w2.length());
    }
}
