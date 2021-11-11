import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Sorting meetings by their start time and use a priority queue to manage their
 * finish time. When manage meeting i, whether i can re-use a room can be simply
 * decided by look-up the earliest finish time priority queue.
 */
class Solution {

	public int minMeetingRooms(int[][] intervals) {
		Arrays.sort(intervals, (i, j) -> Integer.compare(i[0], j[0]));
		PriorityQueue<Integer> endingTimes = new PriorityQueue<>(
				Integer::compare);

		for (int[] interval : intervals) {
			if (endingTimes.isEmpty())
				endingTimes.add(interval[1]);
			else {
				// if the earlies ending room is available for this meeting:
				int earlistEnding = endingTimes.peek();

				if (earlistEnding <= interval[0]) {
					endingTimes.poll();
				}
				endingTimes.add(interval[1]);
			}
		}
		return endingTimes.size();
	}

}
