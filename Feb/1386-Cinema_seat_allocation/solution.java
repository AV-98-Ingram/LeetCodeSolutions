import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    static List<Integer> emptyList = new LinkedList<>();

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
	Map<Integer, List<Integer>> table = new HashMap<>();

	for (int[] seat : reservedSeats)
	    table.computeIfAbsent(seat[0], k -> new LinkedList<>()).add(seat[1]);

	int max = 0;
	// no need to think about the first and the last seat
	byte twoGrp = (byte) 0b11111111;
	byte leftGrp = (byte) 0b11110000;
	byte rightGrp = 0b00001111;
	byte midGrp = 0b00111100;

	for (int r : table.keySet()) {
	    byte status = (byte) 0b11111111; // all 1s meaning all empty
	    byte mask = (byte) 0b11111111;

	    for (int seat : table.getOrDefault(r, emptyList)) {
		if (seat == 1 || seat == 10)
		    continue;
		status = (byte) (status & (mask ^ ((byte) 1 << (9 - seat))));
	    }
	    if ((status & twoGrp) == twoGrp)
		max += 2;
	    else if ((status & leftGrp) == leftGrp)
		max += 1;
	    else if ((status & rightGrp) == rightGrp)
		max += 1;
	    else if ((status & midGrp) == midGrp)
		max += 1;
	}
	return max + (n - table.size()) * 2;
    }

    public static void main(String args[]) {
	new Solution().maxNumberOfFamilies(2, new int[][] { { 2, 1 }, { 1, 8 }, { 2, 6 } });
    }
}
