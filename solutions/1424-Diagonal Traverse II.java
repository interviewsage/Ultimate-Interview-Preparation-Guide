// LeetCode Question URL: https://leetcode.com/problems/diagonal-traverse-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/diagonal-traverse-ii/discuss/597698/JavaC++-HashMap-with-Picture-Clean-code-O(N)
 * 2. https://leetcode.com/problems/diagonal-traverse-ii/discuss/597741/Clean-Simple-Easiest-Explanation-with-a-picture-and-code-with-comments
 * </pre>
 */

/**
 * <pre>
 * Time Complexity:
 * 1. Each number is visited twice = O(2 * N)
 * 2. Populate unique diagSet TreeSet = O(U * log U)
 * 3. Iterate over unique diagonals = O(U)
 * Time Complexity = O(2*N + U*logU + U) = O(N + U*logU + U)
 *
 * Space Complexity:
 * 1. HashMap will take All numbers + unique diagonals = O(N + U)
 * 2. diagSet TreeSet will take all unique diagonals = O(U)
 * Total Space Complexity = O(N + 2*U) = O(N + U)
 * </pre>
 */
class Solution1 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0) {
            return new int[0];
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> diagSet = new TreeSet<>();
        int numCount = 0;

        for (int i = nums.size() - 1; i >= 0; i--) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                int diag = i + j;
                if (map.putIfAbsent(diag, new ArrayList<>()) == null) {
                    diagSet.add(diag);
                }
                map.get(diag).add(nums.get(i).get(j));
                numCount++;
            }
        }

        int[] result = new int[numCount];
        int idx = 0;
        for (int diag : diagSet) {
            for (int n : map.get(diag)) {
                result[idx++] = n;
            }
        }
        return result;
    }
}

/**
 * <pre>
 * Time Complexity:
 * 1. Each number is visited twice = O(2 * N)
 * 3. Iterate on maxDiag = O(U)
 * Time Complexity = O(2*N + U) = O(N + U)
 *
 * Space Complexity:
 * 1. HashMap will take All numbers + unique diagonals = O(N + U)
 * </pre>
 */
class Solution2 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0) {
            return new int[0];
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        int numCount = 0;
        int maxDiag = -1;

        for (int i = nums.size() - 1; i >= 0; i--) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                int diag = i + j;
                if (map.putIfAbsent(diag, new ArrayList<>()) == null) {
                    maxDiag = Math.max(maxDiag, diag);
                }
                map.get(diag).add(nums.get(i).get(j));
                numCount++;
            }
        }

        int[] result = new int[numCount];
        int idx = 0;
        for (int i = 0; i <= maxDiag; i++) {
            List<Integer> list = map.get(i);
            if (list == null) {
                continue;
            }
            for (int n : list) {
                result[idx++] = n;
            }
        }
        return result;
    }
}
