// LeetCode Question URL: https://leetcode.com/problems/rotate-array/
// LeetCode Discuss URL:

/**
 * Two Pass Solution
 *
 * Rotate whole array and then rotate 0 -> k-1 and k -> len-1.
 *
 * Time Complexity: O(N/2 + N/2) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public void rotate(int[] nums, int k) {
        if (k < 0 || nums == null) {
            throw new IllegalArgumentException("input is invalid");
        }

        int len = nums.length;
        if (len <= 1) {
            return;
        }

        k %= len;
        if (k == 0) {
            return;
        }

        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}

/**
 * One Pass Solution - Using Cyclic Replacements
 *
 * <pre>
 * Refer for proof of this solution
 * 1) https://leetcode.com/problems/rotate-array/solution/356363
 * 2) https://leetcode.com/problems/rotate-array/discuss/54277/Summary-of-C++-solutions/497566
 * </pre>
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution2 {
    public void rotate(int[] nums, int k) {
        if (k < 0 || nums == null) {
            throw new IllegalArgumentException("input is invalid");
        }

        int len = nums.length;
        if (len <= 1) {
            return;
        }

        k %= len;
        if (k == 0) {
            return;
        }

        int count = 0;
        int start = 0;
        while (count < len) {
            int curIdx = start;
            int toBeMoved = nums[start];

            do {
                int nextIdx = (curIdx + k) % len;
                int temp = nums[nextIdx];
                nums[nextIdx] = toBeMoved;
                toBeMoved = temp;
                curIdx = nextIdx;
                count++;
            } while (start != curIdx);

            start++;
        }
    }
}
