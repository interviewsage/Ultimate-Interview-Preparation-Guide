// LeetCode Question URL: https://leetcode.com/problems/longest-increasing-subsequence/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Very similar solution to Word Break.
 *
 * DP[i] = Length of longest increasing subsequence ending at i.
 *
 * DP[i] = Math.max(DP[i], DP[j]+1). where j = 0 -> i-1 and if nums[j] < nums[i]
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input array.
 */
class Solution1 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        int[] dp = new int[len];
        dp[0] = 1;
        int maxLen = 1;

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i]++;
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }
}

// Description from discuss.

// tails is an array storing the smallest tail of all the increasing
// subsequences with length i+1 in tails[i].

// For example, say we have nums = [4,5,6,3], then all the available increasing
// subsequences are:

// len = 1 : [4], [5], [6], [3] => tails[0] = 3
// len = 2 : [4, 5], [5, 6] => tails[1] = 5
// len = 3 : [4, 5, 6] => tails[2] = 6

// We can easily prove that tails is a increasing array. Therefore it is
// possible to do a binary search in tails array to find the one needs update.

// Each time we only do one of the two:

// (1) if x is larger than all tails, append it, increase the size by 1
// (2) if tails[i-1] < x <= tails[i], update tails[i]

// Doing so will maintain the tails invariant. The the final answer is just the
// size.

/**
 * Binary Search using tails array.
 *
 * Refer:
 * https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
 *
 * <pre>
 * increasingSequence is an array storing the longest increasing subsequences with smallest values.
 *
 * increasingSequence will be an increasing array as we are using binary search
 * to find an index where all elements on left side are smaller than the current
 * number.
 *
 * Each time we only do one of the two:
 * 1. if x is larger than all increasingSequence, append it, increase the size by 1
 * 2. if increasingSequence[i-1] < x <= increasingSequence[i], update increasingSequence[i]
 *
 * Doing so will maintain the increasingSequence. The the final answer is just the size.
 * </pre>
 *
 * Time Complexity : O(N * log N)
 *
 * Space Complexity: O(N)
 *
 * N = length of the input nums array.
 */
class Solution2 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        List<Integer> increasingSequence = new ArrayList<>();
        increasingSequence.add(nums[0]);

        for (int i = 1; i < len; i++) {
            // Here search space is from 0 to size as we might have to add the element at
            // size index.
            int start = 0;
            int end = increasingSequence.size();

            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[i] > increasingSequence.get(mid)) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

            if (start == increasingSequence.size()) {
                // Number was added at the size index as it was greater than other elements in
                // the increasingSequence array.
                increasingSequence.add(nums[i]);
            } else {
                increasingSequence.set(start, nums[i]);
            }
        }

        return increasingSequence.size();
    }
}
