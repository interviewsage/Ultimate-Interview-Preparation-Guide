// LeetCode Question URL: https://leetcode.com/problems/factor-combinations/
// LeetCode Discuss URL: https://leetcode.com/problems/factor-combinations/discuss/1549648/Java-or-Optimized-Backtracking-(w-early-exit-condition)-and-Iterative-Solutions

import java.util.*;

/**
 * Optimized Backtracking solution.
 *
 * <pre>
 * Time Complexity:
 * Each node will have maximum sqrt(N) branches and maximum depth of the tree is log(N).
 * Therefore the total maximum nodes in the tree can be sqrt(N)^log(N).
 * And at each node the time take to create a copy of the temp list and add to result will be O(log(N)).
 *
 * Therefore, Total Time Complexity = O(log(N) * (sqrt(N)^log(N)))
 * This is Upper bound.
 * </pre>
 *
 * Space Complexity: O(log(N)) -> TempList + Recursion
 *
 * N = Input number.
 */
class Solution1 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 3) {
            return result;
        }

        getFactorsHelper(n, 2, new ArrayList<>(), result);
        return result;
    }

    private void getFactorsHelper(int n, int factor, List<Integer> temp, List<List<Integer>> result) {
        // i * i <= n helps to avoid duplicates.
        for (int i = factor; i * i <= n; i++) {
            if (n % i != 0) {
                continue;
            }

            temp.add(i);
            List<Integer> res = new ArrayList<>(temp);
            res.add(n / i);
            result.add(res);

            // No need to recurse if i*i > n/i as it will never enter this for loop.
            if (i * i <= n / i) {
                getFactorsHelper(n / i, i, temp, result);
            }

            temp.remove(temp.size() - 1);
        }
    }
}

/**
 * Backtracking (without optimization)
 *
 * Time Complexity: O(log(N) * (N^log(N))) -> Refer above for explanation.
 *
 * Space Complexity: O(log(N)) -> TempList + Recursion
 *
 * N = Input number.
 */
class Solution2 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 3) {
            return result;
        }

        getFactorsHelper(n, 2, new ArrayList<>(), result);
        return result;
    }

    private void getFactorsHelper(int n, int factor, List<Integer> temp, List<List<Integer>> result) {
        if (n == 1) {
            if (temp.size() > 1) {
                result.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = factor; i <= n; i++) {
            if (n % i != 0) {
                continue;
            }
            temp.add(i);
            getFactorsHelper(n / i, i, temp, result);
            temp.remove(temp.size() - 1);
        }
    }
}

/**
 * Iterative Solution
 *
 * Time Complexity: O(log(N) * (N^log(N))) -> Refer above for explanation.
 *
 * Space Complexity: O(log(N) * (N^log(N)))
 *
 * N = Input number.
 */
class Solution3 {
    public class Node {
        int n;
        int factor;
        List<Integer> temp;

        public Node(int n, int factor, List<Integer> temp) {
            this.n = n;
            this.factor = factor;
            this.temp = temp;
        }
    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 3) {
            return result;
        }

        Deque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(n, 2, new ArrayList<>()));

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            int num = cur.n;
            int factor = cur.factor;
            List<Integer> temp = cur.temp;

            for (int i = factor; i * i <= num; i++) {
                if (num % i != 0) {
                    continue;
                }
                temp.add(i);
                List<Integer> res = new ArrayList<>(temp);
                res.add(num / i);
                result.add(res);
                if (i * i <= num / i) {
                    stack.push(new Node(num / i, i, new ArrayList<>(temp)));
                }
                temp.remove(temp.size() - 1);
            }
        }

        return result;
    }
}
