
public class NestedIterator implements Iterator<Integer> {

    LinkedList<Iterator<NestedInteger>> stack = new LinkedList<>();

    Integer next;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        stack.add(nestedList.iterator());
    }

    @Override
    public Integer next() {
	return next;
    }

    @Override
    public boolean hasNext() {
	while (!stack.isEmpty()) {
	    if (stack.getLast().hasNext()) {
		NestedInteger nxtNI = stack.getLast().next();

		if (nxtNI.isInteger()) {
		    next = nxtNI.getInteger();
		    return true;
		} else {
		    stack.add(nxtNI.getList().iterator());
		    return hasNext();
		}
	    }
	    stack.removeLast();
	}	
	return false;
    }
}


