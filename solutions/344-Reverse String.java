// LeetCode Question URL: https://leetcode.com/problems/reverse-string/
// LeetCode Discuss URL: https://leetcode.com/problems/reverse-string/discuss/1555808/Java-TC:-O(N)-or-SC:-O(1)-or-Simple-and-Concise-3-diff-solutions-(Iterative-and-Recursive)

/**
 * Time Complexity: O(N/2) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input char array.
 */
class Solution1 {
    public void reverseString(char[] s) {
        if (s == null) {
            throw new IllegalArgumentException("Input char array is null");
        }

        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            s[left] = (char) (s[left] ^ s[right]);
            s[right] = (char) (s[left] ^ s[right]);
            s[left] = (char) (s[left] ^ s[right]);
            left++;
            right--;
        }
    }
}

/**
 * Time Complexity: O(N/2) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input char array.
 */
class Solution2 {
    public void reverseString(char[] s) {
        if (s == null) {
            throw new IllegalArgumentException("Input char array is null");
        }

        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char t = s[left];
            s[left] = s[right];
            s[right] = t;
            left++;
            right--;
        }
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity: O(N/2) = O(N)
 *
 * Space Complexity: O(N/2) = O(N)
 *
 * N = Length of input char array.
 */
class Solution3 {
    public void reverseString(char[] s) {
        if (s == null) {
            throw new IllegalArgumentException("Input char array is null");
        }

        reverseStringHelper(s, 0, s.length - 1);
    }

    private void reverseStringHelper(char[] s, int left, int right) {
        if (left >= right) {
            return;
        }
        s[left] = (char) (s[left] ^ s[right]);
        s[right] = (char) (s[left] ^ s[right]);
        s[left] = (char) (s[left] ^ s[right]);
        reverseStringHelper(s, left + 1, right - 1);
    }
}
