import java.util.LinkedList;

class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
	LinkedList<Integer> l = new LinkedList<>();
	int t1 = 0, t2 = 0;

	for (int i = 0; i < m + n; i++) {
	    int min = Integer.min(
				  Integer.min(t1 < m ? nums1[t1] : Integer.MAX_VALUE,
					      t2 < n ? nums2[t2] : Integer.MAX_VALUE),
				  !l.isEmpty() ? l.getFirst() : Integer.MAX_VALUE);

	    if (t1 < m && min == nums1[t1])
		t1++;
	    else if (t2 < n && min == nums2[t2]) {
		if (t1 < m)
		    l.add(nums1[t1]);
		nums1[t1++] = nums2[t2++];
	    } else {
		if (t1 < m)
		    l.add(nums1[t1]);
		nums1[t1++] = l.removeFirst();
	    }
	}
    }
}
