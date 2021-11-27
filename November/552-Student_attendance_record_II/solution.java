class Solution {

	/**
	 * DP: Let P(n) be the result.
	 * 
	 * To compute P(n), we could append 'P', 'L', or 'A' to records of length
	 * n-1. There are P(n-1) such records in total. But not all P(n-1) such
	 * records can be appended with all three of the letters.
	 * 
	 * 1) one can always append 'P' to any record so there are P(n-1) cases that
	 * can be appended to by 'P'.
	 * 
	 * 2) whether we can add 'L'? We cannot add 'L' if (n-1)-th and (n-2)-th
	 * places are both 'L' already. Visually,
	 * 
	 * <code>
	 * [ ... 'L', 'L'] 
	 *  0    n-2  n-1
	 * </code>
	 * 
	 * So what is the number of records of length n-1 that not end with "LL"?
	 * P(n-1) - P(n-3)? Wrong! Because (n-3)-th place also must not be L.
	 * 
	 * Here in addition to the function P, we need another function Q such that
	 * Q(i) represents the number of records of length i that contains no 'A'.
	 * The way compute Q is similar to P but simpler.
	 * 
	 * So in fact, the number of records of length n-1 that can be appended to
	 * by 'L' is P(n-1) - (P(n-4) + Q(n-4)). Why? <code>
	 * P(n-4) represents number of records of length n-3 ending with 'P';
	 * Q(n-4) represents number of records of length n-3 ending with 'A'
	 * </code> These two form all the cases that can be appened to by "LL" of
	 * length n-1.
	 * 
	 * Finally, what about 'A', we could only place 'A' there if there is no 'A'
	 * before. So there are simply Q(n-1) such records of length (n-1).
	 * 
	 * In the following code, we call P(i) "mayA(i)" and Q(i) "noA(i)".
	 */
	public int checkRecord(int n) {
		int[] mayA = new int[n + 3];
		int[] noA = new int[n + 3];

		noA[0] = 2;
		mayA[0] = 3;
		noA[1] = 4;
		mayA[1] = 8;
		noA[2] = noA[1] + (noA[1] - 1); // there is only one case of being "LLL"
		mayA[2] = mayA[1] + (mayA[1] - 1) + noA[1];
		noA[3] = noA[2] + (noA[2] - 1);
		mayA[3] = mayA[2] + (mayA[2] - 2) + noA[2];
		for (int i = 4; i < n; i++) {
			int noAEndingLL = (noA[i - 1] + 1000000007 - noA[i - 4])
					% 1000000007;

			noA[i] = noA[i - 1] + // for adding 'P'
					noAEndingLL; // for adding 'L'

			int mayAEndingLL = ((mayA[i - 1] + 1000000007 - mayA[i - 4])
					% 1000000007 + 1000000007 - noA[i - 4]) % 1000000007;

			mayA[i] = (mayA[i - 1] + // for adding 'P'
					mayAEndingLL) // for adding 'L'
					% 1000000007;
			mayA[i] += noA[i - 1]; // for adding 'A'
			noA[i] = noA[i] % 1000000007;
			mayA[i] = mayA[i] % 1000000007;
		}
		return mayA[n - 1];
	}

	public static void main(String[] args) {
		System.out.print(new Solution().checkRecord(100));
	}
}
