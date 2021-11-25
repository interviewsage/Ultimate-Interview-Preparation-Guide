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
        Map<Character, TrieNode> children;
        boolean isWordEnd;

        TrieNode() {
            this.children = new HashMap<>();
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }

        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
        }
        cur.isWordEnd = true;
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }

        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur = cur.children.get(c);
            if (cur == null) {
                return false;
            }
        }
        return cur.isWordEnd;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null) {
            return false;
        }

        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            cur = cur.children.get(c);
            if (cur == null) {
                return false;
            }
        }
        return true;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie obj = new Trie();
// obj.insert(word);
// boolean param_2 = obj.search(word);
// boolean param_3 = obj.startsWith(prefix);