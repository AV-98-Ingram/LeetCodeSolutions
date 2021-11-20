import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class TimeMap {

	static TreeMap<Integer, String> emptyTree = new TreeMap<>();

	Map<String, TreeMap<Integer, String>> map = new HashMap<>();

	public TimeMap() {

	}

	public void set(String key, String value, int timestamp) {
		map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
	}

	public String get(String key, int timestamp) {
		Map.Entry<Integer, String> floorEntry = map.getOrDefault(key, emptyTree)
				.floorEntry(timestamp);

		if (floorEntry == null)
			return null;
		else
			return floorEntry.getValue();
	}
}
