class Solution {
    public int missingMultiple(int[] nums, int k) {
        
        int multiple = k;

        while (true) {
            
            boolean found = false;

            // Check if multiple exists in nums
            for (int num : nums) {
                if (num == multiple) {
                    found = true;
                    break;
                }
            }

            // If not found, this is the answer
            if (!found) {
                return multiple;
            }

            multiple += k;
        }
    }
}