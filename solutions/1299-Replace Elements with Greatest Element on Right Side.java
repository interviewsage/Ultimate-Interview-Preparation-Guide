// LeetCode Question URL: https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(N)
 *
 * Space Complexity O(1)
 */
class Solution {
    public int[] replaceElements(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = arr.length;
        if (len == 0) {
            return arr;
        }

        int maxOnRight = arr[len - 1];
        arr[len - 1] = -1;
        for (int i = len - 2; i >= 0; i--) {
            int cur = arr[i];
            arr[i] = maxOnRight;
            maxOnRight = Math.max(maxOnRight, cur);
        }

        return arr;
    }
}
