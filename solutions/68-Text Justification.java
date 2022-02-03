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

        int len = words.length;
        int wIdx = 0;
        while (wIdx < len) {
            int startIdx = wIdx++;
            int wordsLen = words[startIdx].length();
            while (wIdx < len && (wordsLen + words[wIdx].length() + 1) <= maxWidth) {
                wordsLen += words[wIdx].length() + 1;
                wIdx++;
            }

            int spaceCount = wIdx - startIdx - 1;
            int evenSpaces = 1;
            int extraSpaces = 0;
            if (spaceCount != 0 && wIdx != len) {
                evenSpaces = 1 + (maxWidth - wordsLen) / spaceCount;
                extraSpaces = (maxWidth - wordsLen) % spaceCount;
            }

            StringBuilder sb = new StringBuilder(maxWidth);
            sb.append(words[startIdx]);
            for (int i = startIdx + 1; i < wIdx; i++) {
                for (int j = 0; j < evenSpaces; j++) {
                    sb.append(' ');
                }
                if (extraSpaces > 0) {
                    sb.append(' ');
                    extraSpaces--;
                }
                sb.append(words[i]);
            }
            while (sb.length() < maxWidth) {
                sb.append(' ');
            }

            result.add(sb.toString());
        }

        return result;
    }
}
