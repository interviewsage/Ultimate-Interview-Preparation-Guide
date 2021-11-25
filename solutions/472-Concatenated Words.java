// LeetCode Question URL: https://leetcode.com/problems/concatenated-words/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Dynamic Programming (Sorting not required)
 *
 * Refer:
 * https://leetcode.com/problems/concatenated-words/discuss/95652/Java-DP-Solution/224870
 *
 * Extend solution for 139-Word Break
 *
 * If you do know one optimized solution for above question is using DP, this
 * problem is just one more step further. We iterate through each word and see
 * if it can be formed by using other words.
 *
 * A word can only be formed by words shorter than it. So we can first sort the
 * input by length of each word, and only try to form one word by using words in
 * front of it.
 *
 * To check for correctness, workout this example: ["f", "a", "bc", "d", "abc",
 * "abcd", "abcde", "abcdef"]
 *
 * Time Complexity: O(N + N * L^3)
 *
 * Space Complexity: O(N * L + L)
 *
 * N = Number of words in input array. L = Average length of each word.
 */
class Solution1 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length <= 1) {
            return result;
        }

        Set<String> wordSet = new HashSet<>(Arrays.asList(words));

        for (String word : words) {
            wordSet.remove(word);
            if (canBreakWord(word, wordSet)) {
                result.add(word);
            } else {
                wordSet.add(word);
            }
        }

        return result;
    }

    private boolean canBreakWord(String word, Set<String> wordSet) {
        int len = word.length();
        if (len == 0) {
            return false;
        }

        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && wordSet.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}

/**
 * Dynamic Programming (Sorting required)
 *
 * Refer:
 * https://leetcode.com/problems/concatenated-words/discuss/95652/Java-DP-Solution
 *
 * Extend solution for 139-Word Break
 *
 * If you do know one optimized solution for above question is using DP, this
 * problem is just one more step further. We iterate through each word and see
 * if it can be formed by using other words.
 *
 * A word can only be formed by words shorter than it. So we can first sort the
 * input by length of each word, and only try to form one word by using words in
 * front of it.
 *
 * Time Complexity: O(N log N + N * L^3)
 *
 * Space Complexity: O(N * L + L)
 *
 * N = Number of words in input array. L = Average length of each word.
 */
class Solution2 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length <= 1) {
            return result;
        }

        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        HashSet<String> prevWords = new HashSet<>();

        for (String w : words) {
            if (isConcatenated(w, prevWords)) {
                result.add(w);
            }
            prevWords.add(w);
        }

        return result;
    }

    private boolean isConcatenated(String word, HashSet<String> wordSet) {
        if (wordSet.size() == 0) {
            return false;
        }

        int len = word.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }
}

/**
 * Using Trie. (DO NOT USE THIS SOLUTION IN INTERVIEW)
 *
 * The time complexity of this solution is O(N * 2^L * L) where L is the average
 * length of words as it still takes O(L) is check whether a word exists. It can
 * be optimized through DP or memoization to O(N *L^3). Even with DP, it's still
 * not better than the wordbreak approach with hashset which also has O(N *L^3).
 * The only thing that Trie is better is it can stop early if a word doesn't
 * exist.
 *
 * <pre>
 * Time Complexity:
 * T(L) = T(L-1) + T(L-2) + ... + T(1) + O(L)
 * T(L-1) = T(L-2) + T(L-3) + ... + T(1) + O(L-1)
 * T(L-2) = T(L-3) + T(L-4) + ... + T(1) + O(L-2)
 * T(L-3) = T(L-4) + T(L-5) + ... + T(1) + O(L-3)
 * T(L-4) = T(L-5) + T(L-6) + ... + T(1) + O(L-4)
 * ...
 * T(2) = T(1) + O(2)
 * T(1) = O(1)
 *
 * T(L) = L + 2^0 * (L-1) + 2^1 * (L-2) + 2^2 * (L-3) + .... + 2^(L-1) * 2 + 2^L
 *      = L * (1 + 2^0 + 2^1 + 2^2 + ... + 2^L) - (1 + 2 + 3 + ... + (L-1))
 *      = L * 2^(L+1) - L*(L-1)/2
 * T(L) ~ O(L * O^L)
 * </pre>
 *
 * Space Complexity: O(N + L)
 *
 * Where, N = Number of words in input array. L = Average length of each word.
 */
class Solution3 {

    public class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;

        TrieNode() {
            children = new HashMap<>();
        }
    }

    public void addWordToTrie(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
        }
        cur.isEnd = true;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length <= 1) {
            return result;
        }

        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        TrieNode root = new TrieNode();
        addWordToTrie(root, words[0]);

        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            if (canBreakWord(root, word, 0)) {
                result.add(word);
            } else {
                addWordToTrie(root, word);
            }
        }

        return result;
    }

    private boolean canBreakWord(TrieNode root, String word, int start) {
        TrieNode cur = root;
        int len = word.length();
        for (int i = start; i < len; i++) {
            char c = word.charAt(i);
            cur = cur.children.get(c);
            if (cur == null) {
                return false;
            }
            if (cur.isEnd && (i == len - 1 || canBreakWord(root, word, i + 1))) {
                return true;
            }
        }
        return false;
    }
}
