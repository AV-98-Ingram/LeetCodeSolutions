class Solution {
    /*
      Things to be CAREFUL with:
      
      1. From the leftmost digit d to right, if there is a maximum
      digit d' on d' left, swap d and d'.
      
      2. If there are multiple such d' with the same value, pick the
       far-most to the right.

     */
    public int maximumSwap(int num) {
	LinkedList<Integer> digits = new LinkedList<>();
	 // maxs[i]: the max digit (& its index) in the [0 .. i]-th
	 // digits.  When there are multiple identical max digits,
	 // keep the one with the smallest index
	ArrayList<int[]> maxs = new ArrayList<>();
	int dig;
	int i = 0;
	
	if (num == 0)
	    return 0;
	while (num > 0) {
	    dig = num % 10;
	    digits.addFirst(dig);
	    num -= dig;
	    num /= 10;

	    if (i > 0) {
		int[] prevMax = maxs.get(maxs.size()-1);

		// if the max of all the digits on the right is
		// identical to me, keep the one on the right:
		if (prevMax[0] >= dig)  
		    maxs.add(prevMax);
		else if (prevMax[0] < dig)
		    maxs.add(new int[] {dig, i});
	    } else
		maxs.add(new int[] {dig, i});
	    i++;
	}
	
	int ret = 0;
	boolean swapped = false;
	int swapIdx = -1, swapDig = -1;

	i = maxs.size() - 1;
	while (!digits.isEmpty()) {
	    dig = digits.removeFirst();
	    if (swapped) {
		if (i == swapIdx) 
		    ret = ret * 10 + swapDig;
		else
		    ret = ret * 10 + dig;
	    } else if (maxs.get(i)[1] != i &&
		       // need to avoid to swap i-th digit d with the
		       // one on d's right that is identical to d:
		       maxs.get(i)[0] != dig) {
		ret = ret * 10 + maxs.get(i)[0];
		swapIdx = maxs.get(i)[1];
		swapDig = dig;
		swapped = true;
	    } else
		ret = ret * 10 + dig;
	    i--;
	}	
	return ret;
    }
}
