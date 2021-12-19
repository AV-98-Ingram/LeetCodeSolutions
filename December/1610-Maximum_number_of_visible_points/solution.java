import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    // atan2(y, x) is the angle between vector (x, y) and (+infi, 0)
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
	ArrayList<Double> pointAngles = new ArrayList<>();
	int[] loc = new int[] { location.get(0), location.get(1) };
	int result = 0;

	for (List<Integer> pt : points) {
	    int x = pt.get(0);
	    int y = pt.get(1);

	    if (x == loc[0] && y == loc[1]) {
		result++;
		continue;
	    }

	    double ptAngle = Math.atan2(y - loc[1], x - loc[0]) * 180.0 / Math.PI;

	    pointAngles.add(ptAngle);
	    if (ptAngle < 0)
		pointAngles.add(ptAngle + 360);
	}
	Collections.sort(pointAngles);

	final int len = pointAngles.size();
	int l = 0, r = 1;
	int max = 0;

	while (r < len) {
	    while (r < len && pointAngles.get(r) - pointAngles.get(l) <= angle)
		r++;
	    max = Math.max(max, (r - l));
	    while (r < len && pointAngles.get(r) - pointAngles.get(l) > angle)
		l++;
	}
	return max + result;
    }
}
