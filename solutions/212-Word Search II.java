// LeetCode Question URL: https://leetcode.com/problems/word-search-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/word-search-ii/discuss/1520723/Java-or-TC:-O(RC*(3L))-or-SC:-O(N+L)-or-Optimal-Trie+DFS-solution

import java.util.*;

/**
 * Create Trie of all words. And then search in Trie.
 *
 * Refer: https://leetcode.com/problems/word-search-ii/solution/
 *
 * <pre>
 * Time Complexity: O(R*C * 4*(3^(L-1))) + O(N)
 *      O(4*(3^(L-1))) ==> For the dfsHelper function, first time we have at most 4 directions
 *                         to explore, but the choices are reduced to 3 (since no need to go back to the
 *                         cell from where we came). Therefore, in the worst case, the total number of
 *                         calls to dfsHelper will be 3^L
 *      O(N) ==> For building trie
 *
 * Space Complexity: O(N + L)
 *      O(N) ==> For Trie. We are storing reference of word. So no space used by word.
 *      O(L) ==> For Recursion Depth.
 * </pre>
 *
 * R = Number of rows. C = Number of columns. N = Total number of chars in words
 * array. L = Maximum length of a word in the words array.
 */
class Solution {
    private static final int[][] DIRS = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    class TrieNode {
        HashMap<Character, TrieNode> map;
        String word;

        public TrieNode() {
            map = new HashMap<>();
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return result;
        }

        int rows = board.length;
        int cols = board[0].length;

        TrieNode root = buildTrie(words, rows * cols);
        root.word.hashCode();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (root.map.containsKey(board[i][j])) {
                    dfsHelper(board, root, i, j, result);
                }
            }
        }

        return result;
    }

    private TrieNode buildTrie(String[] words, int maxLen) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            if (w == null || w.length() == 0 || w.length() > maxLen) {
                continue;
            }
            TrieNode cur = root;
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                if (!cur.map.containsKey(c)) {
                    cur.map.put(c, new TrieNode());
                }
                cur = cur.map.get(c);
            }
            cur.word = w;
        }
        return root;
    }

    private void dfsHelper(char[][] board, TrieNode cur, int x, int y, List<String> result) {
        if (cur == null) {
            return;
        }

        if (cur.word != null) {
            // A Valid word found. Add to the result.
            result.add(cur.word);
            // Set this word to null, so that its not added again.
            cur.word = null;
        }

        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || !cur.map.containsKey(board[x][y])) {
            return;
        }

        char curChar = board[x][y];
        board[x][y] = '#';
        for (int[] d : DIRS) {
            dfsHelper(board, cur.map.get(curChar), x + d[0], y + d[1], result);
        }
        board[x][y] = curChar;
    }
}
