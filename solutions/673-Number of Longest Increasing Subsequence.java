// LeetCode Question URL: https://leetcode.com/problems/number-of-longest-increasing-subsequence/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Dynamic Programming
 *
 * Refer:
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/discuss/107293
 *
 * Lengths[i] = Longest Increasing Subsequence known at i (including ith
 * element)
 *
 * Counts[i] = Count of longest Increasing Subsequence known at i (including ith
 * element)
 *
 * for nums[i] > nums[j] ... 0 <= j < i, if lengths[i] == lengths[j]+1 ... then
 * just add nums[i] to the subsequence ending at jth position. Thus, add the
 * counts of the subsequences with the same max length known at i.
 *
 * If lengths[i] < length[j] + 1.. then new max Length at i is found... thus
 * update the length and counts at i.
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input nums array.
 */
class Solution1 {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int len = nums.length;
        if (len <= 1) {
            return 1;
        }

        int[] lengthDP = new int[len];
        int[] countDP = new int[len];
        lengthDP[0] = 1;
        countDP[0] = 1;
        int maxLen = 1;
        int countOfMaxLen = 1;

        for (int i = 1; i < len; i++) {
            lengthDP[i] = 1;
            countDP[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (lengthDP[i] == lengthDP[j] + 1) {
                        // Just adding the counts of the subsequences with same max length known at i.
                        countDP[i] += countDP[j];
                    } else if (lengthDP[i] < lengthDP[j] + 1) {
                        // New max length at i is found.
                        lengthDP[i] = lengthDP[j] + 1;
                        countDP[i] = countDP[j];
                    }
                }
            }
            if (maxLen == lengthDP[i]) {
                countOfMaxLen += countDP[i];
            } else if (maxLen < lengthDP[i]) {
                maxLen = lengthDP[i];
                countOfMaxLen = countDP[i];
            }
        }

        return countOfMaxLen;
    }
}

/**
 * Using Binary Search
 *
 * Refer:
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/discuss/107295/9ms-C++-Explanation:-DP-+-Binary-search-+-prefix-sums-O(NlogN)-time-O(N)-space
 *
 * Time Complexity: O(N * log N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input nums array.
 */
class Solution2 {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int len = nums.length;
        if (len <= 1) {
            return 1;
        }

        List<List<int[]>> seq = new ArrayList<>();
        List<int[]> temp = new ArrayList<>();
        temp.add(new int[] { nums[0], 1 });
        seq.add(temp);

        for (int i = 1; i < len; i++) {
            int start = 0;
            int end = seq.size();
            while (start < end) {
                int mid = start + (end - start) / 2;
                List<int[]> midSeq = seq.get(mid);
                if (nums[i] > midSeq.get(midSeq.size() - 1)[0]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

            int prev = start - 1;
            int countSeq = 1;
            if (prev >= 0) {
                List<int[]> prevSeq = seq.get(prev);
                int s = 0;
                int e = prevSeq.size();
                while (s < e) {
                    int m = s + (e - s) / 2;
                    if (nums[i] > prevSeq.get(m)[0]) {
                        e = m;
                    } else {
                        s = m + 1;
                    }
                }

                if (s == 0) {
                    countSeq = prevSeq.get(prevSeq.size() - 1)[1];
                } else {
                    countSeq = prevSeq.get(prevSeq.size() - 1)[1] - prevSeq.get(s - 1)[1];
                }
            }

            if (start == seq.size()) {
                List<int[]> t = new ArrayList<>();
                t.add(new int[] { nums[i], countSeq });
                seq.add(t);
            } else {
                List<int[]> curSeq = seq.get(start);
                int curSize = curSeq.size();
                if (curSeq.get(curSize - 1)[0] == nums[i]) {
                    curSeq.get(curSize - 1)[1] += countSeq;
                } else {
                    curSeq.add(new int[] { nums[i], curSeq.get(curSize - 1)[1] + countSeq });
                }
            }
        }

        List<int[]> lastSeq = seq.get(seq.size() - 1);
        return lastSeq.get(lastSeq.size() - 1)[1];
    }
}
