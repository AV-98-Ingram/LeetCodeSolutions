class Solution {
    public boolean isHappy(int n) {
	Set<Integer> seen = new HashSet<>();
	int m;
	
	do {
	    seen.add(n);
	    m = 0;
	    while (n > 0) {
		int divByTen = n / 10;
		int digit = n - divByTen * 10;
		
		m += digit*digit;
		n = divByTen;
	    }
	    n = m;
	} while (m != 1 && !seen.contains(m));
	if (m == 1)
	    return true;
	return false;
    }
}
