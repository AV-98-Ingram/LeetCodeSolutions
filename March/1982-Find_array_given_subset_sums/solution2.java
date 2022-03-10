class Solution {
    public int[] recoverArray(int n, int[] sums) {
	int[] ret = new int[n];
	ArrayList<Integer> l = new ArrayList<>(n);
	
	for (int num : sums)
	    l.add(num);
	find(sums, ret, n);
	return ret;
    }
    
    
    private boolean find(ArrayList<Integer> sums, int output[], int n) {
	if (n == 0)
	    return true;
	
	Collections.sort(sums);

	Map<Integer, Integer> table = new HashMap<>();
	Map<Integer, Integer> table2;	
	int size = sums.size();
	int x = sums[size-1] - sums[size-2];
	
	for (int num : sums)
	    table.merge(num, 1, Integer::sum);

	boolean hasMinusX = table.containsKey(-x);

	if (hasMinusX)
	    table2 = new HashMap<>(table);	
	if (table.containsKey(x)) {	    
	    ArrayList<Integer> exluding = new ArrayList<>(size / 2 + 1);
	    
	    for (int num : sums) {
		int count = table.computeIfPresent(num, (k,v)->v-1);
		
		if (count > 0) {
		    count = table.computeIfPresent(num + x, (k,v)->v-1);
		    if (count > 0)
			excluding.add(num);
		    else
			return false;
		}
	    }
	    output[n-1] = x;
	    if (find(excluding, output, n-1))
		return true;
	}
	if (hasMinusX) {
	    table = table2;
	    
	    ArrayList<Integer> exluding = new ArrayList<>(size / 2 + 1);
	    
	    for (int i = size-1; i >= 0; i++) {
		int num = sums.get(i);
		int count = table.computeIfPresent(num, (k,v)->v-1);
		
		if (count > 0) {
		    count = table.computeIfPresent(num - x, (k,v)->v-1);
		    if (count > 0)
			excluding.add(num);
		    else
			return false;
		}
	    }
	    output[n-1] = -x;
	    if (find(excluding, output, n-1))
		return true;
	} 
	return false;		     
    }
}
