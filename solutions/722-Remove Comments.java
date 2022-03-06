// LeetCode Question URL: https://leetcode.com/problems/remove-comments/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Process each char by char.
 *
 * Time Complexity: O(N * L) = O(TC)
 *
 * Space Complexity: O(N * L) = O(TC). In worst case string builder can have
 * almost characters at the same time.
 *
 * N = Number of line. L = Average length of the line. TC = Total number of
 * characters in all lines.
 */
class Solution {
    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();

        if (source == null || source.length == 0) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        boolean isInMultiLineCommentBlock = false;

        for (String line : source) {
            int len = line.length();
            int i = 0;

            while (i < len) {
                if (isInMultiLineCommentBlock) {
                    if (i < len - 1 && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                        isInMultiLineCommentBlock = false;
                        i += 2;
                    } else {
                        i++;
                    }
                } else {
                    if (i < len - 1 && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                        break;
                    } else if (i < len - 1 && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                        isInMultiLineCommentBlock = true;
                        i += 2;
                    } else {
                        sb.append(line.charAt(i++));
                    }
                }
            }

            if (!isInMultiLineCommentBlock && sb.length() > 0) {
                result.add(sb.toString());
                sb.setLength(0);
            }
        }

        return result;
    }
}
