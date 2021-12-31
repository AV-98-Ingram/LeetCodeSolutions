import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    /*
     * Use binary search. Select a point p and let the distance of p, dist(p), be a
     * pivot.
     * 
     * Let S be the set {q | dist(q) <= dist(p)}. If there are more than k points in
     * S, we only need to look at S. Otherwise, we are going to find the minimum k -
     * |S| elements in the complement set of S. Basically same idea as QuickSelect.
     * 
     */
    public int[][] kClosest(int[][] points, int k) {
	List<int[]> distancesLst = Arrays.stream(points)
	    .map((a) -> new int[] { (a[0] * a[0] + a[1] * a[1]), a[0], a[1] }).collect(Collectors.toList());
	int[][] dists = new int[points.length][3];

	distancesLst.toArray(dists);

	int left = 0, right = points.length - 1, mid;
	int kk = k, lb = 0, rb = points.length - 1;

	do {
	    mid = (right - left) / 2 + left;
	    while ((left < mid || right > mid)) {
		if (left == mid && dists[right][0] < dists[mid][0]) {
		    int[] tmp = dists[mid];

		    dists[mid] = dists[right];
		    dists[right] = dists[mid + 1];
		    dists[mid + 1] = tmp;
		    mid++;
		} else if (right == mid && dists[left][0] > dists[mid][0]) {
		    int[] tmp = dists[mid];

		    dists[mid] = dists[left];
		    dists[left] = dists[mid - 1];
		    dists[mid - 1] = tmp;
		    mid--;
		} else if (dists[left][0] > dists[mid][0] && dists[right][0] < dists[mid][0]) {
		    int[] tmp = dists[left];

		    dists[left] = dists[right];
		    dists[right] = tmp;
		    left++;
		    right--;
		    continue;
		}
		if (left < mid && dists[left][0] <= dists[mid][0])
		    left++;
		if (right > mid && dists[right][0] >= dists[mid][0])
		    right--;
	    }
	    if (mid + 1 < kk) {
		right = rb;
		left = mid + 1;
		lb = left;
	    } else if (mid > kk) {
		right = mid;
		left = lb;
		rb = right;
	    }
	} while (mid + 1 != kk && mid != kk);

	int[][] ret = new int[k][];

	for (int i = 0; i < k; i++) {
	    ret[i] = Arrays.copyOfRange(dists[i], 1, 3);
	}

	return ret;
    }
}
