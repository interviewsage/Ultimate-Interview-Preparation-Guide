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

    public class TrieNode {
        Map<Character, TrieNode> map;
        String word;

        public TrieNode() {
            map = new HashMap<>();
            word = null;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null) {
            throw new IllegalArgumentException("Input board is null");
        }

        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0 || board.length == 0 || board[0].length == 0) {
            return result;
        }

        int rows = board.length;
        int cols = board[0].length;
        TrieNode root = buildTrie(words, rows * cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                TrieNode next = root.map.get(board[i][j]);
                if (next != null) {
                    dfsHelper(board, i, j, next, result);
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
                TrieNode next = cur.map.get(c);
                if (next == null) {
                    next = new TrieNode();
                    cur.map.put(c, next);
                }
                cur = next;
            }
            cur.word = w;
        }

        return root;
    }

    private void dfsHelper(char[][] board, int r, int c, TrieNode node, List<String> result) {
        if (node.word != null) {
            result.add(node.word);
            // Set this word to null, so that its not added again.
            node.word = null;
        }

        char curChar = board[r][c];
        board[r][c] = '#';

        for (int[] d : DIRS) {
            int x = r + d[0];
            int y = c + d[1];

            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length) {
                TrieNode next = node.map.get(board[x][y]);
                if (next != null) {
                    dfsHelper(board, x, y, next, result);
                }
            }
        }

        board[r][c] = curChar;
    }
}
