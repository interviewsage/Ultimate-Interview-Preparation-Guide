// LeetCode Question URL: https://leetcode.com/problems/remove-element/
// LeetCode Discuss URL: https://leetcode.com/problems/remove-element/discuss/1529351/Java-or-TC:-O(N)-or-SC:-O(1)-or-Optimized-Two-Pointers-solution-and-FollowUp

/**
 * Two Pointers - when elements to remove are rare.
 *
 * Here whenever we find val, we replace it with num at len-1 and reduce the
 * length of the array by one. Total number of assignment operations required
 * are less.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution1 {
    public int removeElement(int[] nums, int val) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int i = 0;
        while (i < len) {
            if (nums[i] == val) {
                nums[i] = nums[len - 1];
                // Reduce Array length by one
                len--;
            } else {
                i++;
            }
        }
        return len;
    }
}

/**
 * Using 2 pointers. Output array maintains the order of the input array.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution2 {
    public int removeElement(int[] nums, int val) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int insertPos = -1;
        for (int n : nums) {
            if (n != val) {
                nums[++insertPos] = n;
            }
        }
        return insertPos + 1;
    }
}

/**
 * Using 2 pointers. Output array maintains the order of the input array.
 *
 * Here number of assignment operations will be reduced until we find the first
 * occurrence of val.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution3 {
    public int removeElement(int[] nums, int val) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int insertPos = -1;
        int i = 0;
        for (; i < len; i++) {
            if (nums[i] == val) {
                break;
            }
            insertPos++;
        }
        for (; i < len; i++) {
            if (nums[i] != val) {
                nums[++insertPos] = nums[i];
            }
        }
        return insertPos + 1;
    }
}
