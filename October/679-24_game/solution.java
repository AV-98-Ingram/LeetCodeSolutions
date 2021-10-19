import java.util.HashSet;
import java.util.Set;

class Solution {

    private class Real {
	private int numer;
	private int denom;

	Real(int numer, int denom) {
	    this.numer = numer;
	    this.denom = denom;
	}

	Integer getInteger() {
	    if (numer > denom && numer % denom == 0) {
		return numer / denom;
	    } else
		return null;
	}

	Real add(Real x) {
	    return new Real(numer * x.denom + x.numer * denom, denom * x.denom);
	}

	Real subtract(Real x) {
	    return new Real(numer * x.denom - x.numer * denom, denom * x.denom);
	}

	Real times(Real x) {
	    return new Real(numer * x.numer, denom * x.denom);
	}

	Real div(Real x) {
	    return new Real(numer * x.denom, denom * x.numer);
	}

	boolean isZero() {
	    return numer == 0;
	}

	@Override
	public String toString() {
	    return numer + "/" + denom;
	}
    }

    Set<int[]> cache = new HashSet<>();

    public boolean judgePoint24(int[] cards) {
	Real[] reals = new Real[cards.length];
	int i = 0;

	for (int card : cards)
	    reals[i++] = new Real(card, 1);
	return f(reals);
    }

    private boolean f(Real[] cards) {
	if (cards.length == 1) {
	    Integer i = cards[0].getInteger();

	    if (i != null)
		return i == 24;
	    return false;
	}

	for (int i = 0; i < cards.length; i++)
	    for (int j = i + 1; j < cards.length; j++) {
		Real[] newCards = mkNewCards(i, j, cards);

		newCards[0] = cards[i].add(cards[j]);
		if (f(newCards))
		    return true;
		newCards[0] = cards[i].subtract(cards[j]);
		if (f(newCards))
		    return true;
		newCards[0] = cards[j].subtract(cards[i]);
		if (f(newCards))
		    return true;
		newCards[0] = cards[i].times(cards[j]);
		if (f(newCards))
		    return true;
		if (!cards[i].isZero()) {
		    newCards[0] = cards[j].div(cards[i]);
		    if (f(newCards))
			return true;
		}
		if (!cards[j].isZero()) {
		    newCards[0] = cards[i].div(cards[j]);
		    if (f(newCards))
			return true;
		}
	    }
	return false;
    }

    private Real[] mkNewCards(int x, int y, Real[] cards) {
	Real[] ret = new Real[cards.length - 1];
	int p = 1;

	for (int i = 0; i < cards.length; i++)
	    if (i != x && i != y)
		ret[p++] = cards[i];
	return ret;
    }

    public static void main(String[] args) {
	new Solution().judgePoint24(new int[] { 1, 3, 4, 6 });
    }
}
