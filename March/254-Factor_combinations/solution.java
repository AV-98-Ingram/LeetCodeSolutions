class Solution {

    public List<List<Integer>> getFactors(int n) {
	return fac(n, n, 0);
    }
    
    public List<List<Integer>> fac(int n, int m, int prev) {
	List<List<Integer>> results = new LinkedList<>();       
	
	if (n < m && prev <= n) {
	    List<Integer> factors = new LinkedList<>();

	    factors.add(n);
	    results.add(factors);
	}
	for (int i = 2; i < n-1; i++) {
	    if (i < prev || n % i != 0)
		continue;
	    
	    int j = n / i;
	    
	    for (List<Integer> factors : fac(j, m, i)) {
		factors.add(i);
		results.add(factors);
	    }	    
	}
	return results;
    }
}
