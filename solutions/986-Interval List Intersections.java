// LeetCode Question URL: https://leetcode.com/problems/interval-list-intersections/

import java.util.ArrayList;
import java.util.List;

/**
 * Using modified Merge operation of Merge Sort
 *
 * Refer:
 * https://leetcode.com/problems/interval-list-intersections/discuss/231108/C++-O(n)-"merge-sort"
 *
 * Time Complexity: O(A + B)
 *
 * Space Complexity: O(A + B)
 *
 * A = Length of array A. B = Length of array B.
 */
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList == null || secondList == null) {
            throw new IllegalArgumentException("Invalid Input");
        }

        int len1 = firstList.length;
        int len2 = secondList.length;
        if (len1 == 0 || len2 == 0) {
            return new int[0][0];
        }

        int i = 0;
        int j = 0;
        List<int[]> resultList = new ArrayList<>();

        while (i < len1 && j < len2) {
            if (firstList[i][1] < secondList[j][0]) {
                // No overlap. Since A[i] < B[j].. then A[i] will be less than all other
                // intervals in B.
                i++;
                continue;
            }
            if (secondList[j][1] < firstList[i][0]) {
                // No overlap. Since B[j] < A[i].. then B[j] will be less than all other
                // intervals in A.
                j++;
                continue;
            }

            resultList.add(new int[] { Math.max(firstList[i][0], secondList[j][0]),
                    Math.min(firstList[i][1], secondList[j][1]) });

            // Increasing the index of the list which have been explored fully..
            if (firstList[i][1] == secondList[j][1]) {
                i++;
                j++;
            } else if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return resultList.toArray(new int[resultList.size()][]);
    }
}

// -----------------------------------------------------------------------------

// Definition for an interval.
class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

/**
 * Using modified Merge operation of Merge Sort
 *
 * Refer:
 * https://leetcode.com/problems/interval-list-intersections/discuss/231108/C++-O(n)-"merge-sort"
 *
 * Time Complexity: O(A + B)
 *
 * Space Complexity: O(A + B)
 *
 * A = Length of array A. B = Length of array B.
 */
class Solution2 {
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return new Interval[0];
        }

        List<Interval> result = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < A.length && j < B.length) {
            if (A[i].end < B[j].start) {
                i++;
            } else if (B[j].end < A[i].start) {
                j++;
            } else {
                result.add(new Interval(Math.max(A[i].start, B[j].start), Math.min(A[i].end, B[j].end)));
                if (A[i].end < B[j].end) {
                    i++;
                } else {
                    j++;
                }
            }
        }

        return result.toArray(new Interval[result.size()]);
    }
}