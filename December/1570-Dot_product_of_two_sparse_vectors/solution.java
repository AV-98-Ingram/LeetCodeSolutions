class SparseVector {

    ArrayList<int[]> values = new ArrayList<>();
    
    SparseVector(int[] nums) {
	int i = 0;

	for (int n : nums) {
	    if (n != 0)
		values.add(new int[]{n, i});
	    i++;
	}
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
	int e2_i = 0, e2_size = vec.values.size();
	int dp = 0;
	
	for (int[] e1 : values) {
	    int[] e2 = new int[]{0,-1};
	    
	    while (e2_i < e2_size) {
		e2 = vec.values.get(e2_i++);
		if (e2[1] == e1[1]) 
		    break;
		if (e2[1] > e1[1]) {
		    e2_i--;
		    break;
		}	
	    }
	    if (e2[1] == e1[1])
		dp += e2[0] * e1[0];
	}
	return dp;
    }
}
