// LeetCode Question URL: https://leetcode.com/problems/koko-eating-bananas/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/koko-eating-bananas/discuss/152324/JavaC++Python-Binary-Search
 *
 * Find the max number of bananas in a pile. Use Binary Search to find the
 * hourly eating rate between 1 and max.
 *
 * Time Complexity: O(N + N * log (max-avg))
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array. max = Maximum number in the input array. avg =
 * Average of all piles.
 */
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        if (h <= 0 || piles == null || piles.length > h) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = piles.length;

        if (len == 0) {
            return 0;
        }

        int max = piles[0];
        long sum = piles[0];
        for (int i = 1; i < len; i++) {
            max = Math.max(max, piles[i]);
            sum += piles[i];
        }

        if (h == len) {
            return max;
        }

        int start = (int) (sum / h) + (sum % h != 0 ? 1 : 0);
        int end = max;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isPossible(piles, h, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private boolean isPossible(int[] piles, int h, int speed) {
        for (int p : piles) {
            h -= p / speed + (p % speed != 0 ? 1 : 0);
            if (h < 0) {
                return false;
            }
        }

        return true;
    }
}
