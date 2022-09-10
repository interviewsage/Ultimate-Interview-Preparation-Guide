// LeetCode Question URL: https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Prefix & Suffix counts
 *
 * Refer:
 * https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/discuss/1499860/Prefix-and-Suffix-Counts
 *
 * Time Complexity: O(N * 2L). Where L is bounded by the length of target.
 *
 * Space Complexity: O(4 * N).
 *
 * L = Average length of each number in the nums array.
 */
class Solution1 {
    public int numOfPairs(String[] nums, String target) {
        if (nums == null || nums.length <= 1 || target == null) {
            return 0;
        }

        Map<Integer, Integer> prefixCount = new HashMap<>();
        Map<Integer, Integer> suffixCount = new HashMap<>();
        int result = 0;
        int tLen = target.length();

        for (String n : nums) {
            int len = n.length();
            if (len > tLen) {
                continue;
            }

            boolean isPrefix = target.startsWith(n);
            boolean isSuffix = target.endsWith(n);
            result += isPrefix ? suffixCount.getOrDefault(tLen - len, 0) : 0;
            result += isSuffix ? prefixCount.getOrDefault(tLen - len, 0) : 0;

            if (isPrefix) {
                prefixCount.put(len, prefixCount.getOrDefault(len, 0) + 1);
            }
            if (isSuffix) {
                suffixCount.put(len, suffixCount.getOrDefault(len, 0) + 1);
            }
        }

        return result;
    }
}

/**
 * Count all nums and then find its suffix. This is better if there are
 * duplicates.
 *
 * Refer:
 * https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/discuss/1503157/C++-Simple-and-Easy-Solution-With-Detailed-Explanation
 *
 * Time Complexity: O(N + UN * T).
 *
 * Space Complexity: O(2*N + T).
 *
 * UN = Unique num in nums array. T = Length of target.
 */
class Solution2 {
    public int numOfPairs(String[] nums, String target) {
        if (nums == null || nums.length <= 1 || target == null) {
            return 0;
        }

        Map<String, Integer> countMap = new HashMap<>();
        int tLen = target.length();
        int maxLen = 0;
        for (String n : nums) {
            int len = n.length();
            if (len <= tLen) {
                countMap.put(n, countMap.getOrDefault(n, 0) + 1);
                maxLen = Math.max(maxLen, len);
            }
        }

        if (maxLen < tLen / 2) {
            return 0;
        }

        int result = 0;
        for (String n : countMap.keySet()) {
            if (!target.startsWith(n)) {
                continue;
            }
            int prefixCount = countMap.get(n);
            String suffix = target.substring(n.length());
            if (n.equals(suffix)) {
                result += prefixCount * (prefixCount - 1);
            } else {
                Integer suffixCount = countMap.get(suffix);
                if (suffixCount != null) {
                    result += prefixCount * suffixCount;
                }
            }
        }

        return result;
    }
}
