// LeetCode Question URL: https://leetcode.com/problems/subarray-sums-divisible-by-k/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/subarray-sums-divisible-by-k/discuss/413234/DETAILED-WHITEBOARD!-BEATS-100-(Do-you-really-want-to-understand-It)
 * 2. https://leetcode.com/problems/subarray-sums-divisible-by-k/discuss/217980/Java-O(N)-with-HashMap-and-prefix-Sum
 * 3. https://leetcode.com/problems/subarray-sums-divisible-by-k/discuss/217985/JavaC++Python-Prefix-Sum
 * </pre>
 *
 * Use hashmap to save the remainder of the prefix sum at each index. If the
 * same remainder is found in the map, then we have found the subarray that sums
 * up to a multiple of k.
 *
 * Also convert negative remainder to positive by adding K. This will not change
 * the answer. If K = 5, and remainder was -1, then if we find 4 in the map,
 * then we have a subarray of sum 5.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(min(N, K)) when K != 0 because remainder can between 0 &
 * K-1. When K == 0, then SC=O(N)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public int subarraysDivByK(int[] nums, int k) {
        if (nums == null || k == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (k == -1 || k == 1) {
            return len * (len + 1) / 2;
        }
        if (len == 1) {
            return nums[0] % k == 0 ? 1 : 0;
        }

        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, 1);
        int result = 0;
        int prefixSumRem = 0;

        for (int n : nums) {
            // prefixSumRem = (prefixSumRem + n % k + k) % k;
            prefixSumRem = (prefixSumRem + n % k) % k;
            if ((k < 0 && prefixSumRem > 0) || (k > 0 && prefixSumRem < 0)) {
                prefixSumRem += k;
            }

            int count = remainderMap.getOrDefault(prefixSumRem, 0);
            result += count;
            remainderMap.put(prefixSumRem, count + 1);
        }

        return result;
    }
}

class Solution2 {
    public int subarraysDivByK(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0;
        int result = 0;

        for (int n : A) {
            sum += n;
            if (K != 0) {
                sum %= K;
                if (sum < 0) {
                    sum += K;
                }
            }

            int cnt = map.getOrDefault(sum, 0);
            result += cnt;

            map.put(sum, cnt + 1);
        }

        return result;
    }
}
