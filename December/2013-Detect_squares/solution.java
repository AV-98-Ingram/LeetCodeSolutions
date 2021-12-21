import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class DetectSquares {

	Map<Integer, Map<Integer, Integer>> x_indexed = new HashMap<>();

	static Map<Integer, Integer> empty = new HashMap<>();

	public DetectSquares() {

	}

	public void add(int[] point) {
		x_indexed.computeIfAbsent(point[0], k -> new HashMap<>()).merge(point[1], 1, Integer::sum);
	}

	public int count(int[] point) {
		Map<Integer, Integer> pts = x_indexed.getOrDefault(point[0], empty);
		int count = 0;

		for (Entry<Integer, Integer> pt : pts.entrySet()) {
			int size = Math.abs(point[1] - pt.getKey());
			int n, m, nsquares = 0;

			if (size <= 0)
				continue;

			// right sqaures:
			n = x_indexed.getOrDefault(point[0] + size, empty).getOrDefault(point[1], 0);
			m = x_indexed.getOrDefault(point[0] + size, empty).getOrDefault(pt.getKey(), 0);
			if (point[0] >= 0 && point[1] >= 0 && pt.getKey() >= 0)
				nsquares = n * m;
			// left squares:
			n = x_indexed.getOrDefault(point[0] - size, empty).getOrDefault(point[1], 0);
			m = x_indexed.getOrDefault(point[0] - size, empty).getOrDefault(pt.getKey(), 0);
			if (point[0] >= 0 && point[0] >= size && point[1] >= 0 && pt.getKey() >= 0)
				nsquares += n * m;
			nsquares *= pt.getValue();
			count += nsquares;
		}
		return count;
	}
}
