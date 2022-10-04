// LeetCode Question URL: https://leetcode.com/problems/longest-absolute-file-path/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(N) -> Each character is visited once
 *
 * <pre>
 * Space Complexity: O(sqrt(N)) --> This only grow up to max number of levels.
 *
 * a\n\tb\n\t\tc\n\t\t\td
 * 1 + 3 + 4 + 5 + 6 + ... + x = N
 *
 * ==> x * (x+1)/2 - 2 = N
 * ==> x^2 + x - (2N+4) = 0
 *
 * Roots of a Quadratic Equation --> https://www.cuemath.com/algebra/roots-of-quadratic-equation/
 *
 * x = O(sqrt(N))
 * </pre>
 *
 * N = Length of input string.
 */
class Solution1 {
    public int lengthLongestPath(String input) {
        if (input == null || input.length() < 3) {
            return 0;
        }

        List<Integer> levels = new ArrayList<>();
        levels.add(0);
        int maxLen = 0;
        int len = input.length();

        int i = 0;
        while (i < len) {
            int curLevel = 1;
            while (i < len && input.charAt(i) == '\t') {
                curLevel++;
                i++;
            }

            // If we have to check for invalid directory structure, store prevLevel.
            // If curLevel is greater than prevLevel+1, the input is invalid.

            int start = i;
            boolean isFile = false;
            while (i < len && input.charAt(i) != '\n') {
                if (!isFile && input.charAt(i) == '.') {
                    isFile = true;
                }
                i++;
            }

            int curLen = levels.get(curLevel - 1) + (i - start);
            if (isFile) {
                maxLen = Math.max(maxLen, curLen);
            } else {
                curLen++; // for '/'
                if (levels.size() == curLevel) {
                    levels.add(curLen);
                } else {
                    levels.set(curLevel, curLen);
                }
            }

            i++; // skipping \n
        }

        return maxLen;
    }
}

class Solution2 {
    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }

        String[] parts = input.split("\n");
        int[] levels = new int[parts.length + 1];
        int result = 0;

        for (String p : parts) {
            /*
             * numOfTabs is the number of "\t", numOfTabs = 0 when "\t" is not found,
             * because s.lastIndexOf("\t") returns -1. So normally, the first parent "dir"
             * have numOfTabs 0.
             */
            int noOfTabs = p.lastIndexOf("\t") + 1;

            /*
             * Level is defined as numOfTabs + 1. For example, in
             * "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext", dir is level 1, subdir1 and
             * subdir2 are level 2, file.ext is level3
             */
            int level = noOfTabs + 1;

            /*
             * Calculate the path length of current directory or file that we are currently
             * looking at.
             *
             * Current path lenght = path length of parent + current file/dir len - number
             * of tabs + 1 for '/'
             */
            levels[level] = levels[level - 1] + p.length() - noOfTabs + 1;

            if (p.contains(".")) {
                result = Math.max(result, levels[level] - 1);
            }
        }

        return result;
    }
}
