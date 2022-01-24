// LeetCode Question URL: https://leetcode.com/problems/palindrome-pairs/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Two Tries (Normal + Reverse)
 *
 * Here 2 tries make the word search O(1). We are adding next char in every
 * loop.
 *
 * <pre>
 * Time Complexity:
 * 1. O(2 * N * L) --> To build both tries.
 * 2. O(N * L^2 / 2) --> To find pairs. (To check palindrome we take O(L/2) time.)
 * Total Time Complexity: O(2 * N * L + N * L^2 / 2)
 *
 * Space Complexity: O(2 * N * L)
 * </pre>
 *
 * N = Number of words. L = Average length of each word.
 */
class Solution1 {

    class TrieNode {
        Map<Character, TrieNode> children;
        int wordIdx;

        public TrieNode() {
            children = new HashMap<>();
            wordIdx = -1;
        }

        public void addWord(String word, int index) {
            TrieNode cur = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
            }
            cur.wordIdx = index;
        }

        public void addWordReverse(String word, int index) {
            TrieNode cur = this;
            for (int i = word.length() - 1; i >= 0; i--) {
                char c = word.charAt(i);
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
            }
            cur.wordIdx = index;
        }

        public TrieNode searchNextChar(char c) {
            return this.children.get(c);
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) {
            return result;
        }

