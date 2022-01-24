// LeetCode Question URL: https://leetcode.com/problems/third-maximum-number/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(N). Where N is the number of elements.
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }

        Integer firstMax = null;
        Integer secondMax = null;
        Integer thirdMax = null;

        for (int n : nums) {
            if (firstMax == null || n >= firstMax) {
                if (firstMax != null && firstMax == n) {
                    continue;
                }
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = n;
            } else if (secondMax == null || n >= secondMax) {
                if (secondMax != null && secondMax == n) {
                    continue;
                }
                thirdMax = secondMax;
                secondMax = n;
            } else if (thirdMax == null || n > thirdMax) {
                thirdMax = n;
            }
        }

        return thirdMax != null ? thirdMax : firstMax;
    }
}

/**
 * Time Complexity: O(N). Where N is the number of elements.
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Invalid Input");
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }

        Integer firstMax = null;
        Integer secondMax = null;
        Integer thirdMax = null;

        for (int n : nums) {
            if (firstMax == null || n > firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = n;
            } else if (n != firstMax && (secondMax == null || n > secondMax)) {
                thirdMax = secondMax;
                secondMax = n;
            } else if (n != firstMax && n != secondMax && (thirdMax == null || n > thirdMax)) {
                thirdMax = n;
            }
        }

        return thirdMax == null ? firstMax : thirdMax;
    }
}

/**
 * Time Complexity: O(N). Where N is the number of elements.
 *
 * Space Complexity: O(1)
 */
class Solution3 {
    public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }

        Integer firstMax = null;
        Integer secondMax = null;
        Integer thirdMax = null;

        for (Integer n : nums) {
            if (n.equals(firstMax) || n.equals(secondMax) || n.equals(thirdMax)) {
                continue;
            }
            if (firstMax == null || n > firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = n;
            } else if (secondMax == null || n > secondMax) {
                thirdMax = secondMax;
                secondMax = n;
            } else if (thirdMax == null || n > thirdMax) {
                thirdMax = n;
            }
        }

        return thirdMax != null ? thirdMax : firstMax;
    }
}

/**
 * Using 3 variables. Using long variables to handle Integer.MIN_VALUE.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution4 {
    public int thirdMax(int[] nums) throws IllegalArgumentException {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Invalid or Empty input nums array");
        }
        if (nums.length == 1) {
            return nums[0];
        }

        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;

        for (int n : nums) {
            if (n == max1 || n == max2 || n == max3) {
                continue;
            }
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }
        }

        return (int) (max3 == Long.MIN_VALUE ? max1 : max3);
    }
}

/**
 * Using TreeSet
 *
 * Time Complexity: O(N log N)
 *
 * Space Complexity: O(N)
 */
class Solution5 {
    public int thirdMax(int[] nums) throws IllegalArgumentException {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Invalid or Empty input nums array");
        }
        if (nums.length == 1) {
            return nums[0];
        }

        TreeSet<Integer> set = new TreeSet<>();
        for (int n : nums) {
            set.add(n);
            if (set.size() > 3) {
                set.pollFirst();
            }
        }

        return set.size() == 3 ? set.first() : set.last();
    }
}
