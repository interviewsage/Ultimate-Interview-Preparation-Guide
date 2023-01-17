// LeetCode URL: https://leetcode.com/problems/prefix-and-suffix-search/

import java.util.*;

/**
 * Reduced Space Complexity Answer:
 * Approach 1 at https://leetcode.com/problems/prefix-and-suffix-search/solution/
 * We use two tries to separately find all words that match the prefix, plus all words that match the suffix.
 * Then, we try to find the highest weight element in the intersection of these sets.
 */

/**
 * Using Trie
 *
 * Refer:
 * https://leetcode.com/problems/prefix-and-suffix-search/discuss/144432/Java-Beat-95-just-small-modifications-in-implementing-Trie.
 *
 * Consider the word 'apple'. For each suffix of the word, we could insert that
 * suffix, followed by '#', followed by the word, all into the trie.
 *
 * For example, we will insert '#apple', 'e#apple', 'le#apple', 'ple#apple',
 * 'pple#apple', 'apple#apple' into the trie. Then for a query like prefix =
 * "ap", suffix = "le", we can find it by querying our trie for le#ap.
 *
 * At each node, we are storing the maximum weight of the all words which have
 * path from root to this node as their prefix.
 *
 * In this particular question, we don't have to do anything particular to store
 * the maximum weight, since initial for-loop inserts the words in order of
 * increasing weight, but in a generic solution, I'd go for cur.weight =
 * Math.max(cur.weight, weight); at each node, when inserting new words.
 *
 * <pre>
 * Time & Space to Populate the Trie:
 * ((K*(K+1))/2 + (K+1) + K*(K+1)) * N = O(N * K^2)
 * </pre>
 *
 * Time Complexity: WordFilter() -> O(N * K * 2K) = O(N * K^2). f() -> O(P+S)
 *
 * Space Complexity: O(N * K^2), the size of the trie.
 *
 * N = Number of words. K = Average length of a word. P = Length of prefix. S =
 * Length of suffix.
 */
class WordFilter {

    public class TrieNode {
        Map<Character, TrieNode> map;
        int index;

        public TrieNode() {
            map = new HashMap<>();
            index = -1;
        }
    }

    TrieNode root;

    public WordFilter(String[] words) {
        if (words == null) {
            throw new IllegalArgumentException("Input words array is null");
        }

        root = new TrieNode();

        for (int i = 0; i < words.length; i++) {
            int wordLen = words[i].length();
            String word = words[i];
            for (int j = 0; j <= wordLen; j++) {
                TrieNode cur = insertFrom(root, word, j, i);
                cur = insertFrom(cur, "#", 0, i);
                insertFrom(cur, word, 0, i);
            }
        }
    }

    private TrieNode insertFrom(TrieNode cur, String word, int startIdx, int wordIdx) {
        for (int i = startIdx; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode next = cur.map.get(c);
            if (next == null) {
                next = new TrieNode();
                cur.map.put(c, next);
            }
            cur = next;
            cur.index = wordIdx;
        }

        return cur;
    }

    public int f(String pref, String suff) {
        if (pref == null || suff == null) {
            throw new IllegalArgumentException("Input pref / suff are null");
        }

        TrieNode cur = searchFrom(root, suff);
        if (cur == null) {
            return -1;
        }

        cur = searchFrom(cur, "#");
        if (cur == null) {
            return -1;
        }

        cur = searchFrom(cur, pref);
        return cur == null ? -1 : cur.index;
    }

    private TrieNode searchFrom(TrieNode cur, String word) {
        // if (start == null) {
        // return null;
        // }
        for (int i = 0; i < word.length(); i++) {
            cur = cur.map.get(word.charAt(i));
            if (cur == null) {
                return null;
            }
        }
        return cur;
    }
}

// Your WordFilter object will be instantiated and called as such:
// WordFilter obj = new WordFilter(words);
// int param_1 = obj.f(prefix,suffix);
