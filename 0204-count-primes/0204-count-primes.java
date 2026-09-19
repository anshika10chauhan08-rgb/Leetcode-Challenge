class Solution {
    public int countPrimes(int n) {

        if (n <= 2) {
            return 0;
        }

        byte[] prime = new byte[n];
        int count = 1; // 2 is prime

        // Mark odd numbers as prime
        for (int i = 3; i < n; i += 2) {
            prime[i] = 1;
        }

        // Sieve
        for (int i = 3; i * i < n; i += 2) {

            if (prime[i] == 1) {

                for (int j = i * i; j < n; j += i * 2) {
                    prime[j] = 0;
                }
            }
        }

        // Count primes
        for (int i = 3; i < n; i += 2) {
            if (prime[i] == 1) {
                count++;
            }
        }

        return count;
    }
}