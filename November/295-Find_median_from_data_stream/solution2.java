class MedianFinder {

    Queue<Integer> prefix, suffix;
    double median;
    
    public MedianFinder() {
        prefix = new PriorityQueue<>((i,j)->Integer.compare(j, i));
	suffix = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
	if (prefix.isEmpty() && suffix.isEmpty()) {
	    prefix.add(num);
	    median = (double)num;
	    return;
	}
	if (median >= num)
	    prefix.add(num);
	else
	    suffix.add(num);
	balance();
    }
    
    public double findMedian() {
	return median;
    }

    private void balance() {
	while (prefix.size() < suffix.size())	    	    
	    prefix.add(suffix.poll());
	while (prefix.size() > suffix.size() + 1)
	    suffix.add(prefix.poll());
	if (prefix.size() == suffix.size())
	    median = (double)(prefix.peek()  + suffix.peek()) / 2;
	else
	    median = prefix.peek();
    }
}
