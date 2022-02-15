import java.util.HashSet;
import java.util.Set;

class Solution {
    public int smallestRepunitDivByK(int k) {
	Set<Integer> seenMods = new HashSet<>();
	int i = 1;
	int mod = 0;
	int num = 1;

	while (seenMods.add(mod)) {
	    mod = num % k;
	    if (mod == 0)
		return i;
	    i++;
	    num = mod * 10 + 1;
	}
	return -1;
    }
}
