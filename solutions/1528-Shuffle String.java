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

        char[] charArr = s.toCharArray();
        int len = charArr.length;
        if (len <= 1) {
            return new String(charArr);
        }

        for (int i = 0; i < len; i++) {
            while (i != indices[i]) {
                swap(charArr, i, indices[i]);
                swap(indices, i, indices[i]);
            }
        }

        return new String(charArr);
    }

    private static void swap(char[] arr, int x, int y) {
        char t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    private static void swap(int[] arr, int x, int y) {
        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }
}
