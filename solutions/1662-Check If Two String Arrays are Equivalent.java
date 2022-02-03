// LeetCode Question URL: https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/discuss/967572/Java-4-pointers-and-Iterator-Solution-Doing-it-in-O(1)-space-is-the-trick
 * 2) https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/discuss/944525/Java-using-count-pointers-no-String-concatenation-2ms-memory-100
 * </pre>
 *
 * Time Complexity: O(min(N1, N2))
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public class CharIterator implements Iterator<Character> {
        String[] words;
        int wordIdx;
        int charIdx;

        public CharIterator(String[] words) {
            if (words == null) {
                throw new IllegalArgumentException("Input is array is null");
            }
            this.words = words;
        }

        @Override
        public boolean hasNext() {
            if (charIdx == words[wordIdx].length()) {
                if (wordIdx == words.length - 1) {
                    return false;
                }
                wordIdx++;
                charIdx = 0;
            }
            return true;
        }

        @Override
        public Character next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return words[wordIdx].charAt(charIdx++);
        }
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        if (word1 == null || word2 == null || ((word1.length == 0) ^ (word2.length == 0))) {
            return false;
        }

        if (word1.length == 0 && word2.length == 0) {
            return true;
        }

        CharIterator itr1 = new CharIterator(word1);
        CharIterator itr2 = new CharIterator(word2);

        while (itr1.hasNext() && itr2.hasNext()) {
            if (!itr1.next().equals(itr2.next())) {
                return false;
            }
        }

        return !itr1.hasNext() && !itr2.hasNext();
    }
}

/**
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/discuss/967572/Java-4-pointers-and-Iterator-Solution-Doing-it-in-O(1)-space-is-the-trick
 * 2) https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/discuss/944525/Java-using-count-pointers-no-String-concatenation-2ms-memory-100
 * </pre>
 *
 * Time Complexity: O(min(N1, N2))
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        if (word1 == null || word2 == null || ((word1.length == 0) ^ (word2.length == 0))) {
            return false;
        }

        int len1 = word1.length;
        int len2 = word2.length;

        if (len1 == 0 && len2 == 0) {
            return true;
        }

        int w1Idx = 0;
        int w1CIdx = 0;
        int w2Idx = 0;
        int w2CIdx = 0;

        while (w1Idx < len1 && w2Idx < len2) {
            String w1 = word1[w1Idx];
            int l1 = w1.length();
            String w2 = word2[w2Idx];
            int l2 = w2.length();

            while (w1CIdx < l1 && w2CIdx < l2) {
                if (w1.charAt(w1CIdx) != w2.charAt(w2CIdx)) {
                    return false;
                }
                w1CIdx++;
                w2CIdx++;
            }

            if (w1CIdx == l1) {
                w1Idx++;
                w1CIdx = 0;
            }
            if (w2CIdx == l2) {
                w2Idx++;
                w2CIdx = 0;
            }
        }

        return w1Idx == len1 && w2Idx == len2;
    }
}
