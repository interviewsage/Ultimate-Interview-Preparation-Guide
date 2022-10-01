// LeetCode Question URL: https://leetcode.com/problems/cutting-ribbons/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/cutting-ribbons/discuss/1263437/Java-Simple-Binary-Search-+-Explanation
 *
 * <pre>
 * Time Complexity:
 * 1. To calculate initial data from ribbons array --> O(N)
 * 2. Binary Search --> O(log (end-start+1)) & O(N) times for each search to check if the cut is possible.
 * Search Space:
 * end = Min (maxRibbonLen, totalRibbonLen / k)
 * start = numRibbons == k ==> minRibbonLen
 *       = numRibbons != k ==> Max (1, minRibbonLen/k)
 *
 * Total Time Complexity = O(N + N*log(end-start+1))
 * </pre>
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
        int minLen = ribbons[0];
        long totalLen = ribbons[0];

        for (int i = 1; i < numRibbons; i++) {
            maxLen = Math.max(maxLen, ribbons[i]);
            minLen = Math.min(minLen, ribbons[i]);
            totalLen += ribbons[i];
        }

        if (totalLen < k) {
            return 0;
        }
        if (totalLen == k) {
            return 1;
        }
        if (k == 1) {
            return maxLen;
        }

        int start = numRibbons == k ? minLen : Math.max(1, minLen / k);
        int end = maxLen;
        if (totalLen / k < end) {
            end = (int) (totalLen / k);
        }

        while (start < end) {
            int mid = start + (end - start) / 2 + 1;
            if (canCut(ribbons, k, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    private boolean canCut(int[] ribbons, int k, int len) {
        for (int r : ribbons) {
            k -= r / len;
            if (k <= 0) {
                return true;
            }
        }
        return false;
    }
}
