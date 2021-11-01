public class Solution {

    Set<ListNode> seen = new HashSet<>();
    
    public boolean hasCycle(ListNode head) {
        while (head != null) {
	    if (seen.add(head))
		head = head.next;
	    else
		return true;
	}
	return false;
    }
}
