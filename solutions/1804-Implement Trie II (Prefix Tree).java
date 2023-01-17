// LeetCode Question URL: https://leetcode.com/problems/implement-trie-ii-prefix-tree/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Maintain two additional details at each TrieNode.
 * 1. Number of words with prefix till this node. (Note prefixCount does not contain words ending count)
 * 2. Number of words ending here.
 * </pre>
 *
 * Time Complexity: All Functions take O(N) time.
 *
 * <pre>
 * Space Complexity:
 * 1. Only erase() function where we also check if word exists needs O(N) space for recursion stack.
 * 2. Remaining all functions take O(1) space.
 * </pre>
 */
class Trie {

    public class TrieNode {
        Map<Character, TrieNode> map;
        int wordCount;
        int prefixCount;

        public TrieNode() {
            map = new HashMap<>();
            wordCount = 0;
            prefixCount = 0;
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
            cur.prefixCount++;
            cur = next;
        }

        cur.wordCount++;
    }

    public int countWordsEqualTo(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Input word is null");
        }

        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur = cur.map.get(c);
            if (cur == null) {
                return 0;
            }
        }

        return cur.wordCount;
    }

    public int countWordsStartingWith(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Input prefix is null");
        }

        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            cur = cur.map.get(c);
            if (cur == null) {
                return 0;
            }
        }

        return cur.wordCount + cur.prefixCount;
    }

    public void erase(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Input word is null");
        }

        eraseHelper(root, word, 0);
    }

    private boolean eraseHelper(TrieNode node, String word, int idx) {
        if (idx == word.length()) {
            if (node.wordCount == 0) {
                return false;
            }
            node.wordCount--;
            return true;
        }

        char c = word.charAt(idx);
        TrieNode next = node.map.get(c);
        if (next == null || !eraseHelper(next, word, idx + 1)) {
            return false;
        }

        node.prefixCount--;
        if (next.wordCount == 0 && next.prefixCount == 0) {
            node.map.remove(c);
        }
        return true;
    }
}

/**
 * <pre>
 * Maintain two additional details at each TrieNode.
 * 1. Number of words with prefix till this node.
 * 2. Number of words ending here.
 * </pre>
 *
 * Time Complexity: All Functions take O(N) time.
 *
 * <pre>
 * Space Complexity:
 * 1. Only erase() function where we also check if word exists needs O(N) space for recursion stack.
 * 2. Remaining all functions take O(1) space.
 * </pre>
 */
class Trie2 {

    public class TrieNode {
        Map<Character, TrieNode> children;
        int prefixCount;
        int wordCount;

        public TrieNode() {
            children = new HashMap<>();
        }
    }

    TrieNode root;

    public Trie2() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Input word is null");
        }
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.prefixCount++;
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
        }
        cur.prefixCount++;
        cur.wordCount++;
    }

    public int countWordsEqualTo(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Input word is null");
        }
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur = cur.children.get(c);
            if (cur == null) {
                return 0;
            }
        }
        return cur.wordCount;
    }

    public int countWordsStartingWith(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Input prefix is null");
        }
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            cur = cur.children.get(c);
            if (cur == null) {
                return 0;
            }
        }
        return cur.prefixCount;
    }

    // This erase function assumes word always exists in the Trie.
    // Refer:
    // https://leetcode.com/problems/implement-trie-ii-prefix-tree/discuss/1126702/Java-Trie-with-comments/909720
    public void erase(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Input word is null");
        }
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.prefixCount--;
            TrieNode child = cur.children.get(c);
            if (child.prefixCount == 1) {
                cur.children.remove(c);
            }
            cur = child;
        }

        cur.prefixCount--;
        cur.wordCount--;
    }

    // This erase function will delete the word, only if it exists.
    public void erase(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Input word is null");
        }
        eraseHelper(word, 0, root);
    }

    private boolean eraseHelper(String word, int idx, TrieNode cur) {
        if (idx == word.length()) {
            if (cur.wordCount > 0) {
                cur.wordCount--;
                cur.prefixCount--;
                return true;
            }
            return false;
        }

        char c = word.charAt(idx);
        TrieNode next = cur.children.get(c);
        if (next == null || !eraseHelper(word, idx + 1, next)) {
            return false;
        }

        if (next.prefixCount == 0) {
            cur.children.remove(c);
        }
        cur.prefixCount--;
        return true;
    }
}

/**
 * <pre>
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 * </pre>
 */
