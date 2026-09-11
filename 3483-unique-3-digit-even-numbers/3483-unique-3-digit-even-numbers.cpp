class Solution {
public:
    int totalNumbers(vector<int>& digits) {
        int freq[10] = {0};

        // Count how many times each digit occurs
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        // Generate all 3-digit numbers
        for (int num = 100; num <= 999; num++) {
            
            // Number must be even
            if (num % 2 != 0)
                continue;

            int a = num / 100;        
            int b = (num / 10) % 10;  
            int c = num % 10;         

            int used[10] = {0};
            used[a]++;
            used[b]++;
            used[c]++;

            bool possible = true;

            for (int i = 0; i < 10; i++) {
                if (used[i] > freq[i]) {
                    possible = false;
                    break;
                }
            }

            if (possible)
                count++;
        }

        return count;
    }
};