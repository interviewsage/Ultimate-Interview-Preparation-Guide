// LeetCode Question URL: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
// LeetCode Discuss URL: https://leetcode.com/problems/remove-duplicates-from-sorted-array/discuss/1529341/Java-or-TC:-O(N)-or-SC:-O(1)-or-Optimized-Two-Pointers-solution

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution1 {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        if (nums[0] == nums[len - 1]) {
            return 1;
        }

        int insertPos = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] != nums[insertPos - 1]) {
                // Only update if insertPos & i are not same.
                if (insertPos != i) {
                    nums[insertPos] = nums[i];
                }
                insertPos++;
            }
        }

        return insertPos;
    }
}

/**
 * Using 2 pointers.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution2 {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        int insertPos = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] != nums[insertPos]) {
                nums[++insertPos] = nums[i];
            }
        }

        return insertPos + 1;
    }
}

/**
 * Using 2 pointers.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution3 {
    public int removeDuplicates(int[] nums) throws IllegalArgumentException {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }
        if (nums.length < 2) {
            return nums.length;
        }

        int pos = 0;
        for (int n : nums) {
            if (n != nums[pos]) {
                pos++;
                nums[pos] = n;
            }
        }

        return pos + 1;
    }
}
