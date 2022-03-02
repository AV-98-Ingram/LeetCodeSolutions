import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    /*
     * The basic idea is that to break repeated characters, we can insert or modify.
     * To add characters to meed the minimum length requirement, we also need to
     * insert.
     * 
     * Each insert or modify could cover a missing kind (i.e., lowercase, uppercase
     * or digit). If no insert or modify to cover missing kinds, for any password
     * whose length >= 3, it takes at most 3 steps to cover missing kinds.
     * 
     * So the remaining problem is to delete characters when the length of the
     * password beyonds 20. To maximize the benefit of a delete, we follow the idea
     * that
     * 
     * Let n be the length of a repeated sequence s. If n % 3 == 0, we need n / 3
     * inserts or modifies to break the repeation. But if we delete 1 character from
     * s, we can save one insert or modify. If n % 3 == 1, we need two deletes to
     * save one insert or modify. If n % 3 == 2, we need three deletes to save one.
     */
    public int strongPasswordChecker(String password) {
	final int len = password.length();
	int toDelete = Math.max(0, len - 20);
	int repeats = 0;
	char prev = 0;
	boolean[] kinds = new boolean[3];
	Queue<Integer> repeatedSeqs = new PriorityQueue<>((a, b) -> (Integer.compare(a % 3, b % 3)));

	for (char c : password.toCharArray()) {

	    kinds[0] |= 'a' <= c && c <= 'z';
	    kinds[1] |= 'A' <= c && c <= 'Z';
	    kinds[2] |= '0' <= c && c <= '9';
	    if (c == prev)
		repeats++;
	    else {
		if (repeats >= 3)
		    repeatedSeqs.add(repeats);
		repeats = 1;
	    }
	    prev = c;
	}
	if (repeats >= 3)
	    repeatedSeqs.add(repeats);

	int missedKinds = (kinds[0] ? 0 : 1) + (kinds[1] ? 0 : 1) + (kinds[2] ? 0 : 1);
	int toDel = toDelete;

	// use a priority queue to maximize the benefit of deletion:
	while (toDel > 0 && !repeatedSeqs.isEmpty()) {
	    int seq = repeatedSeqs.poll();
	    int del = Math.min(toDel, seq % 3 + 1);

	    toDel -= del;
	    seq -= del;
	    if (seq >= 3)
		repeatedSeqs.add(seq);
	}
	if (toDel > 0)
	    return toDelete + missedKinds;

	int insertOrReplace = 0;
	int extraInsertion = 0;
	int result = 0;

	for (int seq : repeatedSeqs)
	    insertOrReplace += seq / 3; // number of insertion or modification to break repeatition
	if (len < 6) {
	    // if len < 6, we use insertion instead of modification to cover the shortage
	    int needInserts = Math.max(6 - len, insertOrReplace);

	    extraInsertion = needInserts - insertOrReplace;
	}
	result += Math.max(missedKinds, insertOrReplace + extraInsertion); // insertion or modification covers missing
	// kinds
	return result + toDelete;
    }

    public static void main(String[] args) {
	System.out.println(new Solution().strongPasswordChecker("bbaaaaaaaaaaaaaaacccccc"));
    }
}
