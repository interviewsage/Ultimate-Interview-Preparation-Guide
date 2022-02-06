// LeetCode Question URL: https://leetcode.com/problems/shuffle-string/
// LeetCode Discuss URL:

/**
 * Cyclic Sort
 *
 * Refer:
 * https://leetcode.com/problems/shuffle-string/discuss/755923/Used-Cyclic-Sort-with-O(1)-SPACE-and-O(N)-Time-complexity
 *
 * Time Complexity: O(2*N) = O(N)
 *
 * Space Complexity: O(N)
 *
 * P = Number of pieces. N = Length of arr.
 */
class Solution {
    public String restoreString(String s, int[] indices) {
        if (s == null || indices == null || s.length() != indices.length) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        if (len <= 1) {
            return s;
        }

        char[] arr = s.toCharArray();
        for (int i = 0; i < len; i++) {
            while (indices[i] != i) {
                swap(arr, i, indices[i]);
                swap(indices, i, indices[i]);
            }
        }

        return new String(arr);
    }

    private void swap(char[] arr, int x, int y) {
        char t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    private void swap(int[] arr, int x, int y) {
        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }
}
