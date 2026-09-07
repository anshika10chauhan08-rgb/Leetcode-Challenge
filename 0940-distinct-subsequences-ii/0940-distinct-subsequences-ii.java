class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007L;

        long total = 0;
        long[] last = new long[26];

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';

            long add = total + 1;

            if (add >= MOD) {
                add -= MOD;
            }

            total += add - last[c];

            if (total < 0) {
                total += MOD;
            } else if (total >= MOD) {
                total -= MOD;
            }

            last[c] = add;
        }

        return (int) total;
    }
}