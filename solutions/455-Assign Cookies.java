// LeetCode Question URL: https://leetcode.com/problems/assign-cookies/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Greedy Solution with Two Pointer
 *
 * Refer:
 * https://leetcode.com/problems/assign-cookies/discuss/93987/Simple-Greedy-Java-Solution
 *
 * <pre>
 * Time Complexity:
 * Sorting: O(N*logN + M*logM)
 * Search: O(M)
 * Total Time Complexity: O(N*logN + M*logM + M)
 *
 * As per https://www.desmos.com/calculator/ugdjmdjdze, after M = 24.5... O(M) performs better than O(log(M!)).
 * Thus first solution is better.
 *
 * Space Complexity: O(N + M). For sorting.
 * </pre>
 *
 * N = Number of Children. M = Number of Cookies
 */
class Solution1 {
    public int findContentChildren(int[] children, int[] cookies) {
        if (children == null || cookies == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int numChildren = children.length;
        int numCookies = cookies.length;
        if (numChildren == 0 || numCookies == 0) {
            return 0;
        }

        Arrays.sort(children);
        Arrays.sort(cookies);

        if (cookies[numCookies - 1] < children[0]) {
            return 0;
        }
        if (children[numChildren - 1] <= cookies[0]) {
            return Math.min(numChildren, numCookies);
        }

        int childIdx = 0;

        for (int i = 0; i < numCookies && childIdx < numChildren; i++) {
            if (cookies[i] >= children[childIdx]) {
                childIdx++;
            }
        }

        return childIdx;
    }
}

/**
 * Greedy Solution Binary Search
 *
 * <pre>
 * Time Complexity:
 * Sorting: O(N*logN + M*logM)
 * Binary Search in Worst Case when all cookies get assigned: O(log M + log (M-1) + log (M-2) + .. + log 1) = O(log(M!))
 * Total Time Complexity: O(N*logN + M*logM + O(log(M!)))
 *
 * As per https://www.desmos.com/calculator/ugdjmdjdze, after M = 24.5... O(M) performs better than O(log(M!)).
 * Thus first solution is better.
 *
 * Space Complexity: O(N + M). For sorting.
 * </pre>
 *
 * N = Number of Children. M = Number of Cookies
 */
class Solution2 {
    public int findContentChildren(int[] children, int[] cookies) {
        if (children == null || cookies == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int numChildren = children.length;
        int numCookies = cookies.length;
        if (numChildren == 0 || numCookies == 0) {
            return 0;
        }

        Arrays.sort(children);
        Arrays.sort(cookies);

        if (cookies[numCookies - 1] < children[0]) {
            return 0;
        }
        if (children[numChildren - 1] <= cookies[0]) {
            return Math.min(numChildren, numCookies);
        }

        int childIdx = 0;
        int cookieStartIdx = 0;

        while (childIdx < numChildren) {
            cookieStartIdx = binarySearchCeilIndex(cookies, cookieStartIdx, children[childIdx]);
            if (cookieStartIdx == numCookies) {
                break;
            }
            childIdx++;
            cookieStartIdx++;
        }

        return childIdx;
    }

    private int binarySearchCeilIndex(int[] arr, int start, int target) {
        int end = arr.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
