// LeetCode Question URL: https://leetcode.com/problems/valid-number/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/valid-number/discuss/23738/Clear-Java-solution-with-ifs
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        boolean signFound = false;
        boolean dotFound = false;
        boolean digitFound = false;
        boolean expFound = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-' || c == '+') {
                if (signFound || digitFound || dotFound) {
                    return false;
                }
                signFound = true;
            } else if (Character.isDigit(c)) {
                digitFound = true;
            } else if (c == '.') {
                if (expFound || dotFound) {
                    return false;
                }
                dotFound = true;
            } else if (c == 'e' || c == 'E') {
                if (expFound || !digitFound) {
                    return false;
                }
                expFound = true;
                digitFound = false;
                signFound = false;
                dotFound = false;
            } else {
                return false;
            }
        }

        return digitFound;
    }
}

/**
 * Refer:
 * https://leetcode.com/problems/valid-number/discuss/23738/Clear-Java-solution-with-ifs
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        boolean numSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '+' || c == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (Character.isDigit(c)) {
                numSeen = true;
            } else if (c == '.') {
                if (dotSeen || eSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (c == 'e' || c == 'E') {
                if (eSeen || !numSeen) {
                    return false;
                }
                numSeen = false;
                eSeen = true;
            } else {
                return false;
            }
        }

        return numSeen;
    }
}
