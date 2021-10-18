import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public int ladderLength(String beginWord, String endWord,
			    List<String> wordList) {
	int wLen = beginWord.length();
	int wordListSize = wordList.size();
	long pow = (int) Math.pow(26, wLen);
	if (pow > wordListSize * wordListSize)
	    return ladderLength1(beginWord, endWord, wordList);
	else
	    return ladderLength2(beginWord, endWord, wordList);
    }

    private int ladderLength2(String beginWord, String endWord,
			      List<String> wordList) {
	Map<String, String> unvisited = new HashMap<>();
	int wordLen = beginWord.length();

	for (String s : wordList)
	    unvisited.putIfAbsent(s, s);
	endWord = unvisited.get(endWord);
	if (endWord == null)
	    return 0;

	List<String> thisLevelWords = new LinkedList<>();
	List<String> nextLevelWords = new LinkedList<>();
	int nwords = 1;

	thisLevelWords.add(beginWord);
	while (!thisLevelWords.isEmpty()) {
	    for (String tw : thisLevelWords) {
		if (tw == endWord)
		    return nwords;
		unvisited.remove(tw);
	    }
	    for (String tw : thisLevelWords) {
		char[] twArr = tw.toCharArray();

		for (int i = 0; i < wordLen; i++) {
		    char old = twArr[i];

		    for (char c = 'a'; c <= 'z'; c++) {
			if (c == old)
			    continue;
			twArr[i] = c;

			String next = unvisited.get(new String(twArr));

			if (next != null)
			    nextLevelWords.add(next);
			twArr[i] = old;
		    }
		}
	    }

	    List<String> tmp = thisLevelWords;

	    thisLevelWords = nextLevelWords;
	    nextLevelWords = tmp;
	    nextLevelWords.clear();
	    nwords++;
	}
	return 0;
    }

    private int ladderLength1(String beginWord, String endWord,
			      List<String> wordList) {
	Map<String, List<String>> edges = new HashMap<>();
	String[] wordArray;
	String theEndWord = null;
	List<String> starters = new LinkedList<>();

	wordArray = new String[wordList.size()];
	wordList.toArray(wordArray);
	for (int i = 0; i < wordArray.length; i++) {
	    for (int j = i + 1; j < wordArray.length; j++)
		if (isSimilar(wordArray[i], wordArray[j])) {
		    List<String> l = edges.computeIfAbsent(wordArray[i],
							   k -> new LinkedList<>());
		    l.add(wordArray[j]);
		    l = edges.computeIfAbsent(wordArray[j],
					      k -> new LinkedList<>());
		    l.add(wordArray[i]);
		}
	    if (wordArray[i].equals(endWord))
		theEndWord = wordArray[i];
	    if (isSimilar(beginWord, wordArray[i]))
		starters.add(wordArray[i]);
	}
	if (theEndWord == null)
	    return 0;
	else
	    endWord = theEndWord;

	int nwords = 1;
	Set<String> unvisited = new HashSet<>(wordList);
	List<String> thisLevelWords = new LinkedList<>();
	List<String> nextLevelWords = new LinkedList<>();

	thisLevelWords.addAll(starters);
	unvisited.remove(beginWord);
	while (!thisLevelWords.isEmpty()) {

	    unvisited.removeAll(thisLevelWords);
	    for (String word : thisLevelWords) {
		if (word == endWord)
		    return nwords + 1;

		List<String> wordNexts = edges.get(word);

		if (wordNexts != null)
		    for (String wordNext : wordNexts)
			if (unvisited.contains(wordNext))
			    nextLevelWords.add(wordNext);
	    }
	    nwords++;

	    List<String> tmp = thisLevelWords;

	    thisLevelWords = nextLevelWords;
	    nextLevelWords = tmp;
	    nextLevelWords.clear();
	}
	return 0;
    }

    private boolean isSimilar(String a, String b) {
	int len = a.length();
	int numDiff = 0;

	for (int i = 0; i < len; i++)
	    numDiff += a.charAt(i) == b.charAt(i) ? 0 : 1;
	return numDiff == 1;
    }

    public static void main(String[] args) {
	System.out.println(new Solution().ladderLength("hit", "cog",
						       Arrays.asList(new String[] { "hot", "dot", "dog", "lot", "log",
										    "cog" })));
    }
}
