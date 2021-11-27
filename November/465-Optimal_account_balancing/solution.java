import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Idea:
 * <p>
 * First execute all given transactions then divide all the IDs (guys) into
 * positive ones and negative ones, based on their balance.
 * 
 * Then elaborate all possible groups for positives and negatives, respectively.
 * A group contains at least one guy. Let the grouping result of the positives
 * be an list of groups, ordered by the size (primary) and sum (secondary). Let
 * the grouping result of the negatives be a map of groups indexed by their
 * sums. Groups within each entry are also ordered by sizes.
 * 
 * The intuition here is to try to match a group in a with a group b while
 * keeping both groups as small and as low (in terms of their sums) as possible.
 * 
 * Finally, we match groups in a with ones in b in turns of the group order in
 * a. We need to keep track of the guys being matched to avoid match a guy
 * twice.
 * </p>
 * 
 * <p> Time complexity: worst case O(n!) but in average O((n/2)!) as
 * numbers are divided into positives and negatives </p>
 * 
 * @author ziqing
 *
 */
class Solution {
    public int minTransfers(int[][] transactions) {
	int[] guys = new int[21];

	Arrays.fill(guys, 0);
	for (int[] trans : transactions) {
	    guys[trans[0]] -= trans[2];
	    guys[trans[1]] += trans[2];
	}

	ArrayList<Integer> pos = new ArrayList<>();
	ArrayList<Integer> neg = new ArrayList<>();

	for (int i = 0; i <= 20; i++)
	    if (guys[i] > 0)
		pos.add(i);
	    else if (guys[i] < 0)
		neg.add(i);

	Map<Integer, PriorityQueue<List<Integer>>> b = new HashMap<>();
	PriorityQueue<Group> a = new PriorityQueue<>();

	allGroup(0, pos, guys, new LinkedList<>(), 0, new HashMap<>(), a);
	allGroup(0, neg, guys, new LinkedList<>(), 0, b,
		 new PriorityQueue<Group>());
	return match(a, b, pos.size() + neg.size());
    }

    /**
     * Tries to match groups in <code>a</code> with ones in <code>b</code>.
     * Groups in a are odered by their size (primary and sum secondary). Groups
     * in b can be quickly looked up by their sums.
     * <p>
     * The mathc is based on the requirement that a and b have same amount but
     * opposite balances.
     * <p>
     * There is another hypothesis that I haven't proved yet: As long as I am
     * match groups in a in its given order, I will get the minimum result. The
     * intuition is to first match smaller groups that have smaller sums.
     * 
     * @param a
     * @param b
     * @return
     */
    private int match(PriorityQueue<Group> a,
		      Map<Integer, PriorityQueue<List<Integer>>> b, int numGuys) {
	Set<Integer> bGone = new HashSet<>();
	Set<Integer> aGone = new HashSet<>();
	int times = 0;

	while (!a.isEmpty() || aGone.size() + bGone.size() < numGuys) {
	    Group g = a.poll();

	    for (Integer member : g.members) {
		if (aGone.contains(member)) {
		    g = null;
		    break;
		}
	    }
	    if (g == null)
		continue;

	    PriorityQueue<List<Integer>> matchedGroups = b.get(-g.sum);

	    if (matchedGroups == null)
		continue;

	    List<Integer> bg = null;

	    while (!matchedGroups.isEmpty()) {
		bg = matchedGroups.poll();
		for (Integer member : bg)
		    if (bGone.contains(member)) {
			bg = null;
			break;
		    }
		if (bg == null)
		    continue;
		break;
	    }
	    if (bg != null) {
		// update times, aGone and bGone:
		times += g.members.size() + bg.size() - 1;
		aGone.addAll(g.members);
		bGone.addAll(bg);
	    }
	}
	return times;
    }

    /**
     * Figure out all possible groups for the given set of
     * indices,i.e.,<code>guyIndices</code>.
     * 
     * Complexity: O(n!)
     * 
     * @param out
     *            one kind of output---sum-index size-ordered groups
     * @param orderedGroups
     *            the other kind of output---size-ordered, sum-sub-ordered
     *            groups
     */
    private void allGroup(int pos, final ArrayList<Integer> guyIndices,
			  final int[] guys, List<Integer> prevSelections, int sum,
			  Map<Integer, PriorityQueue<List<Integer>>> out,
			  PriorityQueue<Group> orderedGroups) {
	if (pos >= guyIndices.size())
	    return;

	for (int i = pos; i < guyIndices.size(); i++) {
	    List<Integer> selections = new LinkedList<>(prevSelections);
	    int newSum = sum + guys[guyIndices.get(i)];

	    selections.add(guyIndices.get(i));
	    out.computeIfAbsent(newSum,
				(k) -> new PriorityQueue<>(
							   (l1, l2) -> Integer.compare(l1.size(), l2.size())))
		.add(selections);

	    Group newGroup = new Group(newSum, selections);

	    orderedGroups.add(newGroup);
	    allGroup(i + 1, guyIndices, guys, selections, newSum, out,
		     orderedGroups);
	}
    }

    /**
     * Represents a group of numbers (either all positive or all negative)
     * 
     * @author ziqing
     */
    class Group implements Comparable<Group> {
	/**
	 * sum of all members
	 */
	int sum;
	List<Integer> members;

	Group(int sum, List<Integer> members) {
	    this.sum = sum;
	    this.members = members;
	}

	@Override
	public int compareTo(Solution.Group o) {
	    int comp = Integer.compare(members.size(), o.members.size());

	    if (comp == 0)
		comp = Integer.compare(sum, o.sum);
	    return comp;
	}

	public String toString() {
	    return members.toString();
	}
    }

    public static void main(String[] args) {
	System.out.println(new Solution().minTransfers(
						       new int[][] { { 1, 0, 18 }, { 2, 1, 9 }, { 4, 3, 11 },
								     { 5, 4, 10 }, { 5, 6, 7 }, { 7, 6, 5 }, { 8, 7, 3 } }));
    }
}
