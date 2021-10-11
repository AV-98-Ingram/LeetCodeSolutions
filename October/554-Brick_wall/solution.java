import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A O(n*m) solution: count the disconnected points in each column
 */
class Solution {
    private Map<Integer, Integer> disconnects = new HashMap<>();

    public int leastBricks(List<List<Integer>> wall) {
	int max = 0;
	int ncols = 0;
	int count;

	for (Integer brick : wall.remove(0)) {
	    ncols += brick;
	    count = disconnects.merge(ncols, 1, Integer::sum);
	    if (count > max)
		max = count;
	}
	disconnects.remove(ncols);
	for (List<Integer> row : wall) {
	    int col = 0;

	    for (Integer brick : row) {
		col += brick;
		if (col >= ncols)
		    break;
		count = disconnects.merge(col, 1, Integer::sum);
		if (count > max)
		    max = count;
	    }
	}
	return disconnects.isEmpty() ? wall.size() + 1 : wall.size() + 1 - max;
    }
}
