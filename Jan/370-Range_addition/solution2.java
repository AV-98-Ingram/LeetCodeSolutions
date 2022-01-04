class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
	int[] arr = new int[length];
	
	for (int[] up : updates) {
	    arr[up[0]] += up[2];
	    if (up[1] < length - 1)
		arr[up[1] + 1] -= up[2];
	}
	// scan arr:
	int scan = 0;
	for (int i = 0; i < length; i++) {
        int oldScan = scan;
        
        scan += arr[i];
	    arr[i] += oldScan;        
	}	
	return arr;
    }
}
