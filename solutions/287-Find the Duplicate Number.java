// LeetCode Question URL: https://leetcode.com/problems/find-the-duplicate-number/
// LeetCode Discuss URL:

/**
 * Similar to LinkedList Cycle II
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/find-the-duplicate-number/solution/
 * 2. https://leetcode.com/problems/find-the-duplicate-number/discuss/72846/My-easy-understood-solution-with-O(n)-time-and-O(1)-space-without-modifying-the-array.-With-clear-explanation./75491
 * </pre>
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Invalid Input");
        }

        int fast = nums[0];
        int slow = nums[0];
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (slow != fast);

        fast = nums[0];
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }

        return fast;
    }
}
