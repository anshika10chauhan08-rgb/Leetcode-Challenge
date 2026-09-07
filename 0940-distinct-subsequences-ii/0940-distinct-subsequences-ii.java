class Solution {
    public int distinctSubseqII(String s) {
        long MOD = 1000000007;
        
        // dp[c] = number of distinct subsequences
        // whose last character is c
        long[] dp = new long[26];

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';

            long total = 1; // empty subsequence

            // All existing subsequences + current character
            for (int i = 0; i < 26; i++) {
                total = (total + dp[i]) % MOD;
            }

            // Current character gets all these subsequences
            dp[c] = total;
        }

        long ans = 0;

        for (long x : dp) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }
}