// LeetCode Question URL: https://leetcode.com/problems/next-greater-element-iii/
// LeetCode Discuss URL:

/**
 * Same solution as 31-Next Permutation
 *
 * Time Complexity: O(N). N = log10(n)
 *
 * Space Complexity: O(N)
 *
 * N = Number of digits in input number. It can be maximum 10.
 */
class Solution {
    public int nextGreaterElement(int n) {
        if (n < 12) {
            return -1;
        }

        char[] charArr = String.valueOf(n).toCharArray();
        int len = charArr.length;
        int pivot = len - 2;

        while (pivot >= 0 && charArr[pivot] >= charArr[pivot + 1]) {
            pivot--;
        }
        if (pivot == -1) {
            return -1;
        }

        int i = len - 1;
        while (i > pivot & charArr[i] <= charArr[pivot]) {
            i--;
        }

        swap(charArr, pivot, i);
        reverseFromStart(charArr, pivot + 1);

        try {
            return Integer.parseInt(new String(charArr));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void swap(char[] charArr, int a, int b) {
        if (a != b) {
            char t = charArr[a];
            charArr[a] = charArr[b];
            charArr[b] = t;
        }
    }

    private void reverseFromStart(char[] charArr, int start) {
        int end = charArr.length - 1;
        while (start < end) {
            swap(charArr, start++, end--);
        }
    }
}
