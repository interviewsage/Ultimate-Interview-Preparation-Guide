// LeetCode Question URL: https://leetcode.com/problems/remove-element/
// LeetCode Discuss URL: https://leetcode.com/problems/remove-element/discuss/1529351/Java-or-TC:-O(N)-or-SC:-O(1)-or-Optimized-Two-Pointers-solution-and-FollowUp

/**
 * Two Pointers - when elements to remove are rare.
 *
 * Here whenever we find val, we replace it with num at len-1 and reduce the
 * length of the array by one. Total number of assignment operations required
 * are less.
 *
 * In this approach, the number of assignment operations is equal to the number
 * of elements to remove. So it is more efficient if elements to remove are
 * rare.
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
            throw new IllegalArgumentException("Input is null");
        }

        int len = nums.length;
        int i = 0;
        while (i < len) {
            if (nums[i] == val) {
                nums[i] = nums[--len];
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
 * Here number of assignment operations will be reduced until we find the first
 * occurrence of val.
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
            throw new IllegalArgumentException("Input is null");
        }

        int insertPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                if (insertPos != i) {
                    nums[insertPos] = nums[i];
                }
                insertPos++;
            }
        }

        return insertPos;
    }
}
