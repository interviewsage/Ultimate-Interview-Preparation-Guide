// LeetCode Question URL: https://leetcode.com/problems/excel-sheet-column-title/
// LeetCode Discuss URL:

/**
 * Keep dividing the number by 26.
 *
 * Make sure to subtract 1 before dividing. This is to ensure that we can easily
 * add to 'A' to get the desired character.
 *
 * Time Complexity: O(3 * log26 N)
 *
 * Space Complexity: O(log26 N)
 *
 * N = Input number n.
 */
class Solution {
    public String convertToTitle(int columnNumber) {
        if (columnNumber <= 0) {
            throw new IllegalArgumentException("Input columnNumber is invalid");
        }

        StringBuilder colTitle = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            colTitle.append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }

        return colTitle.reverse().toString();
    }
}
