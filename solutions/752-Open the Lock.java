// LeetCode Question URL: https://leetcode.com/problems/open-the-lock/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Double Ended BFS
 *
 * Refer:
 * https://leetcode.com/problems/open-the-lock/discuss/110237/Regular-java-BFS-solution-and-2-end-BFS-solution-with-improvement
 *
 * <pre>
 * Time Complexity:
 * Total Number of possible combinations = A^N
 * Each combinations takes O(N + 2*N^2) time to get processed
 * And, we need O(D) time to add deadends in visited set.
 *
 * Thus, Total Time Complexity = O(A^N * (N + 2*N^2) + D) = O(A^N * N^2 + D)
 *
 * Space Complexity:
 * Total Number of possible combinations = A^N
 * Each combination length is N.
 *
 * Thus, Total Space Complexity = O(N * A^N)
 * </pre>
 *
 * A = 0-9 (Number of digits in each dial). N = 4 (Number of circular dials). D
 * = Number of deadends which is bounded by A^N
 */
class Solution1 {
    private static final String START = "0000";

    public int openLock(String[] deadends, String target) {
        if (target == null) {
            return -1;
        }
        if (START.equals(target)) {
            return 0;
        }

        Set<String> visited = new HashSet<>();
        if (deadends != null) {
            for (String d : deadends) {
                if (START.equals(d) || target.equals(d)) {
                    return -1;
                }
                visited.add(d);
            }
        }

        Set<String> beginSet = new HashSet<>();
        beginSet.add(START);
        visited.add(START);
        Set<String> endSet = new HashSet<>();
        endSet.add(target);
        visited.add(target);

        int result = 1;

        while (!beginSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> newSet = new HashSet<>();
            for (String cur : beginSet) {
                char[] chrArr = cur.toCharArray();
                for (int i = 0; i < 4; i++) {
                    char curChar = chrArr[i];
                    for (int j = 0; j < 2; j++) {
                        if (j == 0) {
                            chrArr[i] = curChar == '9' ? '0' : (char) (curChar + 1);
                        } else {
                            chrArr[i] = curChar == '0' ? '9' : (char) (curChar - 1);
                        }
                        String newStr = new String(chrArr);
                        if (endSet.contains(newStr)) {
                            return result;
                        }
                        if (visited.add(newStr)) {
                            newSet.add(newStr);
                        }
                    }
                    chrArr[i] = curChar;
                }
            }
            result++;
            beginSet = newSet;
        }

        return -1;
    }
}

/**
 * Double Ended BFS
 *
 * Refer:
 * https://leetcode.com/problems/open-the-lock/discuss/110237/Regular-java-BFS-solution-and-2-end-BFS-solution-with-improvement
 *
 * Time Complexity: O(Vertexes + Edges + D). Vertexes = A^N. Edges = 2*N*A^N.
 * Also, for each vertex it take 2*N to generate the edges. Time Complexity =
 * O(A^N + 2*N*A^N + 2*N*A^N + D) = O(A^N + 4*N*A^N + D) = O(10^4 + 4*4*10^4 +
 * D)
 *
 * Space Complexity: O(A^N*N + D*N) = O(10^4*4 +D*4). N is multiplied in both as
 * the size of string is 4.
 *
 * A = 0-9 (Number of digits in each dial). N = 4 (Number of circular dials). D
 * = Number of deadends.
 */
class Solution2 {
    public int openLock(String[] deadends, String target) {
        if (target == null || target.length() != 4) {
            return -1;
        }

        HashSet<String> dead = new HashSet<>();
        if (deadends != null) {
            for (String d : deadends) {
                dead.add(d);
            }
        }

        String start = "0000";
        if (dead.contains(start) || dead.contains(target)) {
            return -1;
        }
        if (target.equals(start)) {
            return 0;
        }

        HashSet<String> beginSet = new HashSet<>();
        HashSet<String> endSet = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        beginSet.add(start);
        endSet.add(target);
        visited.add(start);
        visited.add(target);

        int turns = 0;

        while (!beginSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                HashSet<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }

            turns++;
            HashSet<String> tempSet = new HashSet<>();
            for (String comb : beginSet) {
                for (String n : getNeigh(comb)) {
                    if (dead.contains(n)) {
                        continue;
                    }
                    if (endSet.contains(n)) {
                        return turns;
                    }
                    if (!visited.contains(n)) {
                        visited.add(n);
                        tempSet.add(n);
                    }
                }
            }

            beginSet = tempSet;
        }

        return -1;
    }

    private HashSet<String> getNeigh(String comb) {
        HashSet<String> set = new HashSet<>();
        char[] arr = comb.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            int num = c - '0';

            for (int k = num - 1; k <= num + 1; k += 2) {
                if (k == -1) {
                    arr[i] = '9';
                } else if (k == 10) {
                    arr[i] = '0';
                } else {
                    arr[i] = (char) (k + '0');
                }
                set.add(new String(arr));
            }
            arr[i] = c;
        }

        return set;
    }
}