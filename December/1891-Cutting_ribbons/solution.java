class Solution {
    public int maxLength(int[] ribbons, int k) {
	long max_l = 0;

	for (int n : ribbons)
	    max_l += n;
	max_l = max_l / k;

	int max = (int)max_l;
	
	if (max_l > 0)
	    return bsearch(ribbons, 1, max + 1, k);
	return 0;
    }

    private int bsearch(int[] ribs, int start, int end, int k) {
	if (start >= end)
	    return 1;
	
	int mid = (end - start) / 2 + start;
	int checked = 0;
	
	// check for mid:
	for (int n : ribs) {
	    checked += n / mid;
	    if (checked >= k)
		break;
	}
	if (checked < k)
	    return bsearch(ribs, start, mid, k);

	int greater = bsearch(ribs, mid+1, end, k);

	if (greater > mid)
	    return greater;
	return mid;
    }
}
