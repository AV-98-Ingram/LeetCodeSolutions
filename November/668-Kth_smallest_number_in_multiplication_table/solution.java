import java.util.PriorityQueue;

class Solution {
    // damn! I should have thought about this solution
    class Row {
	int row;
	int next;

	Row(int row, int next) {
	    this.row = row;
	    this.next = next;
	}
    }

    public int findKthNumber(int m, int n, int k) {
	int l = m > n ? m : n;
	int s = m > n ? n : m;
	PriorityQueue<Row> queue = new PriorityQueue<>(
						       (a, b) -> Integer.compare(a.next, b.next));

	for (int i = 1; i <= s; i++)
	    queue.add(new Row(i, i));

	int val = 0;

	for (int i = 0; i < k; i++) {
	    Row r = queue.poll();

	    val = r.next;
	    r.next = r.next + r.row;
	    if (r.next <= r.row * l)
		queue.add(r);
	}
	return val;
    }

}
