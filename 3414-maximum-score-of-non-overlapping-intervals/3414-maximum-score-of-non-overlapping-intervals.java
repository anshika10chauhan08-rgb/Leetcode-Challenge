class Solution {

    static class State {
        long score;
        int[] ids;

        State() {
            score = 0;
            ids = new int[0];
        }

        State(State other) {
            score = other.score;
            ids = other.ids.clone();
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[1], b[1]));

        State[][] dp = new State[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State();
            }
        }

        for (int i = 1; i <= n; i++) {

            int left = arr[i - 1][0];
            int weight = arr[i - 1][2];
            int index = arr[i - 1][3];

            int p = lowerBound(arr, i - 1, left);

            for (int k = 1; k <= 4; k++) {

                State skip = dp[i - 1][k];

                State take = new State(dp[p][k - 1]);

                take.score += weight;

                int[] newIds = new int[take.ids.length + 1];

                for (int j = 0; j < take.ids.length; j++) {
                    newIds[j] = take.ids[j];
                }

                newIds[newIds.length - 1] = index;

                Arrays.sort(newIds);

                take.ids = newIds;

                if (better(take, skip)) {
                    dp[i][k] = take;
                } else {
                    dp[i][k] = new State(skip);
                }
            }
        }

        return dp[n][4].ids;
    }

    private int lowerBound(int[][] arr, int count, int left) {

        int low = 0;
        int high = count;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr[mid][1] < left) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private boolean better(State a, State b) {

        if (a.score != b.score) {
            return a.score > b.score;
        }

        int n = Math.min(a.ids.length, b.ids.length);

        for (int i = 0; i < n; i++) {

            if (a.ids[i] != b.ids[i]) {
                return a.ids[i] < b.ids[i];
            }
        }

        return a.ids.length < b.ids.length;
    }
}