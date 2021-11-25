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
 * Space Complexity: O(TotalCharsInTrie)
 * </pre>
 *
 * M = Input word length.
 */
class WordDictionary {

    public class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;

        public TrieNode() {
            children = new HashMap<>();
        }

        public void addWord(String word) {
            TrieNode cur = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
            }
            cur.isEnd = true;
        }

        public boolean searchWordWithWildCards(String word, int idx) {
            int len = word.length();
            TrieNode cur = this;

            while (idx < len && word.charAt(idx) != '.') {
                cur = cur.children.get(word.charAt(idx++));
                if (cur == null) {
                    return false;
                }
            }

            if (idx == len) {
                return cur.isEnd;
            }

            for (TrieNode child : cur.children.values()) {
                if (child.searchWordWithWildCards(word, idx + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

    TrieNode root;
    int minWordLen;
    int maxWordLen;

    public WordDictionary() {
        root = new TrieNode();
        minWordLen = Integer.MAX_VALUE;
        maxWordLen = 0;
    }

    public void addWord(String word) {
        if (word == null) {
            return;
        }
        int len = word.length();
        minWordLen = Math.min(minWordLen, len);
        maxWordLen = Math.max(maxWordLen, len);
        root.addWord(word);
    }

    public boolean search(String word) {
        if (word == null || word.length() < minWordLen || word.length() > maxWordLen) {
            return false;
        }
        return root.searchWordWithWildCards(word, 0);
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary obj = new WordDictionary();
// obj.addWord(word);
// boolean param_2 = obj.search(word);
