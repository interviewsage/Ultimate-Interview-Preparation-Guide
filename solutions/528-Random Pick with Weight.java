// LeetCode Question URL: https://leetcode.com/problems/random-pick-with-weight/

import java.util.Arrays;
import java.util.Random;

/**
 * Prefix Sum Array and Binary Search.
 *
 * Refer:
 * https://leetcode.com/articles/random-pick-with-weight/#approach-1-prefix-sum-and-binary-search
 *
 * Time Complexity: Solution()-> O(N). pickIndex()->O(log N)
 *
 * Space Complexity: O(N)
 *
 * N = length of the input array.
 */
class Solution {

    int[] prefixSumOfWeights;
    Random random;

    public Solution(int[] w) {
        if (w == null || w.length == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = w.length;
        prefixSumOfWeights = new int[len];
        prefixSumOfWeights[0] = w[0];
        for (int i = 1; i < len; i++) {
            prefixSumOfWeights[i] = prefixSumOfWeights[i - 1] + w[i];
        }

        random = new Random();
    }

    public int pickIndex() {
        int randomWeight = random.nextInt(prefixSumOfWeights[prefixSumOfWeights.length - 1]) + 1;
        int idx = Arrays.binarySearch(prefixSumOfWeights, randomWeight);
        return idx >= 0 ? idx : -idx - 1;
    }
}

/**
 * Prefix Sum Array and Binary Search.
 *
 * Refer:
 * https://leetcode.com/articles/random-pick-with-weight/#approach-1-prefix-sum-and-binary-search
 *
 * Time Complexity: Solution()-> O(N). pickIndex()->O(log N)
 *
 * Space Complexity: O(N)
 *
 * N = length of the input array.
 */
class Solution2 {

    int[] prefixSumOfWeights;
    Random random;

    public Solution2(int[] w) {
        if (w == null || w.length == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = w.length;
        prefixSumOfWeights = new int[len];
        prefixSumOfWeights[0] = w[0];
        for (int i = 1; i < len; i++) {
            prefixSumOfWeights[i] = prefixSumOfWeights[i - 1] + w[i];
        }

        random = new Random();
    }

    public int pickIndex() {
        int randomWeight = random.nextInt(prefixSumOfWeights[prefixSumOfWeights.length - 1]) + 1;
        return binarySearch(prefixSumOfWeights, randomWeight);
    }

    private int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}

// Your Solution object will be instantiated and called as such:
// Solution obj = new Solution(w);
// int param_1 = obj.pickIndex();
