// LeetCode Question URL: https://leetcode.com/problems/summary-ranges/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(N). This is better id N >= 25
 * (https://www.desmos.com/calculator/fjz9kqthed)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public List<String> summaryRanges(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        List<String> result = new ArrayList<>();
        int len = nums.length;
        int i = 0;

        while (i < len) {
            int start = nums[i++];
            // Commented condition will not work as it causes overflow
            // while (i < len && (nums[i] - nums[i-1] <= 1)) {
            while (i < len && (nums[i - 1] == nums[i] || nums[i - 1] + 1 == nums[i])) {
                i++;
            }

            result.add(getRangeStr(nums, start, nums[i - 1]));
        }

        return result;
    }

    private String getRangeStr(int[] nums, int x, int y) {
        if (x == y) {
            return String.valueOf(x);
        }
        return new StringBuilder().append(x).append("->").append(y).toString();
    }
}

/**
 * Inspired from:
 * https://leetcode.com/problems/summary-ranges/discuss/63236/My-java-0ms(not-always-Luckily-!You-are-here!-Your-runtime-beats-97.90-of-java-submissions.)
 *
 * Time Complexity: O(log(N!)). This is better if N < 25
 * (https://www.desmos.com/calculator/fjz9kqthed)
 *
 * <pre>
 * log(N) + log(N-1) + log(N-2) + ... + log(2) + log(1) --> In worst case, if all numbers do not form a range, search space is reduced by one in each iteration.
 * = log(N!)
 * </pre>
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution2 {
    public List<String> summaryRanges(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        List<String> result = new ArrayList<>();
        int len = nums.length;
        int i = 0;

        while (i < len) {
            int end = binarySearchHelper(nums, i);
            result.add(getRangeStr(nums, nums[i], nums[end]));
            i = end + 1;
        }

        return result;
    }

    private int binarySearchHelper(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start + 1) / 2;
            if (nums[mid] - nums[start] == mid - start) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    private String getRangeStr(int[] nums, int x, int y) {
        if (x == y) {
            return String.valueOf(x);
        }
        return new StringBuilder().append(x).append("->").append(y).toString();
    }
}

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1) -> Excluding the result list. O(N) -> Including the
 * result list
 *
 * N = Length of the input array.
 */
class Solution3 {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int len = nums.length;
        // Set the initial start as the zero index.
        int start = 0;

        for (int i = 1; i < len; i++) {
            // Extend the range if consecutive numbers are found
            if (nums[i] == nums[i - 1] + 1) {
                continue;
            }

            // Add the found range. Note, current index is not part of the range as the
            // above if condition was false.
            result.add(getRange(nums, start, i - 1));

            // Set the new start.
            start = i;
        }

        // Add the final range in the result list. Because, above for loop does not add
        // the range that includes the last number.
        result.add(getRange(nums, start, len - 1));
        return result;
    }

    private String getRange(int[] nums, int start, int end) {
        if (start == end) {
            return String.valueOf(nums[start]);
        } else {
            return nums[start] + "->" + nums[end];
        }
    }
}
