class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
	Queue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(b[1], a[1]));

	for (int bt[] : boxTypes)
	    pq.add(bt);

	int units = 0;
	
	while (!pq.isEmpty() && truckSize > 0) {
	    int[] box = pq.poll();
	    int addToTruck = Math.min(truckSize, box[0]);

	    truckSize -= addToTruck;
	    units += addToTruck * box[1];
	}
	return units;
    }
}
