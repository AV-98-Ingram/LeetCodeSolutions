
class Solution {

    Map<Node, Node> old2new = new IdentityHashMap<>();
    
    public Node copyRandomList(Node head) {
        Node copy = null;
	Node copyHead = null;
	Node orig = head;
	
	while (orig != null) {
	    Node newCopy = copy(orig);

	    if (copy == null) {
		copy = newCopy;
		copyHead = copy;
	    } else {
		copy.next = newCopy;
		copy = copy.next;
	    }
	    orig = orig.next;
	}
	copy = copyHead;
	while (copy != null) {
	    Node randomCopy = old2new.get(copy.random);

	    copy.random = randomCopy;
	    copy = copy.next;
	}
	return copyHead;
    }

    private Node copy(Node orig) {
	Node copy = new Node(orig.val);

	copy.random = orig.random;
	old2new.put(orig, copy);
	return copy;
    }
}
