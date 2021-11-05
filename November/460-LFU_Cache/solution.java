import java.util.HashMap;
import java.util.Map;

/**
  Made two attempts.
 
  The first attempt is simpler and slower.  It maintains a single
  ordered list of nodes and a map from key to nodes.  Every time a
  node is accessed, the counter of the node increases by one.  Then
  the node will be moved ahead in the ordered list.  When the capacity
  is full, allways remove the tail of the ordered list.

  The second attempt is faster.  It maintains two maps.  A map from
  key to nodes and a map from counter to lists.  For all the nodes
  sharing the same counter value, they are stored as a list in
  most-recent visited order in the second map.  In addition, use a
  field "min" to keep track of the minimum counter.  When an existing
  node is accessed, it will be cut off from its old list and insert at
  the head of the new list (with the counter greater than the counter
  of the old list by one).  When to remove, just remove the tail of
  the list of the min counter.
 */
class LFUCache {

    class Node {
	int key;
	int value;
	int count;
	Node prev, next;

	Node(int key, int val, Node prev, Node next) {
	    this.key = key;
	    this.value = val;
	    this.prev = prev;
	    this.next = next;
	    this.count = 0;
	}
    }

    Map<Integer, Node> key2node = new HashMap<>();
    Map<Integer, Node[]> count2list = new HashMap<>();

    int min = 0;

    int cap;

    public LFUCache(int capacity) {
	cap = capacity;
    }

    public int get(int key) {
	Node node = key2node.get(key);

	if (node == null)
	    return -1;
	incr(key, node);
	return node.value;
    }

    public void put(int key, int value) {
	Node node = key2node.get(key);

	if (node != null) {
	    node.value = value;
	    incr(key, node);
	    return;
	}

	node = new Node(key, value, null, null);
	if (cap == key2node.size()) {
	    removeLF();
	}
	if (cap > key2node.size()) {
	    key2node.put(key, node);
	    // add to list of count == 1:
	    node.count = 1;

	    Node[] list = count2list.computeIfAbsent(1,
						     k -> new Node[] { null, null });

	    if (list[0] == null) {
		list[0] = node;
		list[1] = node;
	    } else {
		node.next = list[0];
		list[0].prev = node;
		list[0] = node;
	    }
	    // reset min to 1 after new node gets added:
	    min = 1;
	}
    }

    private void removeLF() {
	if (min <= 0) // cap is 0, nothing to remove and nothing will be added
	    return;

	Node[] list = count2list.get(min);
	Node head = list[0];
	Node tail = list[1];

	key2node.remove(tail.key);
	// remove tail from list:
	if (head != tail) {
	    tail = tail.prev;
	    tail.next = null;
	} else {
	    head = null;
	    tail = null;
	}
	list[0] = head;
	list[1] = tail;
	// update min:
	if (key2node.size() > 0)
	    while (count2list.get(min) == null
		   || count2list.get(min)[0] == null)
		min++;
	else
	    min = 0;
    }

    private void incr(int key, Node node) {
	// remove it from the current list:
	Node[] oldList = count2list.get(node.count);
	Node oldPrev = node.prev;
	Node oldNext = node.next;

	if (oldPrev == null)
	    oldList[0] = oldNext;
	else
	    oldPrev.next = oldNext;
	if (oldNext == null)
	    oldList[1] = oldPrev;
	else
	    oldNext.prev = oldPrev;
	// add it to the new list:
	node.count++;

	Node[] newList = count2list.computeIfAbsent(node.count,
						    k -> new Node[] { null, null });

	node.prev = null;
	if (newList[0] == null) {
	    newList[0] = newList[1] = node;
	    node.next = null;
	} else {
	    node.next = newList[0];
	    newList[0].prev = node;
	    newList[0] = node;
	}
	// update min:
	if (count2list.get(min) == null || count2list.get(min)[0] == null)
	    min++;
    }

    public static void main(String[] args) {
	LFUCache cache = new LFUCache(1);

	cache.put(2, 1);
	System.out.println(cache.get(2));
	cache.put(3, 2);
	System.out.println(cache.get(2));
	System.out.println(cache.get(3));
	cache.put(2, 3);
	cache.put(4, 1);
	cache.put(3, 3);
	cache.put(4, 4);
	System.out.println(cache.get(3));
    }
}
