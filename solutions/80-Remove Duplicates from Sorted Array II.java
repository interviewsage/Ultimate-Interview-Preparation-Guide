// LeetCode Question URL: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/discuss/1529357/Java-or-TC:-O(N)-or-SC:-O(1)-or-Optimized-Two-Pointers-solution-and-FollowUp

/**
 * In place, one pass solution using 2 pointers
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len <= 2) {
            return len;
        }

        int insertPos = 1;
        for (int i = 2; i < len; i++) {
            if (nums[i] != nums[insertPos - 1]) {
                nums[++insertPos] = nums[i];
            }
        }

        return insertPos + 1;
    }
}

/**
 * Follow-Up: Each unique element appears at most K times.
 *
 * Refer:
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/discuss/27976/3-6-easy-lines-C++-Java-Python-Ruby/27034
 *
 * In place, one pass solution using 2 pointers
 *
 * Time Complexity: O(N-K)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution2 {
    public int removeDuplicates(int[] nums) {
        return removeDuplicatesMoreThanK(nums, 2);
    }

    public int removeDuplicatesMoreThanK(int[] nums, int k) {
        if (nums == null || k < 0) {
            throw new IllegalArgumentException("Invalid Input");
        }
        if (k == 0) {
            return 0;
        }

        int len = nums.length;
        if (len <= k) {
            return len;
        }

        int insertPos = k - 1;
        for (int i = k; i < len; i++) {
            if (nums[i] != nums[insertPos - (k - 1)]) {
                nums[++insertPos] = nums[i];
            }
        }

        return insertPos + 1;
    }
}
