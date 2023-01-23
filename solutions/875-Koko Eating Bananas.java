// LeetCode Question URL: https://leetcode.com/problems/koko-eating-bananas/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/koko-eating-bananas/discuss/152324/JavaC++Python-Binary-Search
 *
 * Find the max number of bananas in a pile. Use Binary Search to find the
 * hourly eating rate between average and max.
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
        if (piles == null || h <= 0 || piles.length > h) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int numPiles = piles.length;
        if (numPiles == 0) {
            return 0;
        }
        if (h == 1) {
            return piles[0];
        }
        if (numPiles == 1) {
            return piles[0] / h + (piles[0] % h != 0 ? 1 : 0);
        }

        int max = 0;
        int sum = 0;
        for (int p : piles) {
            max = Math.max(max, p);
            sum += p;
        }

        if (numPiles == h) {
            return max;
        }

        int start = sum / h + (sum % h != 0 ? 1 : 0);
        int end = max;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (canEatInAtMostHHours(piles, h, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private boolean canEatInAtMostHHours(int[] piles, int h, int speed) {
        for (int p : piles) {
            h -= p / speed + (p % speed != 0 ? 1 : 0);
            if (h < 0) {
                return false;
            }
        }
        return true;
    }
}
