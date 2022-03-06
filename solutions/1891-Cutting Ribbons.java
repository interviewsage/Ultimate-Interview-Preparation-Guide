// LeetCode Question URL: https://leetcode.com/problems/cutting-ribbons/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/cutting-ribbons/discuss/1263437/Java-Simple-Binary-Search-+-Explanation
 *
 * Time Complexity: O(N + N * log(max-minLenPerRibbon))
 *
 * Space Complexity: O(1)
 */
class Solution {
    public int maxLength(int[] ribbons, int k) {
        if (ribbons == null || ribbons.length == 0 || k <= 0) {
            return 0;
        }

        int numRibbons = ribbons.length;
        if (numRibbons == 1) {
            return ribbons[0] / k;
        }

        int maxLen = ribbons[0];
        int minLenPerRibbon = ribbons[0] / k;
        long totalLen = ribbons[0];
        for (int i = 1; i < numRibbons; i++) {
            maxLen = Math.max(maxLen, ribbons[i]);
            minLenPerRibbon = Math.min(minLenPerRibbon, ribbons[i] / k);
            totalLen += ribbons[i];
        }

        if (totalLen < k) {
            return 0;
        }
        if (k == 1) {
            return maxLen;
        }

        int start = minLenPerRibbon == 0 ? 1 : minLenPerRibbon;
        int end = maxLen;
        while (start < end) {
            int mid = start + (end - start) / 2 + 1;
            if (isPossibleToCut(ribbons, k, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    private boolean isPossibleToCut(int[] ribbons, int k, int targetLen) {
        for (int r : ribbons) {
            k -= r / targetLen;
            if (k <= 0) {
                return true;
            }
        }
        return false;
    }
}
