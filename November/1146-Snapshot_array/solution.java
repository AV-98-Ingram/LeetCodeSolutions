import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

class SnapshotArray {

	final ArrayList<Map<Integer, Integer>> snapshots;
	Map<Integer, Integer> curr;

	public SnapshotArray(int length) {
		snapshots = new ArrayList<>();
		curr = new TreeMap<>();
	}

	public void set(int index, int val) {
		curr.put(index, val);
	}

	public int snap() {
		snapshots.add(curr);
		curr = new TreeMap<>();
		return snapshots.size() - 1;
	}

	public int get(int index, int snap_id) {
		Integer val = null;

		if (snap_id >= snapshots.size())
			snap_id = snapshots.size() - 1;
		while (val == null && snap_id >= 0) {
			val = snapshots.get(snap_id).get(index);
			snap_id--;
		}
		if (val == null)
			return 0;
		return val.intValue();
	}
}
