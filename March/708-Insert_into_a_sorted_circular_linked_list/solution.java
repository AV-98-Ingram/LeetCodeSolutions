class Solution {
    public Node insert(Node head, int insertVal) {
	// handle special case where head == null:
	if (head == null) {
	    Node node = new Node(insertVal);

	    node.next = node;
	    return node;
	}

	Node node = head;
	Node min = node, max = node;
	Node minPrev = null;
	Node prev = null;

	// Iterates through the circular list and finds out the min
	// node and the max node.  The min and the max node can be
	// found at such node n that "n.next.val < n.val".
	
	// During iteration, if there is a node such that "node.val ==
	// insertVal" or "node.val < insertVal < node.next.val", then
	// it immediately is the good place to insert.  Otherwise, the
	// insertVal is either less than min for greater than max.
	do {
	    if (node.val > node.next.val) {
		max = node;
		min = node.next;
		minPrev = node;
	    }
	    if ((node.val < insertVal && node.next.val > insertVal) ||
		node.val == insertVal) {
		node.next = new Node(insertVal, node.next);
		return head;
	    }
	    prev = node;
	    node = node.next;
	} while (node != head);
	assert prev != null;
	if (minPrev == null)
	    minPrev = prev;
	if (insertVal < min.val) {
	    minPrev.next = new Node(insertVal, min);
	} else if (insertVal > max.val) {	    
	    max.next = new Node(insertVal, max.next);
	}
	return head;
    }
}
