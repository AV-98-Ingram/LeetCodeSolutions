import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Solution {

	class MyList {
		Set<Integer> l = new HashSet<>();

		int removeNext() {
			Iterator<Integer> iter = l.iterator();
			int ret = iter.next();

			iter.remove();
			return ret;
		}
	}

	MyList[][] table;

	public void findSecretWord(String[] wordlist, Master master) {
		List<Integer> tasks = new LinkedList<>();
		table = new MyList[wordlist.length][6];

		for (int i = 0; i < wordlist.length; i++)
			tasks.add(i);
		Arrays.sort(wordlist); // sort the words so that the first and the last
								// differ the most
		if (wordlist.length < 20)
			tasks.stream().forEach(i -> fillSimilarities(wordlist, i));
		else
			tasks.parallelStream().forEach(i -> fillSimilarities(wordlist, i));

		// start to guess:
		int wordIdx = 0;
		int match = master.guess(wordlist[wordIdx]);
		int match2 = master.guess(wordlist[wordlist.length - 1]);

		if (match2 > match) {
			match = match2;
			wordIdx = wordlist.length - 1;
		}

		BitSet seen = new BitSet(wordlist.length);

		seen.set(wordIdx);
		if (match == 6)
			return;
		for (int i = 0; i < match; i++)
			for (int j : table[wordIdx][i].l)
				if (!table[wordIdx][match].l.contains(j))
					seen.set(j);
		for (int i = match + 1; i < 6; i++)
			for (int j : table[wordIdx][i].l)
				seen.set(j);
		while (match < 6) {
			int oldWordIdx = wordIdx;

			if (!table[wordIdx][match].l.isEmpty()) {
				do {
					wordIdx = table[oldWordIdx][match].removeNext();
				} while (seen.get(wordIdx));
				seen.set(wordIdx);
			}

			int newMatch = master.guess(wordlist[wordIdx]);

			if (newMatch == 6)
				return;
			for (int i = 0; i < newMatch; i++)
				for (int j : table[wordIdx][i].l)
					if (!table[wordIdx][newMatch].l.contains(j))
						seen.set(j);
			for (int i = newMatch + 1; i < 6; i++)
				for (int j : table[wordIdx][i].l)
					seen.set(j);
			if (newMatch > match) {
				match = newMatch;
			} else
				wordIdx = oldWordIdx;
		}
	}

	private void fillSimilarities(String[] wordlist, int myidx) {
		for (int i = 0; i < 6; i++)
			table[myidx][i] = new MyList();
		for (int i = 0; i < wordlist.length; i++) {
			if (i == myidx)
				continue;

			int sim = similarity(wordlist[i], wordlist[myidx]);

			table[myidx][sim].l.add(i);
		}
	}

	private int similarity(String s1, String s2) {
		int sim = 0;

		for (int i = 0; i < 6; i++)
			if (s1.charAt(i) == s2.charAt(i))
				sim++;
		return sim;
	}
}
