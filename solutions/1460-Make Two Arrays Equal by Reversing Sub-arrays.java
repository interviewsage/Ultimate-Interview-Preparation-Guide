// LeetCode Question URL: https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Facebook Follow-Up: What if the question is asking, what is the minimum number of swaps?
 * https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/discuss/660615/C++Python-1-lines/560969
 * https://leetcode.com/discuss/interview-question/1137426/Facebook-or-Minimizing-Permutations
 */

/**
 * Bit Manipulation + Product
 *
 * Refer: Fastest solution in submissions
 *
 * Also refer to this post for other Bit Manipulation answer that does not work
 * https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/discuss/1283201/Bit-Manipulation-O(1)-Space-or-Java-or-Time:-0ms-or-Memory:-39-MB
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public boolean canBeEqual(int[] target, int[] arr) {
        if (target == null || arr == null || target.length != arr.length) {
            return false;
        }

        int len = target.length;
        if (len == 0) {
            return true;
        }

        int xor = 0;
        int prodTarget = 1;
        int prodArr = 1;
        for (int i = 0; i < len; i++) {
            xor ^= target[i] ^ arr[i];
            prodTarget *= target[i];
            prodArr *= arr[i];
        }

        return prodTarget == prodArr && xor == 0;
    }
}

/**
 * CountMap
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/discuss/661521/Java-O(n)-time-and-space
 * </pre>
 *
 * Time Complexity: O(2 * N)
 *
 * Space Complexity: O(N)
 */
class Solution2 {
    public boolean canBeEqual(int[] target, int[] arr) {
        if (target == null || arr == null || target.length != arr.length) {
            return false;
        }

        int len = target.length;
        if (len == 0) {
            return true;
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            countMap.put(target[i], countMap.getOrDefault(target[i], 0) + 1);
            countMap.put(arr[i], countMap.getOrDefault(arr[i], 0) - 1);
            if (countMap.size() > len) {
                return false;
            }
        }

        for (int val : countMap.values()) {
            if (val != 0) {
                return false;
            }
        }

        return true;
    }
}
