// LeetCode Question URL: https://leetcode.com/problems/longest-consecutive-sequence/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using HashMap Keep track of the sequence length and store that in the
 * boundary points of the sequence.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input nums array.
 */
class Solution1 {
    public int longestConsecutive(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLen = 0;

        for (int n : nums) {
            if (map.containsKey(n)) {
                continue;
            }

            int left = map.getOrDefault(n - 1, 0);
            int right = map.getOrDefault(n + 1, 0);
            int len = left + right + 1;
            maxLen = Math.max(maxLen, len);

            if (left > 0) {
                map.put(n - left, len);
            }
            if (right > 0) {
                map.put(n + right, len);
            }
            map.put(n, len);
        }

        return maxLen;
    }
}

/**
 * Using HashSet
 *
 * Time Complexity: O(3 * N) = O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input nums array.
 */
class Solution2 {
    public int longestConsecutive(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        int maxLen = 0;
        for (int n : nums) {
            if (set.contains(n - 1)) {
                continue;
            }

            int len = 0;
            while (set.remove(n++)) {
                len++;
            }
            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }
}
