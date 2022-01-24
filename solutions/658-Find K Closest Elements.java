// LeetCode Question URL: https://leetcode.com/problems/find-k-closest-elements/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Binary search
 *
 * <pre>
 * Refer:
 * 1. Approach 3: Binary Search To Find The Left Bound: https://leetcode.com/problems/find-k-closest-elements/solution/
 * 2. https://leetcode.com/problems/find-k-closest-elements/discuss/106426/JavaC++Python-Binary-Search-O(log(N-K)-+-K)
 * </pre>
 *
 * Time Complexity: O(K + log(N-K))
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array. K = Number of closest elements required.
 *
 * Refer for diagram:
 * https://www.dropbox.com/s/igfslnae1knrkl8/lc%20-%20658%20-%20find%20k%20closest%20elements.png?dl=0
 *
 */
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        if (arr == null || arr.length == 0 || k <= 0) {
            return result;
        }

        int len = arr.length;
        if (len <= k) {
            for (int n : arr) {
                result.add(n);
            }
            return result;
        }

        int start = 0;
        int end = len - k;

        while (start < end) {
            int mid = start + (end - start) / 2;

            // if (x <= arr[mid]) {
            // // x is on the left side of mid
            // end = mid;
            // } else if (x >= arr[mid + k]) {
            // // x is on the right side of mid+k
            // start = mid + 1;
            // } else {
            // // x is in between mid and mid+k
            // if (x - arr[mid] <= arr[mid + k] - x) {
            // end = mid;
            // } else {
            // start = mid + 1;
            // }
            // }

            if (x - arr[mid] <= arr[mid + k] - x) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        while (k-- > 0) {
            result.add(arr[start++]);
        }

        return result;
    }
}

class Solution2 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> result = new LinkedList<>();
        if (arr == null || arr.length == 0 || k <= 0) {
            return result;
        }

        int len = arr.length;
        if (len <= k) {
            for (int n : arr) {
                result.add(n);
            }
            return result;
        }

        int idx = Arrays.binarySearch(arr, x);
        int left, right;
        if (idx < 0) {
            idx = -idx - 1;
            left = idx - 1;
            right = idx;
        } else {
            result.add(arr[idx]);
            k--;
            left = idx - 1;
            right = idx + 1;
        }

        while (k-- > 0) {
            if (left < 0) {
                result.add(arr[right++]);
            } else if (right >= len) {
                result.addFirst(arr[left--]);
            } else {
                int leftDiff = x - arr[left];
                int rightDiff = arr[right] - x;
                if (leftDiff <= rightDiff) {
                    result.addFirst(arr[left--]);
                } else {
                    result.add(arr[right++]);
                }
            }
        }

        return result;
    }
}