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
 * BFS
 *
 * <pre>
 * Time Complexity:
 * 1. Each number is visited twice = O(2 * N)
 * Time Complexity = O(2*N) = O(N)
 *
 * Space Complexity:
 * 1. Queue will grow to num rows = O(NumRows)
 * </pre>
 */
class Solution1 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input list is null");
        }

        int numRows = nums.size();
        if (numRows == 0) {
            return new int[0];
        }
        if (numRows == 1) {
            int[] result = new int[nums.get(0).size()];
            int idx = 0;
            for (int n : nums.get(0)) {
                result[idx++] = n;
            }
            return result;
        }

        Deque<Iterator<Integer>> queue = new ArrayDeque<>();
        List<Integer> resultList = new ArrayList<>();
        int r = 0;

        while (!queue.isEmpty() || r < numRows) {
            if (r < numRows) {
                List<Integer> row = nums.get(r++);
                if (row != null && row.size() > 0) {
                    queue.offerFirst(row.iterator());
                }
            }

            int curSize = queue.size();
            while (curSize-- > 0) {
                Iterator<Integer> cur = queue.pollFirst();
                resultList.add(cur.next());
                if (cur.hasNext()) {
                    queue.offerLast(cur);
                }
            }
        }

        // return resultList.stream().mapToInt(o -> o).toArray();
        int[] result = new int[resultList.size()];
        int idx = 0;
        for (int n : resultList) {
            result[idx++] = n;
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
        if (nums == null) {
            throw new IllegalArgumentException("Input list is null");
        }

        int numRows = nums.size();
        if (numRows == 0) {
            return new int[0];
        }
        if (numRows == 1) {
            int[] result = new int[nums.get(0).size()];
            int idx = 0;
            for (int n : nums.get(0)) {
                result[idx++] = n;
            }
            return result;
        }

        Map<Integer, List<Integer>> diagMap = new HashMap<>();
        int minDiag = numRows;
        int maxDiag = 0;
        int numCount = 0;

        for (int i = numRows - 1; i >= 0; i--) {
            List<Integer> row = nums.get(i);
            for (int j = row.size() - 1; j >= 0; j--) {
                int diag = i + j;
                if (diagMap.putIfAbsent(diag, new ArrayList<>()) == null) {
                    maxDiag = Math.max(maxDiag, diag);
                    minDiag = Math.min(minDiag, diag);
                }
                diagMap.get(diag).add(row.get(j));
                numCount++;
            }
        }

        int[] result = new int[numCount];
        int idx = 0;
        for (int i = minDiag; i <= maxDiag; i++) {
            List<Integer> list = diagMap.get(i);
            if (list != null) {
                for (int n : list) {
                    result[idx++] = n;
                }
            }
        }

        return result;
    }
}

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
class Solution3 {
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