        TrieNode root = new TrieNode();
        TrieNode rootReverse = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            root.addWord(words[i], i);
            rootReverse.addWordReverse(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int wordLen = word.length();

            TrieNode cur = root;
            for (int j = wordLen; j >= 0; j--) {
                if (j < wordLen) {
                    cur = cur.searchNextChar(word.charAt(j));
                }
                if (cur == null) {
                    break;
                }
                if (cur.wordIdx != -1 && cur.wordIdx != i && isPalindrome(word, 0, j - 1)) {
                    result.add(Arrays.asList(cur.wordIdx, i));
                }
            }

            cur = rootReverse;
            for (int j = 0; j < wordLen; j++) {
                if (j > 0) {
                    cur = cur.searchNextChar(word.charAt(j - 1));
                }
                if (cur == null) {
                    break;
                }
                if (cur.wordIdx != -1 && cur.wordIdx != i && isPalindrome(word, j, wordLen - 1)) {
                    result.add(Arrays.asList(i, cur.wordIdx));
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String word, int start, int end) {
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}

/**
 * Using One Trie
 *
 * <pre>
 * Time Complexity:
 * 1. O(N * L) --> To build trie.
 * 2. O(2 * N * L^2) --> To find pairs.
 * Total Time Complexity: O(N * L + 2 * N * L^2)
 *
 * Space Complexity: O(N * L)
 * </pre>
 *
 * N = Number of words. L = Average length of each word.
 */
class Solution2 {

    public class TrieNode {
        Map<Character, TrieNode> children;
        int wordIdx;

        public TrieNode() {
            children = new HashMap<>();
            wordIdx = -1;
        }

        public void addWord(String word, int index) {
            TrieNode cur = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
            }
            cur.wordIdx = index;
        }

        public int searchReverse(String word, int start, int end) {
            TrieNode cur = this;
            for (int i = end; i >= start; i--) {
                cur = cur.children.get(word.charAt(i));
                if (cur == null) {
                    return -1;
                }
            }
            return cur.wordIdx;
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) {
            return result;
        }

        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            root.addWord(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int wordLen = word.length();

            for (int j = 0; j <= wordLen; j++) {
                if (isPalindrome(word, 0, j - 1)) {
                    int idx = root.searchReverse(word, j, wordLen - 1);
                    if (idx != -1 && idx != i) {
                        result.add(Arrays.asList(idx, i));
                    }
                }

                if (j < wordLen && isPalindrome(word, j, wordLen - 1)) {
                    int idx = root.searchReverse(word, 0, j - 1);
                    if (idx != -1 && idx != i) {
                        result.add(Arrays.asList(i, idx));
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String word, int start, int end) {
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}

/**
 * Using Two Tries (Normal + Reverse)
 *
 * Here 2 tries make the word search O(1). We are adding next char in every
 * loop.
 *
 * <pre>
 * Time Complexity:
 * 1. O(2 * N * L) --> To build both tries.
 * 2. O(N * L^2 / 2) --> To find pairs. (To check palindrome we take O(L/2) time.)
 * Total Time Complexity: O(2 * N * L + N * L^2 / 2)
 *
 * Space Complexity: O(2 * N * L)
 * </pre>
 *
 * N = Number of words. L = Average length of each word.
 */
class Solution3 {

    class TrieNode {
        TrieNode[] children;
        int wordIdx;

        public TrieNode() {
            children = new TrieNode[26];
            wordIdx = -1;
        }

        public void addWord(String word, int index) {
            TrieNode cur = this;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new TrieNode();
                }
                cur = cur.children[c];
            }
            cur.wordIdx = index;
        }

        public void addWordReverse(String word, int index) {
            TrieNode cur = this;
            for (int i = word.length() - 1; i >= 0; i--) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new TrieNode();
                }
                cur = cur.children[c];
            }
            cur.wordIdx = index;
        }

        public TrieNode searchNextChar(char c) {
            return this.children[c - 'a'];
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) {
            return result;
        }

        TrieNode root = new TrieNode();
        TrieNode rootReverse = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            root.addWord(words[i], i);
            rootReverse.addWordReverse(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int wordLen = word.length();

            TrieNode cur = root;
            for (int j = wordLen; j >= 0; j--) {
                if (j < wordLen) {
                    cur = cur.searchNextChar(word.charAt(j));
                }
                if (cur == null) {
                    break;
                }
                if (cur.wordIdx != -1 && cur.wordIdx != i && isPalindrome(word, 0, j - 1)) {
                    result.add(Arrays.asList(cur.wordIdx, i));
                }
            }

            cur = rootReverse;
            for (int j = 0; j < wordLen; j++) {
                if (j > 0) {
                    cur = cur.searchNextChar(word.charAt(j - 1));
                }
                if (cur == null) {
                    break;
                }
                if (cur.wordIdx != -1 && cur.wordIdx != i && isPalindrome(word, j, wordLen - 1)) {
                    result.add(Arrays.asList(i, cur.wordIdx));
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String word, int start, int end) {
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}

/**
 * Using One Trie
 *
 * <pre>
 * Time Complexity:
 * 1. O(N * L) --> To build trie.
 * 2. O(2 * N * L^2) --> To find pairs.
 * Total Time Complexity: O(N * L + 2 * N * L^2)
 *
 * Space Complexity: O(N * L)
 * </pre>
 *
 * N = Number of words. L = Average length of each word.
 */
class Solution4 {

    public class TrieNode {
        TrieNode[] children;
        int wordIdx;

        public TrieNode() {
            children = new TrieNode[26];
            wordIdx = -1;
        }

        public void addWord(String word, int index) {
            TrieNode cur = this;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new TrieNode();
                }
                cur = cur.children[c];
            }
            cur.wordIdx = index;
        }

        public int searchReverse(String word, int start, int end) {
            TrieNode cur = this;
            for (int i = end; i >= start; i--) {
                cur = cur.children[word.charAt(i) - 'a'];
                if (cur == null) {
                    return -1;
                }
            }
            return cur.wordIdx;
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) {
            return result;
        }

        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            root.addWord(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int wordLen = word.length();

            for (int j = 0; j <= wordLen; j++) {
                if (isPalindrome(word, 0, j - 1)) {
                    int idx = root.searchReverse(word, j, wordLen - 1);
                    if (idx != -1 && idx != i) {
                        result.add(Arrays.asList(idx, i));
                    }
                }

                if (j < wordLen && isPalindrome(word, j, wordLen - 1)) {
                    int idx = root.searchReverse(word, 0, j - 1);
                    if (idx != -1 && idx != i) {
                        result.add(Arrays.asList(i, idx));
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String word, int start, int end) {
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}

/**
 * Step 1: store every word with its index into a hash map.
 *
 * Step 2: For each word in the array, split into two parts str1 and str2. Check
 * whether str1 and str2 is palindrome If str1 is palindrome, we can use str1 as
 * middle part, str2 as right part, and find if map contains reversed str2. If
 * contains, then we can use that string as left part, combine with middle part,
 * right part, it will form a correct palindrome string.
 *
 * Step 3: do all same operations for str2 (set str2 as middle part)
 *
 * Time Complexity: O(N * L^2 + N) = O(N * L^2)
 *
 * Space Complexity: O(N * L + L) = O(N * L)
 *
 * N = Number of words in the input array. L = Average length of a word.
 */
class Solution5 {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) {
            return result;
        }

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if (w.length() == 0) {
                continue;
            }

            for (int j = 0; j <= w.length(); j++) {
                String p1 = w.substring(0, j);
                String p2 = w.substring(j);

                if (isPalindrome(p1)) {
                    String p2Rev = new StringBuilder(p2).reverse().toString();
                    if (map.containsKey(p2Rev) && map.get(p2Rev) != i) {
                        result.add(Arrays.asList(map.get(p2Rev), i));
                    }
                }
                if (p2.length() > 0 && isPalindrome(p2)) {
                    String p1Rev = new StringBuilder(p1).reverse().toString();
                    if (map.containsKey(p1Rev) && map.get(p1Rev) != i) {
                        result.add(Arrays.asList(i, map.get(p1Rev)));
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String w) {
        int l = 0;
        int r = w.length() - 1;
        while (l < r) {
            if (w.charAt(l) != w.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}

/**
 * Using Trie
 *
 * Refer:
 * https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure
 *
 * Time Complexity: O(N * L^2)
 *
 * Space Complexity: O(N * L)
 *
 * N = Number of words in the input array. L = Average length of a word.
 */
class Solution6 {
    class TrieNode {
        Map<Character, TrieNode> map;
        int index;
        List<Integer> palindromes;

        TrieNode() {
            map = new HashMap<>();
            index = -1;
            palindromes = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        if (words == null || words.length < 2) {
            return result;
        }

        TrieNode root = new TrieNode();

        for (int i = 0; i < words.length; i++) {
            buildTrie(root, words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            search(result, root, words[i], i);
        }

        return result;
    }

    private void buildTrie(TrieNode cur, String w, int idx) {
        for (int j = w.length() - 1; j >= 0; j--) {
            char c = w.charAt(j);
            if (!cur.map.containsKey(c)) {
                cur.map.put(c, new TrieNode());
            }
            if (isPalindrome(w, 0, j)) {
                cur.palindromes.add(idx);
            }
            cur = cur.map.get(c);
        }
        cur.palindromes.add(idx);
        cur.index = idx;
    }

    private boolean isPalindrome(String w, int l, int r) {
        while (l < r) {
            if (w.charAt(l) != w.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }

        return true;
    }

    private void search(List<List<Integer>> result, TrieNode cur, String w, int idx) {
        for (int j = 0; j < w.length(); j++) {
            if (cur.index >= 0 && cur.index != idx && isPalindrome(w, j, w.length() - 1)) {
                result.add(Arrays.asList(idx, cur.index));
            }
            char c = w.charAt(j);
            if (!cur.map.containsKey(c)) {
                return;
            }
            cur = cur.map.get(c);
        }

        for (int j : cur.palindromes) {
            if (idx != j) {
                result.add(Arrays.asList(idx, j));
            }
        }
    }
}
