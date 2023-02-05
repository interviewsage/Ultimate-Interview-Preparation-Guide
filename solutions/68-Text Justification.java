// LeetCode Question URL: https://leetcode.com/problems/text-justification/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Figure out how many words can fit in a line. Then calculate the spaces
 * required in the gaps.
 *
 * <pre>
 * Time Complexity: O(N + 2*maxWidth*numLines).
 * numLines is worst case can be O(N).
 * Thus worst case complexity = O(N * maxWidth)
 * </pre>
 *
 * Space Complexity: O(maxWidth). String Builder size.
 *
 * N = Number of words.
 */
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        if (words == null || maxWidth <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<String> result = new ArrayList<>();
        int numWords = words.length;
        int wIdx = 0;

        while (wIdx < numWords) {
            int startIdx = wIdx++;
            int lineLen = words[startIdx].length();
            while (wIdx < numWords && (lineLen + 1 + words[wIdx].length()) <= maxWidth) {
                lineLen += 1 + words[wIdx++].length();
            }

            int spaceCount = wIdx - startIdx - 1;
            int evenSpaces = 1;
            int extraSpaces = 0;
            if (wIdx != numWords && spaceCount != 0) {
                evenSpaces = 1 + (maxWidth - lineLen) / spaceCount;
                extraSpaces = (maxWidth - lineLen) % spaceCount;
            }

            StringBuilder line = new StringBuilder(maxWidth);
            line.append(words[startIdx]);
            for (int i = startIdx + 1; i < wIdx; i++) {
                for (int j = 0; j < evenSpaces; j++) {
                    line.append(' ');
                }
                if (extraSpaces > 0) {
                    line.append(' ');
                    extraSpaces--;
                }
                line.append(words[i]);
            }
            while (line.length() < maxWidth) {
                line.append(' ');
            }

            result.add(line.toString());
        }

        return result;
    }
}
