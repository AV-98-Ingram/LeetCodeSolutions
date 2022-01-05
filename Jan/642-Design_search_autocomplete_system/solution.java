class AutocompleteSystem {

    class Trie {
	Map<Character, Trie> children = new HashMap<>();
	List<Integer> sentenceIDs = new LinkedList<>();

	Trie add(char c, int sentenceID) {
	    Trie child = children.computeIfAbsent(c, k->new Trie());

	    child.sentenceIDs.add(sentenceID);
	}

	Trie get(char c) {
	    return children.get(c);
	}
    }
    
    StringBuffer curr = new StringBuffer();
    Trie currTrie = hist;
    Trie hist = new Trie();
    ArrayList<String> sentences = new ArrayList<>();
    ArrayList<Integer> times = new ArrayList<>();
    
    public AutocompleteSystem(String[] sentences, int[] times) {
	final int len = times.length;

	for (int i = 0; i < len; i++) {
	    this.sentences.add(sentences[i]);
	    this.times.add(times[i]);

	    Trie trie = hist;
	    
	    for (char c : sentences[i].toCharArray()) 
		trie = trie.add(c, i);
	}
    }
    
    public List<String> input(char c) {
        if (c == '#') {
	    // ending:
	    if (currTrie == null) {
		String newSentence = sb.toString();
		int newId = sentences.size();
		
		sentences.add(newSentence);
		times.add(1);
		currTrie = hist;
		for (char cc : newSentence.toCharArray())
		    currTrie = currTrie.add(cc, newId);
		currTrie = hist;
	    } else {
		for (int id : currTrie.sentenceIDs)
		    times.set(id, times.get(id)+1);
	    }
	} else {
	    List<String> ret = new LinkedList<>();
	    
	    sb.append(c);
	    if (currTrie != null)
		currTrie = currTrie.get(c);
	    if (currTrie != null) {
		List<Integer> sentenceIDs = currTrie.sentenceIDs;
		PriorityQueue<Integer> pq = new PriorityQueue((i, j) -> compareSentenceID(i, j));
		
		// Get top 3 hot sentences:
		for (int id : sentenceIDs) {
		    if (pq.size() == 3) {
			if (compareSentenceID(pq.peek(), id) < 0) {
			    pq.poll();
			    pq.add(id);
			}
		    } else
			pq.add(id);
		}	
		while (!pq.isEmpty())
		    ret.add(pq.poll());
		return ret;
	    }
	}
    }

    private int compareSentenceID(int i, int j) {
	return times.get(i) != times.get(j) ?
	    Integer.compare(times.get(i), times.get(j)) :
	    String.compare(sentences.get(j), sentences.get(i));
    }
}
