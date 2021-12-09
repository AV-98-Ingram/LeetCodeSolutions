class Solution {

    final static int MOD_VAL = 1000000007;
    
    public int distinctSubseqII(String s) {
        int[] table = new int[26];
        final int len = s.length();
        int[] dp = new int[len];

        Arrays.fill(table, -1);
        dp[0] = 1;
        table[s.charAt(0) - 'a'] = 0;
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            int lastPos = table[c - 'a'];

            if (lastPos == 0) {
                dp[i] = (dp[i-1] * 2) % MOD_VAL;
            } else if (lastPos > 0) {
                dp[i] = ((dp[i-1] + MOD_VAL - dp[lastPos-1])%MOD_VAL + dp[i-1])%MOD_VAL;
            } else {
                dp[i] = (dp[i-1] * 2 + 1) % MOD_VAL;
            }
            table[c - 'a'] = i;
        }
        return dp[len-1] % MOD_VAL;
    }
}

