class StringIterator {

    char[] chars;
    int pos = 0;
    char next;
    int num;
    
    public StringIterator(String compressedString) {
	chars = compressedString.toCharArray();
    }
    
    public char next() {
	if (hasNext()) {
	    num--;
	    return next;
	}
	return ' ';
    }
    
    public boolean hasNext() {	
	if (num > 0)
	    return true;
	if (pos >= chars.length)
	    return false;	
	// parse next letter:
	do {
	    next = chars[pos++];
	    num = 0;
	    while (pos < chars.length && '0' <= chars[pos] && chars[pos] <= '9') {
		num = num * 10 + chars[pos] - '0';
		pos++;
	    }
	} while (num <= 0 && pos < chars.length);
	return num > 0;
    }
}

