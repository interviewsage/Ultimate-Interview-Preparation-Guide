// LeetCode Question URL: https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(N)
 *
 * Space Complexity O(1)
 */
class Solution {
    public int[] replaceElements(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int max = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int preMax = max;
            max = Math.max(max, arr[i]);
            arr[i] = preMax;
        }

        return arr;
    }
}
