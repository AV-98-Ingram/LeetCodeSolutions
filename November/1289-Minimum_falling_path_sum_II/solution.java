import java.util.PriorityQueue;

/*
  I thought only the minimum and the second minimum numbers of each
  row matter but it is not true.  For example, 
  
  [1, 99, 99]         
  [1, 2, 3]
  [9, 0, 9]

  It we only keep the first two minimun numbers for each row, we have

  1, 99,
  1,  2,
  9,  0, 9

  We lost the min path 1, 3, 0.
  
  The correct solution is to compute each cache[i][j], where
  cache[i][j] represents the minimum path through [0, i] while
  grid[i][j] is selected for row[i].  To compute cache[i][j], we need
  the FIRST TWO minimum selections from the last row, so I used a
  PriorityQueue to maintain the result of each row.
  
 */
class Solution {

    class MyPQ {
	PriorityQueue<int[]> pq = new PriorityQueue<>(
						      (a, b) -> (Integer.compare(a[0], b[0])));
    }

    // cache[i]: the sorted list of pairs (s, j), where s is the min path sum
    // through [0 .. i] while grid[i][j] is selected:
    MyPQ cache[];

    public int minFallingPathSum(int[][] grid) {
	cache = new MyPQ[grid.length];
	cache[0] = new MyPQ();
	for (int i = 0; i < grid[0].length; i++)
	    cache[0].pq.add(new int[] { grid[0][i], i });
	for (int i = 1; i < grid.length; i++) {
	    cache[i] = new MyPQ();

	    for (int j = 0; j < grid[i].length; j++) {
		int[] min = cache[i - 1].pq.peek();

		if (j == min[1]) {
		    cache[i - 1].pq.poll();
		    cache[i].pq.add(new int[] {
			    cache[i - 1].pq.peek()[0] + grid[i][j], j });
		    cache[i - 1].pq.add(min); // add back
		} else
		    cache[i].pq.add(new int[] { min[0] + grid[i][j], j });

	    }
	}
	return cache[grid.length - 1].pq.peek()[0];
    }

    public static void main(String[] args) {
	System.out.println(new Solution().minFallingPathSum(
							    new int[][] { { 1, 99, 99 }, { 0, 2, 1 }, { 99, 99, 4 } }));
    }
}
