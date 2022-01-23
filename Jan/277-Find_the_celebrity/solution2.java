public class Solution extends Relation {
    public int findCelebrity(int n) {
	int cand = 0;

	// loop invariant: forall k; 0 <= k < i ==> k != cand ==> knows(k, someone) || !knows(someone, k)
	
	/* Note that the predicate knows(k, someone) || !knows(someone, k) means that k is NOT a celebrity.
           We write this predicate as notCeleb(k) for brevity.

	   Proof: 
	     base : 0 <= k < 1 ==> k != 0 ==> notCeleb(k), vacuously true

	     suppose: 0 <= k < i ==> k != cand ==> notCeleb(k)

	        case 1: knows(cand, i) and cand := i
		        We know that 
			k == i ==> k != cand ==> notCeleb(k) is vacuously true
			Thus 0 <= k < i+1 ==> k != cand ==> notCeleb(k) is true

		case 2: !knows(cand, i) and cand is unchanged
		        We know that i is NOT a celebrity, thus
			k == i ==> k != cand ==> notCeleb(k) is true
			Therefore 0 <= k < i+1 ==> k != cand ==> notCeleb(k) is true
	 */
	for (int i = 1; i < n; i++) {
	    if (knows(cand, i))
		cand = i;
	}
	for (int i = 0; i < n; i++)
	    if (i != cand)
		if (!knows(i, cand) || knows(cand, i))
		    return -1;
	return cand;	
    }
}
