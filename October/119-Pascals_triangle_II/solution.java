class Solution {
    public List<Integer> getRow(int rowIndex) {
	int numRows = rowIndex + 1;
	List<Integer> result = new ArrayList<>();
	
	if (numRows >= 1)
	    result.add(1);
	if (numRows >= 2)
	    result.add(1);
	for (int i = 2; i < numRows; i++) {
	    int prev = result.get(0);
	    
	    for (int j = 1; j < i; j++) {
		int nextPrev = result.get(j);
		
		result.set(j, nextPrev + prev);
		prev = nextPrev;
	    }
	    result.add(1);
	}
	return result;
    }
}
