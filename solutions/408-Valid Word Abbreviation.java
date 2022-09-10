// LeetCode URL: https://leetcode.com/problems/valid-word-abbreviation/

/**
 * <pre>
 * Time Complexity:
 *       O(1) --> abbrLen > wordLen
 * O(abbrLen) --> abbrLen <= wordLen
 * </pre>
 *
 * Space Complexity: O(1)
 */
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        if (word == null || abbr == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int wordLen = word.length();
        int abbrLen = abbr.length();

        if (abbrLen > wordLen) {
            return false;
        }
        if (wordLen == 0) {
            return true;
        }

        int i = 0;
        int j = 0;

        while (i < wordLen && j < abbrLen) {
            char curAbChar = abbr.charAt(j++);
            if (Character.isDigit(curAbChar)) {
                if (curAbChar == '0') {
                    return false;
                }
                int num = curAbChar - '0';
                while (j < abbrLen && Character.isDigit(abbr.charAt(j))) {
                    num = num * 10 + abbr.charAt(j++) - '0';
                    if (i + num > wordLen) {
                        return false;
                    }
                }
                i += num;
            } else {
                if (curAbChar != word.charAt(i)) {
                    return false;
                }
                i++;
            }
        }

        return i == wordLen && j == abbrLen;
    }
}
