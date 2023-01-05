// LeetCode Question URL: https://leetcode.com/problems/previous-permutation-with-one-swap/
// LeetCode Discuss URL:

/**
 * Find the index where first increasing number is found. That will be the
 * pivot. Now find the first lower number from right. If there are repeating
 * numbers, then get the last occurrence. Then swap it with pivot.
 *
 * Time Complexity: O(N) - Each Number can be visited twice.
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public int[] prevPermOpt1(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = arr.length;
        if (len <= 1) {
            return arr;
        }

        int i = len - 2;
        while (i >= 0 && arr[i] <= arr[i + 1]) {
            i--;
        }
        if (i == -1) {
            return arr;
        }

        int j = len - 1;
        while (j > i && (arr[i] <= arr[j] || arr[j - 1] == arr[j])) {
            j--;
        }

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
        return arr;
    }
}

/**
 * Find the index where first increasing number is found. That will be the
 * pivot. Now find the just smallest number from the left of pivot. If there are
 * repeating numbers, then get the first occurrence. Then swap it with pivot.
 *
 * Time Complexity: O(N) - Each Number can be visited twice.
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public int[] prevPermOpt1(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = arr.length;
        if (len <= 1) {
            return arr;
        }

        int i = len - 2;
        while (i >= 0 && arr[i] <= arr[i + 1]) {
            i--;
        }
        if (i == -1) {
            return arr;
        }

        int j = i + 1;
        int jToBeSwapped = j;
        while (j < len && arr[j] < arr[i]) {
            if (arr[j - 1] != arr[j]) {
                jToBeSwapped = j;
            }
            j++;
        }

        int t = arr[i];
        arr[i] = arr[jToBeSwapped];
        arr[jToBeSwapped] = t;
        return arr;
    }
}
