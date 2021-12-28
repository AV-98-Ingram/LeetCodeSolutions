class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
	return f(nestedList, 1);
    }

    private int f(List<NestedInteger> nestedList, int depth) {
	if (nestedList.isEmpty())
	    return 0;
	
	List<NestedInteger> nexts = new LinkedList<>();
	int sum = 0;
	
	for (NestedInteger ni : nestedList) {
	    if (ni.isInteger()) 
		sum += ni.getInteger() * depth;
	    else
		nexts.addAll(ni.getList());
	}
	return sum + f(nexts, depth + 1);	
    }
}
