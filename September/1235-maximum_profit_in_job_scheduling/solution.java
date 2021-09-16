import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * I didn't think of this idea. This problem is worth to revisit someday.
 */
class Solution {

	private class Job {
		int startTime;
		int endTime;
		int profit;

		Job(int start, int end, int profit) {
			this.startTime = start;
			this.endTime = end;
			this.profit = profit;
		}

		public String toString() {
			return "[" + startTime + " => " + endTime + "] = " + profit;
		}
	}

	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		Job[] jobs = new Job[profit.length];

		for (int i = 0; i < profit.length; i++)
			jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
		Arrays.sort(jobs, new Comparator<Job>() {
			@Override
			public int compare(Solution.Job o1, Solution.Job o2) {
				if (o1.endTime == o2.endTime)
					return 0;
				if (o1.endTime < o2.endTime)
					return -1;
				return 1;
			}
		});

		Map<Integer, Integer> prefixMax = new HashMap<>();
		int max = jobs[0].profit;

		prefixMax.put(0, jobs[0].profit);
		for (int i = 1; i < jobs.length; i++) {
			int currMax = jobs[i].profit;

			for (int j = i - 1; j >= 0; j--) {
				// find the last non-conflict job idx:
				if (jobs[j].endTime <= jobs[i].startTime) {
					currMax += prefixMax.get(j);
					break;
				}
			}
			if (max < currMax)
				max = currMax;
			prefixMax.put(i, max);
		}
		return max;
	}

	public static void main(String args[]) {
		int[] startTime = new int[] { 4, 2, 4, 8, 2 };
		int[] endTime = new int[] { 5, 5, 5, 10, 8 };
		int[] profit = new int[] { 1, 2, 8, 10, 4 };
		// int[] startTime = new int[] { 1, 2, 2, 3 };
		// int[] endTime = new int[] { 2, 5, 3, 4 };
		// int[] profit = new int[] { 3, 4, 1, 2 };

		System.out.println(
				"" + new Solution().jobScheduling(startTime, endTime, profit));
	}
}
