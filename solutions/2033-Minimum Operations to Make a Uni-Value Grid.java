// LeetCode Question URL: https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Two Pointer Method
 *
 * This solution is same as 296. Best Meeting Point
 *
 * Refer:
 * https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/discuss/1513354/Straight-Forward-Python-Two-Pointers-without-Making-Median
 *
 * Time Complexity: O(N + N/2 + N*logN) = O(N + N*logN)
 *
 * Space Complexity: O(N)
 *
 * N = Number of cells = R*C.
 */
class Solution1 {
    public int minOperations(int[][] grid, int x) {
        if (grid == null || x <= 0) {
            return -1;
        }
        if (grid.length == 0 || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int len = rows * cols;
        int[] nums = new int[len];
        int i = 0;
        int rem = grid[0][0] % x;
        for (int[] row : grid) {
            for (int n : row) {
                if (n % x != rem) {
                    return -1;
                }
                nums[i++] = n;
            }
        }

        Arrays.sort(nums);

        int result = 0;
        int p1 = 0;
        int p2 = len - 1;
        while (p1 < p2) {
            result += (nums[p2--] - nums[p1++]) / x;
        }

        return result;
    }
}

/**
 * Two Swipe + Sort Method
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/discuss/1513331/Two-swipes-vs.-Median
 * 2. https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/discuss/1513354/Straight-Forward-Python-Two-Pointers-without-Making-Median
 *
 * Time Complexity: O(3*N + N*logN) = O(N + N*logN)
 *
 * Space Complexity: O(2*N)= O(N)
 *
 * N = Number of cells = R*C.
 * </pre>
 */
class Solution2 {
    public int minOperations(int[][] grid, int x) {
        if (grid == null || x <= 0) {
            return -1;
        }
        if (grid.length == 0 || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int len = rows * cols;
        int[] nums = new int[len];
        int k = 0;
        int rem = grid[0][0] % x;
        for (int[] row : grid) {
            for (int n : row) {
                if (n % x != rem) {
                    return -1;
                }
                nums[k++] = n;
            }
        }

        Arrays.sort(nums);
        int[] dp = new int[len];

        for (int i = 1; i < len; i++) {
            dp[i] = dp[i - 1] + i * (nums[i] - nums[i - 1]) / x;
        }

        int result = dp[len - 1];
        int opsRTL = 0;

        for (int i = len - 2; i >= 0; i--) {
            opsRTL += (len - i - 1) * (nums[i + 1] - nums[i]) / x;
            result = Math.min(result, opsRTL + dp[i]);
        }

        return result;
    }
}

/**
 * Using Quick Select
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/discuss/1513331/Two-swipes-vs.-Median
 * 2. https://asvrada.github.io/blog/median-shortest-distance-sum/
 *
 * Time Complexity:
 * 1. Create nums array: O(M*N)
 * 2. Quick Select: O(M*N) is Average Case. O((M*N)^2) in worst case
 * 3. Find result: O(M*N)
 *
 * Space Complexity: O(M*N)
 * </pre>
 */
class Solution3 {
    private static final Random RANDOM = new Random();

    public int minOperations(int[][] grid, int x) {
        if (grid == null || x <= 0) {
            return -1;
        }
        if (grid.length == 0 || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int len = rows * cols;
        int[] nums = new int[len];
        int i = 0;
        int rem = grid[0][0] % x;
        for (int[] row : grid) {
            for (int n : row) {
                if (n % x != rem) {
                    return -1;
                }
                nums[i++] = n;
            }
        }

        int start = 0;
        int end = len - 1;
        int k = len / 2;
        while (start < end) {
            int p = partition(nums, start, end);
            if (p == k) {
                break;
            }
            if (p < k) {
                start = p + 1;
            } else {
                end = p - 1;
            }
        }

        int result = 0;
        for (int n : nums) {
            // int diff = Math.abs(n - nums[k]);
            // if (diff % x != 0) {
            // return -1;
            // }
            result += Math.abs(n - nums[k]) / x;
        }

        return result;
    }

    private int partition(int[] nums, int start, int end) {
        swap(nums, start, start + RANDOM.nextInt(end - start + 1));
        int insertPos = start;

        for (int i = start + 1; i <= end; i++) {
            if (nums[i] < nums[start]) {
                insertPos++;
                swap(nums, insertPos, i);
            }
        }

        swap(nums, insertPos, start);
        return insertPos;
    }

    private void swap(int[] nums, int x, int y) {
        if (x != y) {
            int t = nums[x];
            nums[x] = nums[y];
            nums[y] = t;
        }
    }
}

class Solution4 {

    public int minOperations(int[][] grid, int x) {

        int m = grid.length;
        int n = grid[0].length;
        int size = m * n;

        int[] hist = new int[10001];

        int rem = grid[0][0] % x;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((grid[i][j] % x) != rem)
                    return -1;
                hist[grid[i][j]]++;
            }
        }

        int count = 0;
        int medium = 0;

        // find median using count sort instead of sorting
        int middleSize = (size + 1) / 2;
        while (count < middleSize) {
            count += hist[++medium];
        }
        count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int dif = Math.abs(grid[i][j] - medium);
                // if (dif % x != 0) return -1;
                count += (dif / x);
            }
        }
        return count;
        // return op(grid, index, x);
    }

    // int op (int[][] grid, int medium, int x) {
    // int op = 0;
    // for (int i = 0; i < m; i++) {
    // for (int j = 0; j < n; j++) {
    // int dif = Math.abs(grid[i][j] - medium);
    // if (dif % x != 0) return -1;
    // op += (dif / x);
    // }
    // }
    // return op;
    // }

    public int minOperations1(int[][] grid, int x) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        int m = grid.length, n = grid[0].length;
        int[] nums = new int[m * n];
        int rem = grid[0][0] % x;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] % x != rem) {
                    return -1;
                }
                nums[r * n + c] = grid[r][c];
            }
        }
        Arrays.sort(nums);
        int median = nums[(m * n - 1) / 2];
        int ops = 0;
        for (int num : nums) {
            ops += (Math.abs(median - num) / x);
        }
        return ops;
    }

    public int minOperations2(int[][] grid, int x) {
        ArrayList<Integer> arr = new ArrayList<>();
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr.add(grid[i][j]);
            }
        }
        Collections.sort(arr);
        int median = arr.get((arr.size()) / 2);
        int count = 0;
        for (int val : arr) {
            int diff = Math.abs(val - median);
            if (diff % x != 0)
                return -1;
            count += diff / x;
        }
        return count;
    }
}
