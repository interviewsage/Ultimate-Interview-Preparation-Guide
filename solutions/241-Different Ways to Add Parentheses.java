// LeetCode Question URL: https://leetcode.com/problems/different-ways-to-add-parentheses/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Generate all substrings and calculate the possible expression values.
 *
 * String of length N can be divided into N-(K-1) subsets of K length.
 *
 * https://www.youtube.com/watch?v=pxYhN3hvKXM
 *
 * Time Complexity = ∑K=1→N ((N-(K-1))*K*K^2). Input string has N-(K-1) subsets
 * of size K. Each subset takes K*K^2 times to process. Thus overall time
 * complexity = O(N^5)
 *
 * Space Complexity = ∑K=1→N ((N-(K-1))*K + (N-(K-1))*K^2) = O(N^4). This is
 * required for HashMap to store result of all subsets. First part is for Keys.
 * Second part is for values.
 *
 * N = Length of the input string.
 */
class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        if (expression == null || expression.length() == 0) {
            throw new IllegalArgumentException("Input expression is invalid");
        }

        Map<String, List<Integer>> memoMap = new HashMap<>();
        memoMap.put("", List.of(0));
        return diffWaysToComputeHelper(expression, memoMap);
    }

    private List<Integer> diffWaysToComputeHelper(String expression, Map<String, List<Integer>> memoMap) {
        List<Integer> result = memoMap.get(expression);
        if (result != null) {
            return result;
        }

        result = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                List<Integer> list1 = diffWaysToComputeHelper(expression.substring(0, i), memoMap);
                List<Integer> list2 = diffWaysToComputeHelper(expression.substring(i + 1), memoMap);

                for (int a : list1) {
                    for (int b : list2) {
                        if (c == '+') {
                            result.add(a + b);
                        } else if (c == '-') {
                            result.add(a - b);
                        } else {
                            result.add(a * b);
                        }
                    }
                }
            }
        }

        // If the input string is just a number. It does not have any operators
        if (result.size() == 0) {
            result.add(Integer.parseInt(expression));
        }

        memoMap.put(expression, result);
        return result;
    }
}
