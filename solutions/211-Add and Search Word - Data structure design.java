// LeetCode Question URL: https://leetcode.com/problems/add-and-search-word-data-structure-design/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Trie
 *
 * <pre>
 * Time Complexity:
 * WordDictionary() constructor --> O(1)
 * addWord() --> O(M)
 * search() --> Case 1: Input word length < minWordLen OR > maxWordLen --> O(1)
 *          --> Case 2: Input word does not contain '.' --> O(M)
 *          --> Case 3: General Case --> O(min(TotalCharsInTrie, (AllUniqueCharsInTrie)^M))
 *
 * Explanation for (AllUniqueCharsInTrie)^M OR 26^M:
 * Each Trie Node can have upto 26 chars. Thus from each trie node we can 26 recursions.
 * Thus at idx 0 --> 26
 * Thus at idx 1 --> 26^2
 * Thus at idx 2 --> 26^3
 * .
 * .
 * .
 * Thus at idx M-1 --> 26^M
 *
 * This is Geometric Progression. Sum = a*(r^n - 1)/(r-1)
 *                                    = 26 * (26^M - 1) / (26 - 1)
 *                                    = O(26^M)
 *
 * Space Complexity: O(TotalCharsInTrie)
 * </pre>
 *
 * M = Input word length.
 * AllUniqueCharsInTrie = Character Set = 26
 */
class WordDictionary {

    public class TrieNode {
        Map<Character, TrieNode> map;
        boolean isEnd;

        public TrieNode() {
            map = new HashMap<>();
            isEnd = false;
        }
    }

    TrieNode root;
    int minLen;
    int maxLen;

    public WordDictionary() {
        root = new TrieNode();
        minLen = Integer.MAX_VALUE;
        maxLen = 0;
    }

    public void addWord(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Input word is null");
        }

        int len = word.length();
        minLen = Math.min(minLen, len);
        maxLen = Math.max(maxLen, len);

        TrieNode cur = root;
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            TrieNode next = cur.map.get(c);
            if (next == null) {
                next = new TrieNode();
                cur.map.put(c, next);
            }
            cur = next;
        }

        cur.isEnd = true;
    }

    public boolean search(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Input word is null");
        }

        int len = word.length();
        if (len < minLen || len > maxLen) {
            return false;
        }

        return searchHelper(root, word, 0);
    }

    private boolean searchHelper(TrieNode cur, String word, int idx) {
        int len = word.length();
        while (idx < len && word.charAt(idx) != '.') {
            cur = cur.map.get(word.charAt(idx++));
            if (cur == null) {
                return false;
            }
        }

        if (idx == len) {
            return cur.isEnd;
        }

        for (TrieNode v : cur.map.values()) {
            if (searchHelper(v, word, idx + 1)) {
                return true;
            }
        }
        return false;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary obj = new WordDictionary();
// obj.addWord(word);
// boolean param_2 = obj.search(word);
