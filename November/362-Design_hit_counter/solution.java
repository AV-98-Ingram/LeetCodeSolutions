import java.util.ArrayList;

/**
   Idea:

   Use an ArrayList to maintain all the hitted-timestamp entries.
   Entries in the ArrayList are ordered always.  ArrayList provides
   constant time chop-head, append and random access operations.  To
   remove out-dated enties, we only need to keep doing chop-head.  To
   add new entries, we only need to append to the list.  For multiple
   hits on the same timestamp, it must happens on the entry at the
   tail of the list.
 
   In fact, we don't need ArrayList.  A LinkedList will do as well.
 */
class HitCounter {

    /**
     * <code>
     foreach hit : hits 
     hit[0]: timestamp
     hit[1]: number of hits
     </code>
    */
    ArrayList<int[]> hits = new ArrayList<>();
    
    public HitCounter() {
	
    }
    
    public void hit(int timestamp) {
	if (hits.isEmpty()) {
	    // init:
	    hits.add(new int[] { timestamp, 1 });
	    return;
	}
	
	final int size = hits.size();
	
	if (hits.get(size - 1)[0] == timestamp) {
	    // if this is a multi-hit at latest timestamp:
	    hits.get(size - 1)[1]++;
	} else {
	    // this is a new time hit, try to remove outdated entry
	    // then append the new hit
	    final int timeUB = timestamp - 300;
	    
	    while (!hits.isEmpty() && hits.get(0)[0] < timeUB)
		hits.remove(0);
	    
	    hits.add(new int[] { timestamp, 1 });
	}
    }
    
    public int getHits(int timestamp) {
	final int size = hits.size();
	final int timeLB = timestamp - 300;
	int i;
	int result = 0;
	
	for (i = size - 1; i >= 0; i--) {
	    if (hits.get(i)[0] > timeLB)
		result += hits.get(i)[1];
	    else
		break;
	}
	for (; i >= 0; i--) 
	    hits.remove(0);
	return result;
    }
}
