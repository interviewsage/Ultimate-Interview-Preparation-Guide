// LeetCode Question URL: https://leetcode.com/problems/valid-parenthesis-string/
// LeetCode Discuss URL:

/**
 * Count the open brackets. In case of * there will be 3 options +1,0,-1. We can
 * notice that its a continuous increasing sequence. Thus we need to only
 * maintain low and high of the sequence.
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/valid-parenthesis-string/discuss/107577/Short-Java-O(n)-time-O(1)-space-one-pass
 * 2) https://leetcode.com/problems/valid-parenthesis-string/discuss/107577/Short-Java-O(n)-time-O(1)-space-one-pass/109718
 * 3) Approach #3: Greedy [Accepted] (in Solutions tab): https://leetcode.com/problems/valid-parenthesis-string/solution/
 * </pre>
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input string.
 */
class Solution {
    public boolean checkValidString(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int countMax = 0;
        int countMin = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                countMax++;
                countMin++;
            } else if (c == ')') {
                if (countMax == 0) {
                    return false;
                }
                countMax--;
                if (countMin > 0) {
                    countMin--;
                }
            } else {
                countMax++;
                if (countMin > 0) {
                    countMin--;
                }
            }
        }

        return countMin == 0;
    }
}
