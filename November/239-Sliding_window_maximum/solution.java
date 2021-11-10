import java.util.LinkedList;
/*
  Maintain a queue where elements are ordered.  The order is enforced by the following algorithm:
  
    Let Q be the queue and v be the adding element.

    If Q is empty, enqueue v results in appending v to Q.

    If Q is non-empty, enqueue v results in 1) remove the suffix of Q
    where elements are strictly less than v; and 2) appending v to Q.

    Dequeue can only happen when the head of Q equals to the number
    that is going to be moved out of the window.
    
  Complexity: O(n) as each number is removed from the queue exactly once.

  Intuition:
  
  For a window W, the max element W[i] dominates W[0 .. i-1]. I.e., we
  do not need to know about W[0 .. i-1].  But we do need to be aware
  of when W[i] is going to be moved out of the window.  After W[i],
  suppose W[j], j > i, is the max element for the subsequece W[i+1
  .. j], then W[j] dominates W[i+1 .. j-1].  Similarly, we do not need
  know about W[i+1 .. j-1].  Thus the Queue in the algorithm will be
  like {W[i], W[j], ...}.
  
 */
class Solution {

    LinkedList<Integer> orderQue = new LinkedList<>();

    public int[] maxSlidingWindow(int[] nums, int k) {
	for (int i = 0; i < k; i++)
	    enqueue(nums[i]);

	final int ub = nums.length - k + 1;
	int[] ret = new int[nums.length - k + 1];

	ret[0] = orderQue.get(0);
	for (int i = 1; i < ub; i++) {
	    dequeue(nums[i - 1]);
	    enqueue(nums[i + k - 1]);
	    ret[i] = orderQue.get(0);
	}
	return ret;
    }

    void enqueue(int val) {
	if (orderQue.isEmpty() || orderQue.getLast() >= val)
	    orderQue.add(val);
	else {
	    while (!orderQue.isEmpty() && orderQue.getLast() < val) {
		orderQue.removeLast();
	    }
	    orderQue.add(val);
	}
    }

    void dequeue(int val) {
	if (val == orderQue.getFirst())
	    orderQue.removeFirst();
    }

    public static void main(String[] args) {
	new Solution().maxSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 },
					3);
    }
}
