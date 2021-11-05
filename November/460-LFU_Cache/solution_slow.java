import java.util.HashMap;
import java.util.Map;

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

	void incr() {
	    count++;

	    Node place = this;

	    while (place.prev != null) {
		if (place.prev.count > count) {
		    break;
		}
		place = place.prev;
	    }
	    insertBefore(place);
	}

	void insertBefore(Node l) { // requires l != null
	    if (l == this)
		return;

	    Node lPrev = l.prev;
	    Node myPrev = prev;
	    Node myNext = next;

	    prev = lPrev;
	    next = l;
	    l.prev = this;
	    if (lPrev != null)
		lPrev.next = this;
	    if (myPrev != null)
		myPrev.next = myNext;
	    if (myNext != null)
		myNext.prev = myPrev;
	}
    }

    Map<Integer, Node> key2node = new HashMap<>();

    Node head = null, tail = null;

    int cap;

    public LFUCache(int capacity) {
	cap = capacity;
    }

    public int get(int key) {
	Node node = key2node.get(key);

	if (node == null)
	    return -1;
	node.incr();

	// update head & tail:
	if (node == tail) {
	    tail = node;
	    while (tail.next != null)
		tail = tail.next;
	}
	if (node.count >= head.count)
	    head = node;
	return node.value;
    }

    public void put(int key, int value) {
	Node node = key2node.get(key);

	if (node != null) {
	    node.value = value;
	    get(key); // use "get" to update count, head and tail;
	    return;
	}

	if (cap == key2node.size()) {
	    Node removing = tail;

	    if (tail != null) {
		tail = tail.prev;
		if (tail != null)
		    tail.next = null;
		key2node.remove(removing.key);
	    }
	    if (cap == 0)
		return;
	}

	Node newNode = new Node(key, value, tail, null);

	if (tail == null)
	    head = tail = newNode;
	else {
	    tail.next = newNode;
	    tail = newNode;
	}
	key2node.put(key, newNode);
	get(key);
    }

    public static void main(String[] args) {
	LFUCache cache = new LFUCache(2);

	cache.put(2, 1);
	cache.put(1, 1);
	cache.put(2, 3);
	cache.put(4, 1);
	System.out.println(cache.get(1));
	System.out.println(cache.get(2));
	cache.put(3, 3);
	cache.put(4, 4);
	System.out.println(cache.get(3));
    }
}
