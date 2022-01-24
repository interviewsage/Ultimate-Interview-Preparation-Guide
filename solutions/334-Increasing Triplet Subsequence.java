// LeetCode Question URL: https://leetcode.com/problems/increasing-triplet-subsequence/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/increasing-triplet-subsequence/discuss/79004/Concise-Java-solution-with-comments.
 * 2) https://leetcode.com/problems/increasing-triplet-subsequence/discuss/79004/Concise-Java-solution-with-comments./83886
 * 3) https://leetcode.com/problems/increasing-triplet-subsequence/discuss/79004/Concise-Java-solution-with-comments./247643
 * 4) https://leetcode.com/problems/increasing-triplet-subsequence/solution/
 * 5) https://leetcode.com/problems/increasing-triplet-subsequence/solution/644547
 * </pre>
 *
 * Example: [4,6,2,7] -> result is true.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n <= first) {
                // update first if num is smaller than both first & second.
                first = n;
            } else if (n <= second) {
                // update second only if num is greater than first but smaller than second.
                second = n;
            } else {
                // return true if found a number bigger than both first & second.
                return true;
            }
        }

        return false;
    }
}

class Solution2 {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        List<Integer> increasingSeq = new ArrayList<>();
        increasingSeq.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int start = 0;
            int end = increasingSeq.size();

            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[i] > increasingSeq.get(mid)) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

            if (start == increasingSeq.size()) {
                if (increasingSeq.size() == 2) {
                    return true;
                }
                increasingSeq.add(nums[i]);
            } else {
                increasingSeq.set(start, nums[i]);
            }
        }

        return false;
    }
}