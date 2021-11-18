class MedianFinder {

    ArrayList<Integer> nums = new ArrayList<>();

    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
	int idx = insertIndex(num, 0, nums.size());

	if (idx < nums.size())
	    nums.add(idx, num);
	else
	    nums.add(num);
    }
    
    public double findMedian() {
        int size = nums.size();

	if (size % 2 == 0) {
	    return (double)(nums.get(size/2) + nums.get(size/2-1)) / 2.0;
	} else
	    return nums.get(size / 2);
    }

    // requires from < to;
    private int insertIndex(int val, int from, int to) {
	if (from >= to) 	    
	    return from;
	
	int mid = (to - from) / 2 + from;
	int midVal = nums.get(mid);
	       	
	if (midVal > val) 
	    return insertIndex(val, from, mid);
	else {
	    return insertIndex(val, mid + 1, to);	    
	}
    }
}
