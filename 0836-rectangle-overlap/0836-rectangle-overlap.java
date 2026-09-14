class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

        // Check horizontal overlap
        boolean horizontal = rec1[2] > rec2[0] && rec2[2] > rec1[0];

        // Check vertical overlap
        boolean vertical = rec1[3] > rec2[1] && rec2[3] > rec1[1];

        // Both must overlap
        return horizontal && vertical;
    }
}