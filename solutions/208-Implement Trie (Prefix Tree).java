// LeetCode Question URL: https://leetcode.com/problems/implement-trie-prefix-tree/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity of all functions is O(N)
 *
 * Space Complexity of individual functions except delete is O(1). For delete
 * its O(N) due to recursion depth.
 *
 * N = Length of the input word.
 *
 * Space Complexity of overall Trie is O(sum of chars of all words).
 */
class Trie {

    public class TrieNode {
        Map<Character, TrieNode> map;
        boolean isEnd;

        public TrieNode() {
            map = new HashMap<>();
            isEnd = false;
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Input word is null");
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
        }

        cur.isEnd = true;
    }

    public boolean search(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Input word is null");
        }

        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur = cur.map.get(c);
            if (cur == null) {
                return false;
            }
        }

        return cur.isEnd;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Input prefix is null");
        }

        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            cur = cur.map.get(c);
            if (cur == null) {
                return false;
            }
        }

        return true;
    }

    public void delete(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Input word is null");
        }

        deleteHelper(root, word, 0);
    }

    private boolean deleteHelper(TrieNode node, String word, int idx) {
        if (node == null) {
            return false;
        }
        if (idx == word.length()) {
            if (!node.isEnd) {
                return false;
            }
            node.isEnd = false;
            return node.map.size() == 0;
        }

        char c = word.charAt(idx);
        if (!deleteHelper(node.map.get(c), word, idx + 1)) {
            return false;
        }

        node.map.remove(c);
        return !node.isEnd && node.map.size() != 0;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie obj = new Trie();
// obj.insert(word);
// boolean param_2 = obj.search(word);
// boolean param_3 = obj.startsWith(prefix);
