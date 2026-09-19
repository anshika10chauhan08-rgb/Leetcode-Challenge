class Solution {
    public int countPrimes(int n) {

        if (n <= 2) {
            return 0;
        }

        boolean[] isPrime = new boolean[n];

        int count = 1; // 2 is prime

        for (int i = 3; i < n; i += 2) {
            isPrime[i] = true;
        }

        for (int i = 3; i * i < n; i += 2) {

            if (isPrime[i]) {

                for (int j = i * i; j < n; j += 2 * i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 3; i < n; i += 2) {

            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }
}