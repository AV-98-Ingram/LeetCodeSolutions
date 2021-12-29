class Solution {
    // if random(sum) falls in between (x, y) (inclusive) and (x + w[y], y + 1)
    // (exclusive) in the following BST, return y.
    TreeMap<Integer, Integer> places = new TreeMap<>();
    Random rand = new Random();
    int sum;
    
    public Solution(int[] w) {	
	int i = 0;

	sum = 0;	
	for (int n : w) {
	    places.put(sum, i++);
	    sum += n;
	}
    }
    
    public int pickIndex() {
	int pick = rand.nextInt(sum);

	return places.floorEntry(pick).getValue();
    }
}
