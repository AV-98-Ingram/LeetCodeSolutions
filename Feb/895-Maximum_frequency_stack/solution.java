class FreqStack {

    private static List<Integer> emptyLst = new LinkedList<>();    
    private int max = 0;
    private Map<Integer, LinkedList<Integer>> stacks = new HashMap<>(); // freq -> vals
    private Map<Integer, Integer> table = new HashMap<>(); // val -> freq
        
    public FreqStack() {
        
    }
    
    public void push(int val) {
	int freq = table.getOrDefault(val, 0);

	stacks.computeIfAbsent(freq + 1, k -> new LinkedList<>()).add(val);
	max = Math.max(max, freq + 1);
    }
    
    public int pop() {
	List<Integer> vals = stacks.get(max);
	int val = vals.removeFirst();

	if (vals.isEmpty()) {
	    stacks.remove(max);
	    while (!stacks.contains(max))
		max--;
	}
	table.computeIfPresent(val, (k,v)->v-1);
	return val;
    }
}
