import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public String longestDiverseString(int a, int b, int c) {
	int[][] l = new int[][] { { a, 0 }, { b, 1 }, { c, 2 } };
	StringBuffer sb = new StringBuffer();
	Queue<int[]> que = new PriorityQueue<>((x, y) -> Integer.compare(y[0], x[0]));

	for (int[] ll : l)
	    if (ll[0] > 0)
		que.add(ll);

	int lastC = -1;

	while (!que.isEmpty()) {
	    int[] lc = que.poll();

	    if (lastC == lc[1]) {
		if (que.isEmpty())
		    break;

		int[] cc = que.poll();

		sb.append((char) (cc[1] + 'a'));
		cc[0]--;
		if (cc[0] > 0)
		    que.add(cc);
		lastC = cc[1];
	    } else {
		if ((que.isEmpty() && lc[0] > 1) || (!que.isEmpty() && lc[0] > que.peek()[0] + 1)) {
		    sb.append((char) (lc[1] + 'a'));
		    lc[0]--;
		}
		sb.append((char) (lc[1] + 'a'));
		lc[0]--;
		lastC = lc[1];
	    }
	    if (lc[0] > 0)
		que.add(lc);

	}
	return sb.toString();
    }

    public static void main(String[] args) {
	new Solution().longestDiverseString(3, 0, 4);
    }
}
