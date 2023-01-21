// LeetCode Question URL: https://leetcode.com/problems/replace-words/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Trie + Optimization
 *
 * Here, in case of "a", "aa", "aaa" in the dictionary, we will only add "a" and
 * can break after isEnd if found while building the trie.
 *
 * <pre>
 * Time Complexity:
 * Build Trie: O(N * AVG_WL)
 * Search: O(SL)
 * Generate Result: O(SL)
 *
 * Total Time Complexity: O(N * AVG_WL + SL)
 *
 * Space Complexity:
 * Trie: O(N * AVG_WL)
 * StringBuilder: O(SL)
 *
 * Total Space Complexity: O(N * AVG_WL + SL)
 *
 * N = Number of words
 * AVG_WL = Average word len
 * SL = Sentence length
 * </pre>
 */
class Solution {

    public class TrieNode {
        Map<Character, TrieNode> map;
        boolean isEnd;

        public TrieNode() {
            map = new HashMap<>();
            isEnd = false;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Input sentence is null");
        }

        int sLen = sentence.length();
        if (dictionary == null || dictionary.size() == 0 || sLen <= 1) {
            return sentence;
        }

        TrieNode root = new TrieNode();

        for (String word : dictionary) {
            if (word == null || word.length() == 0 || word.length() > sLen) {
                continue;
            }

            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode next = cur.map.get(c);
                if (next == null) {
                    next = new TrieNode();
                    cur.map.put(c, next);
                }
                cur = next;
                if (cur.isEnd) {
                    break;
                }
            }
            cur.isEnd = true;
        }

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < sLen) {
            if (sentence.charAt(idx) == ' ') {
                sb.append(' ');
                idx++;
                continue;
            }

            boolean matchFound = false;
            TrieNode cur = root;
            while (idx < sLen && sentence.charAt(idx) != ' ') {
                char c = sentence.charAt(idx++);
                sb.append(c);
                cur = cur.map.get(c);
                if (cur == null) {
                    break;
                }
                if (cur.isEnd) {
                    matchFound = true;
                    break;
                }
            }

            while (idx < sLen && sentence.charAt(idx) != ' ') {
                if (!matchFound) {
                    sb.append(sentence.charAt(idx));
                }
                idx++;
            }
        }

        return sb.toString();
    }
}

/**
 * Trie
 *
 * Refer:
 * https://leetcode.com/problems/replace-words/discuss/105767/Java-SimpleClassical-Trie-questionsolution-(Beat-96)
 *
 * Time Complexity: O(M + N)
 *
 * Space Complexity: O(M + N). O(M) is for Trie. O(N) is for words[] array.
 *
 * M = Number of characters in dict. N = Number of characters in sentence.
 */
class Solution2 {

    private class TrieNode {
        HashMap<Character, TrieNode> map;
        boolean isWord;

        TrieNode() {
            map = new HashMap<>();
            isWord = false;
        }
    }

    public String replaceWords(List<String> dict, String sentence) {
        if (dict == null || dict.size() == 0 || sentence == null || sentence.length() <= 1) {
            return sentence;
        }

        TrieNode root = new TrieNode();

        for (String w : dict) {
            addToTrie(w, root);
        }

        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String w : words) {
            getShortestRoot(w, root, sb);
            sb.append(" ");
        }

        return sb.substring(0, sb.length() - 1);
    }

    private void addToTrie(String word, TrieNode root) {
        if (word == null || word.length() == 0 || root == null) {
            return;
        }

        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.map.containsKey(c)) {
                cur.map.put(c, new TrieNode());
            }
            cur = cur.map.get(c);
        }
        cur.isWord = true;
    }

    private void getShortestRoot(String word, TrieNode root, StringBuilder sb) {
        if (word == null || word.length() == 0 || root == null) {
            return;
        }

        TrieNode cur = root;
        int initialSbLen = sb.length();

        for (char c : word.toCharArray()) {
            if (!cur.map.containsKey(c)) {
                sb.setLength(initialSbLen);
                sb.append(word);
                return;
            }
            sb.append(c);
            cur = cur.map.get(c);
            if (cur.isWord) {
                break;
            }
        }
    }
}
