// LeetCode Question URL: https://leetcode.com/problems/excel-sheet-column-title/
// LeetCode Discuss URL:

/**
 * Keep dividing the number by 26.
 *
 * Make sure to subtract 1 before dividing.
 *
 * Time Complexity: O(log26 N)
 *
 * Space Complexity: O(log26 N)
 *
 * N = Input number n.
 */
class Solution {
    public String convertToTitle(int columnNumber) {
        if (columnNumber < 0) {
            throw new IllegalArgumentException("Input number is invalid");
        }
        if (columnNumber == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }

        return sb.reverse().toString();
    }
}
