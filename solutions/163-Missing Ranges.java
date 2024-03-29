// LeetCode Question URL: https://leetcode.com/problems/missing-ranges/
// LeetCode Discuss URL:

import java.util.*;

/**
 * This solution will also work if the numbers in nums array are in
 * [Integer.MIN_VALUE, Integer.MAX_VALUE] range but may be out of [lower, upper]
 * range.
 *
 * Time Complexity: O(logN + N) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of nums array.
 */
class Solution1 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        if (nums == null || lower > upper) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<String> result = new ArrayList<>();

        int len = nums.length;
        if (len == 0 || nums[len - 1] < lower || nums[0] > upper) {
            result.add(getRangeStr(lower, upper));
            return result;
        }
        if (nums[len - 1] == lower) {
            if (lower < upper) {
                result.add(getRangeStr(lower + 1, upper));
            }
            return result;
        }
        if (nums[0] == upper) {
            if (lower < upper) {
                result.add(getRangeStr(lower, upper - 1));
            }
            return result;
        }

        int idx = Arrays.binarySearch(nums, lower);
        if (lower == upper) {
            if (idx < 0) {
                result.add(getRangeStr(lower, upper));
            }
            return result;
        }
        if (idx < 0) {
            idx = -idx - 1;
        }

        while (idx < len) {
            if (nums[idx] >= upper) {
                if (nums[idx] == upper) {
                    upper--;
                }
                break;
            }

            if (nums[idx] > lower) {
                result.add(getRangeStr(lower, nums[idx] - 1));
            }

            lower = nums[idx++] + 1;
        }

        if (lower <= upper) {
            result.add(getRangeStr(lower, upper));
        }

        return result;
    }

    private static String getRangeStr(int a, int b) {
        return a == b ? String.valueOf(a) : new StringBuilder().append(a).append("->").append(b).toString();
    }
}

class Solution2 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        if (nums == null || lower > upper) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<String> result = new ArrayList<>();

        for (int idx = 0; idx < nums.length; idx++) {
            if (nums[idx] == upper) {
                upper--;
                break;
            }

            if (nums[idx] > lower) {
                result.add(getRangeStr(lower, nums[idx] - 1));
            }

            lower = nums[idx] + 1;
        }

        if (lower <= upper) {
            result.add(getRangeStr(lower, upper));
        }

        return result;
    }

    private static String getRangeStr(int a, int b) {
        return a == b ? String.valueOf(a) : new StringBuilder().append(a).append("->").append(b).toString();
    }
}

/**
 * This solution will also work if the numbers in nums array are in
 * [Integer.MIN_VALUE, Integer.MAX_VALUE] range but may be out of [lower, upper]
 * range.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of nums array.
 */
class Solution3 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        if (lower > upper) {
            return result;
        }
        if (nums == null || nums.length == 0 || upper < nums[0] || nums[nums.length - 1] < lower) {
            result.add(getRange(lower, upper));
            return result;
        }
        if (lower == upper) {
            int idx = Arrays.binarySearch(nums, lower);
            if (idx < 0) {
                result.add(getRange(lower, upper));
            }
            return result;
        }

        int next = lower;
        for (int n : nums) {
            // Skip the current number if it is less than the next starting number
            if (n < next) {
                continue;
            }

            // Current number is part of the range, increment the missing range starting
            // number
            if (n == next) {
                if (n == upper) {
                    return result;
                }
                next++;
                continue;
            }
            result.add(getRange(next, Math.min(upper, n - 1)));
            if (upper <= n) {
                return result;
            }
            next = n + 1;
        }

        // Create the range for the numbers missing between the last number in nums
        // array and upper limit
        if (next <= upper) {
            result.add(getRange(next, upper));
        }

        return result;
    }

    private String getRange(int a, int b) {
        return a == b ? String.valueOf(a) : new StringBuilder().append(a).append("->").append(b).toString();
    }
}

class Solution4 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            result.add(getRange(lower, upper));
            return result;
        }
        if (lower == upper) {
            int idx = Arrays.binarySearch(nums, lower);
            if (idx < 0) {
                result.add(getRange(lower, upper));
            }
            return result;
        }

        if (lower < nums[0]) {
            result.add(getRange(lower, nums[0] - 1));
        }

        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] + 1 == nums[i]) {
                continue;
            } else {
                result.add(getRange(nums[i - 1] + 1, nums[i] - 1));
            }
        }

        if (nums[len - 1] < upper) {
            result.add(getRange(nums[len - 1] + 1, upper));
        }

        return result;
    }

    private String getRange(int a, int b) {
        return a == b ? String.valueOf(a) : new StringBuilder().append(a).append("->").append(b).toString();
    }
}
