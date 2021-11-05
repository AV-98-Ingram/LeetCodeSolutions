class LRUCache {

    class Node {
	int key, val;
	Node next, prev;
	Node(int key, int val, Node prev, Node next) {
	    this.key = key;
	    this.val = val;
	    this.prev = prev;
	    this.next = next;
	}
    }
    
    Map<Integer, Node> map = new HashMap<>(); // keys to List Nodes
    Node head = null, tail = null;
    int cap;
    
    public LRUCache(int capacity) {
	cap = capacity;
    }
    
    public int get(int key) {
	Node node = map.get(key);

	if (node == null)
	    return -1;
	if (map.size() == 1)
	    return node.val;
	
	Node prev = node.prev;
	Node next = node.next;
	
	if (prev != null) 
	    prev.next = next;   // 1(h,t) ->  2(n)
	else
	    head = next;
	if (next != null)
	    next.prev = prev;
	else
	    tail = prev;
	tail.next = node;
	node.next = null;
	node.prev = tail;
	tail = node;
	return node.val;
    }
    
    public void put(int key, int value) {
        int oldVal = get(key);

	if (oldVal == -1) {
	    if (tail == null) {
		head = tail = new Node(key, value, null, null);
		map.put(key, head);
		return;
	    }
	    tail.next = new Node(key, value, tail, null);
	    map.put(key, tail.next);
	    tail = tail.next;
	    if (map.size() > cap) {
		map.remove(head.key);
		head = head.next;
		head.prev = null;
	    }
	} else
	    map.get(key).val = value;
    }
}
