import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
   This solution may not be the fastest one but it divides the task
   into independant smaller ones that can be easily parallelized.
 */
class Solution {
	class Group {
		final int idx;
		final String src;
		final String tar;

		Group(int idx, String src, String tar) {
			this.idx = idx;
			this.src = src;
			this.tar = tar;
		}
	}

	public String findReplaceString(String s, int[] indices, String[] sources,
			String[] targets) {
		Group[] groups = new Group[indices.length];
		List<Integer> tasks = new LinkedList<>();

		tasks.add(-1);
		for (int i = 0; i < groups.length; i++) {
			groups[i] = new Group(indices[i], sources[i], targets[i]);
			tasks.add(i);
		}
		Arrays.sort(groups, (g1, g2) -> (Integer.compare(g1.idx, g2.idx)));

		List<String> strs = tasks.stream().map(i -> replace(s, i, groups))
				.collect(Collectors.toList());
		StringBuilder sb = new StringBuilder();
		
		for (String str : strs)
		    sb.append(str);
		return sb.toString();
	}

	private String replace(String s, final int i, final Group[] groups) {
		if (i == -1)
			return s.substring(0, groups[0].idx);
		// match substring:
		String src = groups[i].src;
		String tar = groups[i].tar;
		int idx = groups[i].idx;
		int end = i < groups.length - 1 ? groups[i + 1].idx : s.length();
		String replacee = s.substring(idx, idx + src.length());
		String suffix = s.substring(idx + src.length(), end);

		if (replacee.equals(src))
			return tar + suffix;
		return s.substring(idx, end);
	}

	public static void main(String[] args) {
		new Solution().findReplaceString("vmokgggqzp", new int[] { 3, 5, 1 },
				new String[] { "kg", "ggq", "mo" },
				new String[] { "s", "so", "bfr" });
	}
}

