// LeetCode Question URL: https://leetcode.com/problems/strobogrammatic-number-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Recursive Backtracking using char array
 *
 * <pre>
 * Time Complexity:
 * T(N)   = 5 * T(N-2) + O(5)
 * T(N-2) = 5 * T(N-4) + O(5)
 * T(N-4) = 5 * T(N-6) + O(5)
 * ...
 * T(2)   = 5 * T(0) + O(5)
 * T(0) = O(N) //  To add the string into ResultList
 *
 * T(N) = 5 + 5^2 + 5^3 + ... + 5^(N/2) + 5^(N/2) * N
 * T(N) = 5 * (5^(N/2 + 1) - 1) / (5 - 1) + 5^(N/2) * N
 * T(N) = 5^(N/2) * (N + 25/4) - 5/4
 * T(N) ~ O(N * 5^(N/2))
 * </pre>
 *
 * Space Complexity: O(N + N/2). There will be maximum N/2 levels in recursion
 * stack.
 *
 * N = input number.
 */
class Solution1 {
    private static final Map<Character, Character> MATCH_MAP = Map.of('0', '0', '1', '1', '8', '8', '6', '9', '9', '6');

    public List<String> findStrobogrammatic(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input is a negative number");
        }
        if (n == 1) {
            return List.of("0", "1", "8");
        }

        List<String> result = new ArrayList<>();
        findStrobogrammaticHelper(result, 0, new char[n]);

        return result;
    }

    private void findStrobogrammaticHelper(List<String> result, int left, char[] charArr) {
        int right = charArr.length - 1 - left;

        if (left > right) {
            result.add(new String(charArr));
            return;
        }

        for (char c : MATCH_MAP.keySet()) {
            // Do not add first char as '0'
            if (left == 0 && c == '0') {
                continue;
            }
            // Do not add '6' or '9' at the center for odd length strings.
            if (left == right && (c == '6' || c == '9')) {
                continue;
            }

            charArr[left] = c;
            charArr[right] = MATCH_MAP.get(c);

            findStrobogrammaticHelper(result, left + 1, charArr);
        }
    }
}

/**
 * Iterative
 *
 * Time Complexity : O(N * 5 ^ (N/2))
 *
 * Space Complexity: O(N * 5 ^ (N/2)). There will be 5 ^ ((N-2)/2) strings of
 * N-2 length at level before result level.
 *
 * N = input number.
 */
class Solution2 {
    public List<String> findStrobogrammatic(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input is a negative number");
        }

        List<String> result = new ArrayList<>();

        if ((n & 1) == 0) {
            result.add("");
        } else {
            result.add("0");
            result.add("1");
            result.add("8");
            n--;
        }

        for (int i = n; i > 0; i -= 2) {
            List<String> list = result;
            result = new ArrayList<>();
            for (String s : list) {
                if (i != 2) {
                    result.add("0" + s + "0");
                }
                result.add("1" + s + "1");
                result.add("8" + s + "8");
                result.add("6" + s + "9");
                result.add("9" + s + "6");
            }
        }
        return result;
    }
}

/**
 * Do not refer this solution.
 *
 * Recursive Backtracking Using temp strings
 *
 * Time Complexity : O(5 ^ (N/2))
 *
 * Space Complexity: O(N ^ 2). There will be maximum N/2 levels in recursion
 * stack.
 *
 * N = input number.
 */
class Solution3 {
    public List<String> findStrobogrammatic(int n) {
        ArrayList<String> result = new ArrayList<>();
        if (n < 0) {
            return result;
        }
        if (n % 2 == 0) {
            dfsHelper(result, "", n);
        } else {
            dfsHelper(result, "0", n);
            dfsHelper(result, "1", n);
            dfsHelper(result, "8", n);
        }

        return result;
    }

    private void dfsHelper(ArrayList<String> list, String tempStr, int n) {
        if (tempStr.length() == n) {
            if (n > 1 && tempStr.charAt(0) == '0') {
                return;
            }
            list.add(tempStr.toString());
            return;
        }

        dfsHelper(list, "0" + tempStr + "0", n);
        dfsHelper(list, "1" + tempStr + "1", n);
        dfsHelper(list, "8" + tempStr + "8", n);
        dfsHelper(list, "6" + tempStr + "9", n);
        dfsHelper(list, "9" + tempStr + "6", n);
    }
}

/**
 * Do not refer this solution.
 *
 * Recursive
 *
 * Time Complexity : O(5 ^ (N/2))
 *
 * Space Complexity: O(N * 5 ^ (N/2)). There will be 5 ^ ((N-2)/2) strings of
 * N-2 length at level before result level.
 *
 * N = input number.
 */
class Solution4 {
    public List<String> findStrobogrammatic(int n) {
        if (n < 0) {
            return new ArrayList<>();
        }

        return dfsHelper(n, n);
    }

    private List<String> dfsHelper(int m, int n) {
        List<String> res = new ArrayList<>();
        if (m == 0) {
            res.add("");
            return res;
        }
        if (m == 1) {
            res.add("0");
            res.add("1");
            res.add("8");
            return res;
        }

        List<String> list = dfsHelper(m - 2, n);

        for (String s : list) {
            if (m != n) {
                res.add("0" + s + "0");
            }
            res.add("1" + s + "1");
            res.add("8" + s + "8");
            res.add("6" + s + "9");
            res.add("9" + s + "6");
        }
        return res;
    }
}
