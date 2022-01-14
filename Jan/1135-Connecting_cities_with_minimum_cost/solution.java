class Solution {

    class DS {
	int id;
	DS root = null;

	DS(int id) {
	    this.id = id;
	}

	DS getRoot() {
	    DS myRoot = this;

	    while (myRoot.root != null)
		myRoot = myRoot.root;
	    this.root = myRoot;
	    return myRoot;
	}

	// requires this and other are not in the same DS;
	void union(DS other) {
	    DS myRoot = getRoot();
	    DS otherRoot = other.getRoot();

	    myRoot.root = otherRoot;
	}
    }
    
    // greedy fixed-point approach, too slow
    public int minimumCost(int n, int[][] connections) {
	PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->(Integer.compare(a[2], b[2])));
	DS[] dss = new DS[n+1];
	int cost = 0;
	int nDss = 0;
	int ids = 0;
	int ncities = 0;
	
	for (int[] con : connections)
	    pq.add(con);
	while (!pq.isEmpty()) {
	    int[] con = pq.poll();
	    
	    if (dss[con[0]] != null && dss[con[1]] == null) {
		dss[con[1]] = dss[con[0]];
		cost += con[2];
		ncities++;
	    } else if (dss[con[1]] != null && dss[con[0]] == null) {
		dss[con[0]] = dss[con[1]];
		cost += con[2];
		ncities++;		
	    } else if (dss[con[0]] == null && dss[con[1]] == null) {
		dss[con[0]] = dss[con[1]] = new DS(ids++);
		nDss++;
		cost += con[2];
		ncities = ncities + 2;
	    } else {
		DS root0 = dss[con[0]].getRoot();
		DS root1 = dss[con[1]].getRoot();
		
		if (root0.id != root1.id) {
		    root0.union(root1);
		    nDss--;
		    cost += con[2];
		}
	    }
	    if (ncities == n && nDss == 1)
		return cost;
	}
	return -1;
    }

    public static void main(String[] args) {
		new Solution().minimumCost(3, new int[][] { { 1, 2, 5 }, { 1, 3, 6 }, { 2, 3, 1 } });
    }
}
