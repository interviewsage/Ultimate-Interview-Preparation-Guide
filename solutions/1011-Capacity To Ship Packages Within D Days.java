// LeetCode Question URL: https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
// LeetCode Discuss URL:

/**
 * Binary Search
 *
 * This question is exactly same as 410-Split Array Largest Sum.
 * https://leetcode.com/problems/split-array-with-equal-sum/
 *
 * The answer is between maximum value of input array weights and sum of those
 * weights.
 *
 * Use binary search to approach the correct answer. We have l = max weight; r =
 * sum of all weights in the array; Every time we do mid = (l + r) / 2;
 *
 * Use greedy to narrow down left and right boundaries in binary search.
 *
 * 1. Cut the array from left.
 *
 * 2. Try our best to make sure that the sum of weights between each two cuts
 * (inclusive) is large enough but still less than mid.
 *
 * 3. We'll end up with two results: either we can divide the array into more
 * than D subarrays or we cannot.
 *
 * If we can, it means that the mid value we pick is too small because we've
 * already tried our best to make sure each part holds as many packages as we
 * can but we still have packages left. So, it is impossible to cut the array
 * into D parts and make sure each parts is no larger than mid. We should
 * increase D. This leads to l = mid + 1;
 *
 * If we can't, it is either we successfully divide the array into D parts and
 * the sum of each part is less than mid, or we used up all packages before we
 * reach D. Both of them mean that we should lower mid because we need to find
 * the minimum one. This leads to r = mid;
 *
 * Time Complexity: O(N + N * log(Sum-Max)) = O(N * log S)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array. S = Sum of all weights in the array.
 */
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (weights == null || weights.length == 0) {
            return 0;
        }

        int len = weights.length;
        int maxWeight = weights[0];
        int totalWeight = weights[0];
        for (int i = 1; i < len; i++) {
            maxWeight = Math.max(maxWeight, weights[i]);
            totalWeight += weights[i];
        }

        if (days == 1) {
            return totalWeight;
        }
        if (len <= days) {
            return maxWeight;
        }

        int start = maxWeight;
        int end = totalWeight;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isPossible(weights, days, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private boolean isPossible(int[] weights, int days, int targetWeight) {
        int curTotalWeight = weights[0];
        for (int i = 1; i < weights.length; i++) {
            curTotalWeight += weights[i];
            if (curTotalWeight > targetWeight) {
                if (days == 1) {
                    return false;
                }
                days--;
                curTotalWeight = weights[i];
            }
        }
        return true;
    }
}
