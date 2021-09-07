import java.util.PriorityQueue;

/**
 * I didn't realize that what I did here is maintaining a PriorityQueue.
 * 
 * So the idea is to use a PriorityQueue to maintain all the heads of the lists.
 * Keep taking the prefix off the min list and appending it to the merging list.
 * The prefix is defined as the maximum prefix such that nodes in the suffix are
 * all greater than the head of the second min list.
 */
class Solution {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public ListNode mergeKLists(ListNode[] lists) {
		ListNode tail = null, head = null;
		PriorityQueue<ListNode> queue = new PriorityQueue<>(
				(i, j) -> (Integer.compare(i.val, j.val)));

		if (lists.length == 0)
			return null;

		for (ListNode l : lists)
			if (l != null)
				queue.add(l);
		while (queue.size() > 1) {
			ListNode min = queue.poll();
			ListNode smin = queue.peek();
			ListNode[] headAndTail = merge(tail, min, smin);

			if (head == null)
				head = headAndTail[0];
			tail = headAndTail[1];
			if (tail.next != null)
				queue.add(tail.next);
		}
		if (tail == null) {
			if (queue.size() < 1)
				return null;
			else
				return queue.poll();
		} else
			tail.next = queue.poll();
		return head;
	}

	// takes a prefix of lists[minIdx] off and adds it to mergeList
	private ListNode[] merge(ListNode mergeList, ListNode min, ListNode smin) {
		ListNode curr = min;
		ListNode head = min;

		// assert curr != null;
		while (curr != null && curr.val <= smin.val) {
			if (mergeList != null)
				mergeList.next = curr;
			mergeList = curr;
			curr = curr.next;
		}
		return new ListNode[] { head, mergeList };
	}

	public static void main(String[] args) {
		int[][] in = new int[][] { { 1, 2, 3, 9 }, { 4, 5, 6, 7 } };
		ListNode[] lists = new ListNode[in.length];
		int i = 0;

		for (int[] l : in) {
			ListNode curr;

			if (l.length <= 0)
				lists[i] = null;
			lists[i] = new ListNode(l[0], null);
			curr = lists[i];
			for (int j = 1; j < l.length; j++) {
				curr.next = new ListNode(l[j], null);
				curr = curr.next;
			}
			i++;
		}
		ListNode m = new Solution().mergeKLists(lists);

		while (m != null) {
			System.out.printf("%3d", m.val);
			m = m.next;
		}
	}
}
