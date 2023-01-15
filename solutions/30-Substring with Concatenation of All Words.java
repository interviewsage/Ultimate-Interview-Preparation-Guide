// LeetCode Question URL: https://leetcode.com/problems/substring-with-concatenation-of-all-words/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Sliding Window
 *
 * <pre>
 * Refer:
 * 1.
 * 2. Approach 2: Sliding Window - https://leetcode.com/problems/substring-with-concatenation-of-all-words/solution/
 *
 * Time Complexity:
 * 1. Populate countMap --> O(A)
 * 2. Each Sliding Window = 2 * N/B * SubstringTime = 2 * N/B * B = O(N)
 * 3. Sliding runs wordLen times. Total time taken by sliding window = O(N * B)
 *
 * Thus, total time complexity = O(A + N * B)
 *
 * Space Complexity:
 * 1. countMap -> O(2 * A)
 * 2. wordsFoundMap -> O(A * B + A)
 * 3. Two Substrings in one iteration = O(2 * B)
 *
 * Thus, total space complexity = O(A + B + A*B) = O(A*B)
 *
 * A = Number of Words
 * B = Length of each word
 * N = Length of Input String
 * </pre>
 */
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || words == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<Integer> result = new ArrayList<>();
        int sLen = s.length();
        int numWords = words.length;

        if (sLen == 0 || numWords == 0) {
            return result;
        }

        int wordLen = words[0].length();

        if (wordLen == 0) {
            for (int i = 0; i < sLen; i++) {
                result.add(i);
            }
            return result;
        }
        if (sLen < numWords * wordLen) {
            return result;
        }

        Map<String, Integer> countMap = new HashMap<>();
        for (String w : words) {
            countMap.put(w, countMap.getOrDefault(w, 0) + 1);
        }

        for (int i = 0; i < wordLen; i++) {
            Map<String, Integer> wordsFoundMap = new HashMap<>();
            int start = i;
            int end = i;
            int wordsFound = 0;

            while (end <= sLen - wordLen) {
                String endWord = s.substring(end, end + wordLen);
                end += wordLen;
                Integer endWordCount = countMap.get(endWord);

                if (endWordCount == null) {
                    wordsFoundMap = new HashMap<>();
                    wordsFound = 0;
                    start = end;
                    continue;
                }

                while (endWordCount.equals(wordsFoundMap.get(endWord))) {
                    String startWord = s.substring(start, start + wordLen);
                    Integer count = wordsFoundMap.get(startWord);
                    if (count == 1) {
                        wordsFoundMap.remove(startWord);
                    } else {
                        wordsFoundMap.put(startWord, count - 1);
                    }
                    wordsFound--;
                    start += wordLen;
                }

                wordsFound++;
                wordsFoundMap.put(endWord, wordsFoundMap.getOrDefault(endWord, 0) + 1);

                if (wordsFound == numWords) {
                    result.add(start);
                }
            }
        }

        return result;
    }
}
