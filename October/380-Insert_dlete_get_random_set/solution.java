import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class RandomizedSet {

	// val -> pos in list
	private Map<Integer, Integer> store = new HashMap<>();
	private ArrayList<Integer> vals = new ArrayList<>();
	Random rand = new Random();

	public RandomizedSet() {

	}

	public boolean insert(int val) {
		Integer exist = store.putIfAbsent(val, vals.size());

		if (exist == null) {
			vals.add(val);
			return true;
		}
		return false;
	}

	public boolean remove(int val) {
		Integer exist = store.remove(val);

		if (exist != null) {
			vals.set((int) exist, null);
			return true;
		}
		return false;
	}

	public int getRandom() {
		Integer val = null;

		do {
			int pos = Math.abs(rand.nextInt());
			val = vals.get((int) (pos % vals.size()));
		} while (val == null);
		return val;
	}
}
