import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Solution {

    class State {
	int stone, k;

	State(int x, int y) {
	    this.stone = x;
	    this.k = y;
	}

	public boolean equals(Object o) {
	    if (o instanceof State) {
		State other = (Solution.State) o;

		return other.stone == stone && other.k == k;

	    }
	    return false;
	}

	public int hashCode() {
	    return (stone * 31) ^ k;
	}

    }

    // BFS with saving states, passed
    public boolean canCross(int[] stones) {
	LinkedList<State> queue = new LinkedList<>();
	int target = stones[stones.length - 1];
	Set<Integer> stoneSet = new HashSet<>();
	Set<State> seenStates = new HashSet<>();

	if (stones[1] != 1)
	    return false;
	for (int i : stones)
	    stoneSet.add(i);
	queue.add(new State(1, 1));
	while (!queue.isEmpty()) {
	    State state = queue.removeFirst();
	    int stone = state.stone;
	    int k = state.k;
	    int next = stone + k - 1;

	    if (!seenStates.add(state))
		continue;
	    if (stone == target)
		return true;
	    if (next > stone && stoneSet.contains(next))
		queue.add(new State(next, k - 1));
	    next = stone + k;
	    if (next > stone && stoneSet.contains(next))
		queue.add(new State(next, k));
	    next = stone + k + 1;
	    if (next > stone && stoneSet.contains(next))
		queue.add(new State(next, k + 1));
	}
	return false;
    }

    public static void main(String[] args) {
	new Solution().canCross(new int[] { 0, 1, 3, 5, 6, 8, 12, 17 });
    }
}
