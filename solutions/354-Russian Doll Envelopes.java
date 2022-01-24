// LeetCode Question URL: https://leetcode.com/problems/russian-doll-envelopes/
// LeetCode Discuss URL:

import java.util.*;

/**
 * DP + Sort
 *
 * Very similar to 300-Longest Increasing Subsequence question in LeetCode.
 *
 * Refer:
 * https://leetcode.com/problems/russian-doll-envelopes/discuss/82763/Java-NLogN-Solution-with-Explanation
 *
 * Time Complexity: O(N^2 + N log N) - DP + Sorting
 *
 * Space Complexity: O(N + Space taken by sorting algo)
 *
 * N = Number of envelopes.
 */
class Solution1 {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null) {
            return 0;
        }

        int numEnvelopes = envelopes.length;
        if (numEnvelopes <= 1) {
            return numEnvelopes;
        }

        // Sort the array. Ascend on width and descend on height if width are same.

        // [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when
        // sorting otherwise it will be counted as an increasing number if the order is
        // [3, 3], [3, 4]
        Arrays.sort(envelopes, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]));

        int[] dp = new int[numEnvelopes];
        dp[0] = 1;
        int maxCount = 1;

        for (int i = 1; i < numEnvelopes; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxCount = Math.max(maxCount, dp[i]);
        }

        return maxCount;
    }
}

/**
 * Sort + Binary Search
 *
 * Very similar to 300-Longest Increasing Subsequence question in LeetCode.
 *
 * Refer:
 * https://leetcode.com/problems/russian-doll-envelopes/discuss/82763/Java-NLogN-Solution-with-Explanation
 *
 * Time Complexity: O(N log N) - Sorting & Binary Search both have same.
 *
 * Space Complexity: O(N + Space taken by sorting algo)
 *
 * N = Number of envelopes.
 */
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null) {
            return 0;
        }

        int numEnvelopes = envelopes.length;
        if (numEnvelopes <= 1) {
            return numEnvelopes;
        }

        // Sort the array. Ascend on width and descend on height if width are same.

        // [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when
        // sorting otherwise it will be counted as an increasing number if the order is
        // [3, 3], [3, 4]
        Arrays.sort(envelopes, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]));
        List<Integer> increasingHeightSeq = new ArrayList<>();
        increasingHeightSeq.add(envelopes[0][1]);

        for (int i = 1; i < numEnvelopes; i++) {
            int start = 0;
            int end = increasingHeightSeq.size();

            while (start < end) {
                int mid = start + (end - start) / 2;
                if (envelopes[i][1] > increasingHeightSeq.get(mid)) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

            if (start == increasingHeightSeq.size()) {
                increasingHeightSeq.add(envelopes[i][1]);
            } else {
                increasingHeightSeq.set(start, envelopes[i][1]);
            }
        }

        return increasingHeightSeq.size();
    }
}
