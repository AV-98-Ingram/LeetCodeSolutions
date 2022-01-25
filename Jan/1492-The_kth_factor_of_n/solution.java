class Solution {
    public int kthFactor(int n, int k) {
	int counter = 0;
	int sqrt = (int) Math.sqrt(n);
	ArrayList<Integer> factors = new ArrayList<>();
	
	for (int i = 1; i <= sqrt; i++) {
	    if (n % i != 0)
		continue;
	    counter++;	    
	    if (counter == k)
		return i;
	    factors.add(i);
	}

	int last = factors.get(factors.size()-1);
	int next = n / last;
	int total = next > Last ? counter * 2 : counter * 2 - 1;

	if (k > total)
	    return -1;
	if (next > last)
	    return n / factors.get(counter - (k - counter));
	else
	    return n / factors.get(counter - (k - counter) - 1);
    }
}
