/**
 * // This is MountainArray's API interface. // You should not implement it, or
 * speculate about its implementation interface MountainArray { public int
 * get(int index) {} public int length() {} }
 */
class MountainArray {
    int a[];

    MountainArray(int a[]) {
	this.a = a;
    }

    public int get(int index) {
	return a[index];
    }

    public int length() {
	return a.length;
    }
}

class Solution {

    public int findInMountainArray(int target, MountainArray mountainArr) {
	int len = mountainArr.length();

	return search(mountainArr, 0, len, target);
    }

    private int search(MountainArray ma, int start, int end, int target) {
	int mid = (end - start) / 2 + start;
	int midVal = ma.get(mid);

	if (mid == start)
	    return midVal == target ? mid : -1;

	int r = -1, l = -1;

	if (mid == end - 1) {
	    r = midVal == target ? mid : -1;
	    l = search(ma, start, mid, target);
	} else {
	    // the general case:
	    int midNextVal = ma.get(mid + 1);

	    if (midVal > midNextVal) {
		// dont waste the obtained values of mid and mid+1
		if (midVal == target)
		    r = mid;
		else if (midNextVal == target)
		    r = mid + 1;
		else if (midVal > target)
		    r = regularSearch(ma, mid + 1, end, target, true);
		l = search(ma, start, mid, target);
	    } else {
		// dont waste the obtained values of mid and mid+1
		if (midVal == target)
		    l = mid;
		else if (midNextVal == target)
		    l = mid + 1;
		else if (midVal > target)
		    l = regularSearch(ma, start, mid, target, false);
		r = search(ma, mid + 1, end, target);
	    }
	}
	if (l >= 0)
	    return l;
	return r;
    }

    private int regularSearch(MountainArray ma, int start, int end, int target,
			      boolean isDescending) {
	int mid = (end - start) / 2 + start;
	int midVal = ma.get(mid);

	if (midVal == target)
	    return mid;
	if (mid == start)
	    return -1;
	if (isDescending) {
	    if (midVal < target)
		return regularSearch(ma, start, mid, target, true);
	    else
		return regularSearch(ma, mid, end, target, true);
	} else {
	    if (midVal < target)
		return regularSearch(ma, mid, end, target, false);
	    else
		return regularSearch(ma, start, mid, target, false);
	}
    }

    public static void main(String args[]) {
	new Solution().findInMountainArray(0,
					   new MountainArray(new int[] { 1, 2, 3, 5, 3 }));
    }
}
