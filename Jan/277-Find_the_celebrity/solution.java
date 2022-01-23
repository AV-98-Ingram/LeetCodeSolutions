import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class Solution extends Relation {

    /*
     * O(N^2) solution is too easy.
     */

    public int findCelebrity(int n) {
	Set<Integer> people = new HashSet<>();
	BitSet cands = new BitSet(n);

	cands.flip(0, n);
	for (int i = 0; i < n; i++)
	    for (int j = 0; j < n; j++) {
		if (i == j)
		    continue;
		if (people.contains(j) && people.contains(i))
		    continue;
		if (knows(i, j)) {
		    people.add(i);
		    cands.clear(i);
		} else {
		    people.add(j);
		    cands.clear(j);
		}
	    }
	if (cands.cardinality() == 1) {
	    return cands.nextSetBit(0);
	}
	return -1;
    }
}
