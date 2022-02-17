class Solution {
    public int findComplement(int num) {
	int i;

	// find the highest 1:
	for (i = 31; i >= 0; i--) {
	    int bit = num & (1 << i);

	    if (bit == 0)
		continue;
	    else
		break;
	}

	// then create a mask:
	int mask = ((1 << (i + 1)) - 1);

	return num ^ mask;
    }

    public static void main(String[] args) {
	new Solution().findComplement(5);
    }
}
