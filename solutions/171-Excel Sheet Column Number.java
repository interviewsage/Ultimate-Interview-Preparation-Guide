// LeetCode Question URL: https://leetcode.com/problems/excel-sheet-column-number/
// LeetCode Discuss URL:

/**
 * Iterate over the input string and then keep updating the result.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input columnTitle
 */
class Solution {
    public int titleToNumber(String columnTitle) {
        if (columnTitle == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            result = result * 26 + (columnTitle.charAt(i) - 'A' + 1);
        }

        return result;
    }
}
