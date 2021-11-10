import java.util.ArrayList;

class MRUQueue {

	ArrayList<Integer> list = new ArrayList<>();

	public MRUQueue(int n) {
		for (int i = 1; i <= n; i++)
			list.add(i);
	}

	public int fetch(int k) {
		int val = list.get(k - 1);

		list.remove(k - 1);
		list.add(val);
		return val;
	}
}

