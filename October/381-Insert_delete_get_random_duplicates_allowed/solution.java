import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

class RandomizedCollection {

	private Map<Integer, LinkedList<Integer>> store;
	private ArrayList<Integer> vals;
	private Random rand;

	public RandomizedCollection() {
		store = new HashMap<>();
		vals = new ArrayList<>();
		rand = new Random();
	}

	public boolean insert(int val) {
		LinkedList<Integer> l = store.computeIfAbsent(val,
				(k) -> (new LinkedList<>()));

		l.add(vals.size());
		vals.add(val);
		return l.size() == 1;
	}

	public boolean remove(int val) {
		LinkedList<Integer> l = store.get(val);

		if (l == null || l.isEmpty())
			return false;

		int idx = l.removeFirst();

		vals.set(idx, null);
		return true;
	}

	public int getRandom() {
		Integer n = null;

		do {
			n = vals.get(Math.abs(rand.nextInt()) % vals.size());
		} while (n == null);
		return n;
	}
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection(); boolean param_1 =
 * obj.insert(val); boolean param_2 = obj.remove(val); int param_3 =
 * obj.getRandom();
 */
