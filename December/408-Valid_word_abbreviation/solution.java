class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
	final int lenw = word.length();
	final int lena = abbr.length();
        int i = 0, j = 0;

	while (i < lenw && j < lena) {
	    int skip = 0;
	    
	    while (j < lena && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
		skip = skip * 10 + (abbr.charAt(j) - '0');
		if (skip == 0)
		    return false;
		j++;
	    }
	    if (skip > 0) {
		i += skip;
		continue;
	    }
	    if (word.charAt(i) == abbr.charAt(j)) {
		i++;
		j++;
	    } else
		return false;
	}
	return i == lenw && j == lena;
    }    
}
