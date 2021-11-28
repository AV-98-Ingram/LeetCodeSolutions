import java.util.Arrays;

class Solution {
    /*
      For each point p1,  let the distance to p2, p3 and p4 be i, j, k respectively.

      Distance could be floating-point number thus we lost precision.
      Instead of using distance, we use the square of distance, i.e., i^2, j^2 and k^2.
      
      Let {i^2, j^2, k^2} be a sorted sequence.  The conditions of validaty is then 

      1) each point should result in exact same sorted sequence, and 
      2) i^2 == j^2 && i^2 * 2 == k^2      
     */
    
	public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
		int[] dists = dist(p1, p2, p3, p4);
		int[] tmp = dist(p2, p1, p3, p4);

		if (dists[0] != dists[1] || dists[2] != 2 * dists[1]
				|| dists[2] == dists[1])
			return false;
		if (!Arrays.equals(dists, tmp))
			return false;
		tmp = dist(p3, p1, p2, p4);
		if (!Arrays.equals(dists, tmp))
			return false;
		tmp = dist(p4, p1, p2, p3);
		if (!Arrays.equals(dists, tmp))
			return false;
		return true;
	}

	private int[] dist(int[] p, int[]... q) {
		int[] ret = new int[q.length];

		for (int i = 0; i < q.length; i++) {
			ret[i] = (p[0] - q[i][0]) * (p[0] - q[i][0])
					+ (p[1] - q[i][1]) * (p[1] - q[i][1]);
		}
		Arrays.sort(ret);
		return ret;
	}

	public static void main(String[] args) {
		new Solution().validSquare(new int[] { 0, 0 }, new int[] { 1, 1 },
				new int[] { 1, 0 }, new int[] { 0, 1 });
	}
}
