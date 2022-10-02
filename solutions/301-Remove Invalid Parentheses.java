// LeetCode Question URL: https://leetcode.com/problems/remove-invalid-parentheses/

import java.util.*;

/**
 * BFS
 *
 * Refer:
 * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75032/Share-my-Java-BFS-solution
 *
 * <pre>
 * Time Complexity: O(n * 2^n).
 *
 * In BFS we handle the states level by level, in the worst case, we need to
 * handle all the levels, we can analyze the time complexity level by level and
 * add them up to get the final complexity.
 *
 * On the first level, there's only one string which is the input string s,
 * let's say the length of it is n, to check whether it's valid, we need O(n)
 * time. On the second level, we remove one ( or ) from the first level, so
 * there are C(n, n-1) new strings, each of them has n-1 characters, and for
 * each string, we need to check whether it's valid or not, thus the total time
 * complexity on this level is (n-1) x C(n, n-1). Come to the third level, total
 * time complexity is (n-2) x C(n, n-2), so on and so forth...
 *
 * Finally we have this formula:
 *
 * T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).
 * https://www.algebra.com/algebra/homework/equations/Equations.faq.question.1093265.html
 *
 * C(n, 0) + C(n, 1) + ... + C(n, n-1) + C(n, n) ==> This represents total number of subsets.
 *
 * Space Complexity: O(n/2 * C(n, (n/2))). Upper bound = O(n * (2*e)^(n/2))
 *
 * At n/2 level, the space required will be maximum. Refer below link for proof
 * and calculation of nCk
 * https://www.johndcook.com/blog/2008/11/10/bounds-on-binomial-coefficients/
 * </pre>
 *
 * Check these LC discuss links
 * https://leetcode.com/discuss/interview-question/124551/Facebook-or-Onsite-or-Remove-Invalid-Parentheses
 * https://leetcode.com/discuss/interview-question/310746/Facebook-or-Phone-screen-or-Remove-Invalid-Parentheses
 */
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Invalid Input String");
        }

        List<String> result = new ArrayList<>();
        if (s.length() == 0) {
            result.add("");
            return result;
        }

        Queue<String> queue = new LinkedList<>();
        boolean foundValid = false;
        queue.offer(s);

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            Set<String> visitedNextLevel = new HashSet<>();
            for (int i = 0; i < queueSize; i++) {
                String cur = queue.poll();

                if (isValid(cur)) {
                    result.add(cur);
                    foundValid = true;
                }
                if (foundValid) {
                    continue;
                }

                for (int j = 0; j < cur.length(); j++) {
                    char c = cur.charAt(j);
                    if (c != '(' && c != ')') {
                        continue;
                    }
                    String newCur = cur.substring(0, j) + cur.substring(j + 1);
                    if (visitedNextLevel.add(newCur)) {
                        queue.offer(newCur);
                    }
                }
            }

            if (foundValid) {
                break;
            }
        }

        return result;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }
}
