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
        if (nums == null || k < 0) {
            throw new IllegalArgumentException("Invalid input");
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

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int t = nums[i];
            nums[i++] = nums[j];
            nums[j--] = t;
        }
    }
}

/**
 * One Pass Solution - Using Cyclic Replacements
 *
 * <pre>
 * Refer for proof of this solution
 * 1) https://leetcode.com/problems/rotate-array/solution/
 * 2) https://leetcode.com/problems/rotate-array/solution/356363
 * 3) https://leetcode.com/problems/rotate-array/discuss/54277/Summary-of-C++-solutions/497566
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
        if (nums == null || k < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int len = nums.length;
        if (len <= 1) {
            return;
        }

        k %= len;
        if (k == 0) {
            return;
        }

        int start = 0;
        int count = 0;
        // We need this while loop when len % k == 0. Here we will find a cycle and thus
        // we need to move the start to next idx to complete the whole array.
        while (count < len) {
            int curIdx = start;
            // It's not about swapping. We need to keep moving the number to nextIdx.
            int toBeMoved = nums[curIdx];

            do {
                int nextIdx = (curIdx + k) % len;
                int nextNum = nums[nextIdx];
                nums[nextIdx] = toBeMoved;
                toBeMoved = nextNum;
                curIdx = nextIdx;
                count++;
            } while (curIdx != start);

            start++;
        }
    }
}
