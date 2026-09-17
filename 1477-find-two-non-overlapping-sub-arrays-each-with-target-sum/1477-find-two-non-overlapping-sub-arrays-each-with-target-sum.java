class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        
        int n = arr.length;
        int[] best = new int[n];
        
        int left = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        
        // best[i] = minimum length of a valid subarray
        // ending at or before index i
        int minLen = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            
            sum += arr[right];
            
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            
            // Carry forward the previous minimum
            if (right > 0) {
                best[right] = best[right - 1];
            } else {
                best[right] = Integer.MAX_VALUE;
            }
            
            // Found a subarray with sum = target
            if (sum == target) {
                
                int len = right - left + 1;
                
                // Need another subarray completely before 'left'
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, len + best[left - 1]);
                }
                
                // Update best valid subarray
                best[right] = Math.min(best[right], len);
            }
            
            minLen = Math.min(minLen, best[right]);
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}