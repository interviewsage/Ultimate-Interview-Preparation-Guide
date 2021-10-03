// LeetCode Question URL: https://leetcode.com/problems/contains-duplicate/
// LeetCode Discuss URL: https://leetcode.com/problems/contains-duplicate/discuss/1500880/Java-or-TC:-O(N)-or-SC:-O(N)-or-Clean-and-concise-solution-using-HashSet

import java.util.*;

/**
 * Using HashSet
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 */
class Solution1 {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        if (nums.length <= 1) {
            return false;
        }

        HashSet<Integer> found = new HashSet<>();
        for (int n : nums) {
            if (!found.add(n)) {
                return true;
            }
        }

        return false;
    }
}

/**
 * Sort the array
 *
 * Time Complexity: O(N log N)
 *
 * Space Complexity: O(Space used by sorting algorithm)
 */
class Solution2 {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }

        return false;
    }
}
