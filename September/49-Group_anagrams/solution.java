class Solution {

	Map<String, List<String>> groups = new HashMap<>();

	public List<List<String>> groupAnagrams(String[] strs) {
		for (String str : strs) {
			char[] chars = str.toCharArray();

			Arrays.sort(chars);

			String key = String.valueOf(chars);
			List<String> group = groups.computeIfAbsent(key,
					k -> new LinkedList<>());

			group.add(str);
		}

		List<List<String>> results = new LinkedList<>();

		results.addAll(groups.values());
		return results;
	}
}
