class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        List<String> ans = new ArrayList<>();

        int n = s.length();

        // First and last occurrence of every character
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';

            first[ch] = Math.min(first[ch], i);
            last[ch] = i;
        }

        // Store valid intervals: [start, end]
        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {

            if (last[c] == -1)
                continue;

            int start = first[c];
            int end = last[c];

            boolean valid = true;

            // Expand the interval if it contains another character
            // whose complete occurrence range goes outside it.
            for (int i = start; i <= end; i++) {

                int ch = s.charAt(i) - 'a';

                if (first[ch] < start) {
                    valid = false;
                    break;
                }

                end = Math.max(end, last[ch]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> a[1] - b[1]);

        int previousEnd = -1;

        // Greedily select intervals that don't overlap
        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            if (start > previousEnd) {
                ans.add(s.substring(start, end + 1));
                previousEnd = end;
            }
        }

        return ans;
    }
}