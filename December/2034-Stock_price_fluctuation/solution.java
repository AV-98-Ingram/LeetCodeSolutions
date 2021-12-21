import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class StockPrice {

	private Map<Integer, Integer> records = new HashMap<>();
	private PriorityQueue<int[]> maxQueue = new PriorityQueue<>((a, b) -> (Integer.compare(b[0], a[0])));
	private PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));

	private int lastTime = 0;

	public StockPrice() {
	}

	public void update(int timestamp, int price) {
		records.put(timestamp, price);
		maxQueue.add(new int[] { price, timestamp });
		minQueue.add(new int[] { price, timestamp });
		lastTime = Math.max(lastTime, timestamp);
	}

	public int current() {
		return records.get(lastTime);
	}

	public int maximum() {
		do {
			int max[] = maxQueue.peek();

			if (records.get(max[1]) == max[0])
				return max[0];
			maxQueue.poll();
		} while (true);
	}

	public int minimum() {
		do {
			int min[] = minQueue.peek();

			if (records.get(min[1]) == min[0])
				return min[0];
			minQueue.poll();
		} while (true);
	}
}

/**
 * Your StockPrice object will be instantiated and called as such: StockPrice
 * obj = new StockPrice(); obj.update(timestamp,price); int param_2 =
 * obj.current(); int param_3 = obj.maximum(); int param_4 = obj.minimum();
 */
