import java.util.Arrays;

class Solution {

    public int numDistinct(String s, String t) {
	if (s.length() == 0 || t.length() == 0)
	    return 0;

	// nss[i][j]: number of subsequences in s[0..i] that is equal to
	// t[0..j]:

	char[] sArr = s.toCharArray();
	char[] tArr = t.toCharArray();
	int[][] nss = new int[sArr.length][tArr.length];

	Arrays.fill(nss[0], 0);
	nss[0][0] = sArr[0] == tArr[0] ? 1 : 0;
	for (int i = 1; i < sArr.length; i++)
	    nss[i][0] = nss[i - 1][0] + (sArr[i] == tArr[0] ? 1 : 0);
	for (int i = 1; i < sArr.length; i++) {
	    final int jub = Math.min(i + 1, tArr.length);

	    for (int j = 1; j < jub; j++) {
		if (sArr[i] == tArr[j])
		    nss[i][j] = nss[i - 1][j] + nss[i - 1][j - 1];
		else
		    nss[i][j] = nss[i - 1][j];
	    }
	}
	return nss[sArr.length - 1][tArr.length - 1];
    }

    public static void main(String[] args) {
	System.out.println(new Solution().numDistinct("ddd", "dd"));
    }
}
