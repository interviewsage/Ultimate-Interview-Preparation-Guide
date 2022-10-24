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
        int wIdx;
        int cIdx;

        public CharIterator(String[] words) {
            if (words == null) {
                throw new IllegalArgumentException("Input words array is null");
            }
            this.words = words;
            this.wIdx = 0;
            this.cIdx = 0;
        }

        @Override
        public Character next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No next character found");
            }
            return words[wIdx].charAt(cIdx++);
        }

        @Override
        public boolean hasNext() {
            while (wIdx < words.length) {
                if (cIdx < words[wIdx].length()) {
                    return true;
                }
                cIdx = 0;
                wIdx++;
            }
            return false;
        }
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        if (word1 == null || word2 == null) {
            throw new IllegalArgumentException("Input is null");
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
 *
 * N1 = Number of chars in word1 array. N2 = Number of chars in word2 array.
 */
class Solution2 {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        if (word1 == null || word2 == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int numWords1 = word1.length;
        int numWords2 = word2.length;
        int w1 = 0;
        int w2 = 0;
        int c1 = 0;
        int c2 = 0;

        while (w1 < numWords1 && w2 < numWords2) {
            int l1 = word1[w1].length();
            int l2 = word2[w2].length();

            while (c1 < l1 && c2 < l2) {
                if (word1[w1].charAt(c1++) != word2[w2].charAt(c2++)) {
                    return false;
                }
            }

            if (c1 == l1) {
                w1++;
                c1 = 0;
            }
            if (c2 == l2) {
                w2++;
                c2 = 0;
            }
        }

        return w1 == numWords1 && w2 == numWords2;
    }
}
