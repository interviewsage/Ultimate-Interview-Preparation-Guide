// LeetCode Question URL: https://leetcode.com/problems/subsets-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/subsets-ii/discuss/1549662/Java-or-TC:-O(N*2N)-or-SC:-O(UniqueNums)-or-Space-Optimized-Iterative-and-Backtracking-solutions

import java.util.*;

/**
 * Backtracking (Recursion)
 *
 * <pre>
 * S(n) = (0 × (n C 0) + 1 × (n C 1) + 2 × (n C 2) + … + n × (n C n))
 * Note that (n C k) = (n C n-k). Therefore:
 * S(n) = 0 × (n C n) + 1 × (n C n-1) + 2 × (n C n-2) + … + n × (n C 0)
 * If we add these two together, we get
 * 2S(n) = n × (n C 0) + n × (n C 1) + … + n × (n C n)
 *       = n × (n C 0 + n C 1 + … + n C n)
 *
 * As per binomial theorem, (n C 0 + n C 1 + … + n C n) = 2^n,
 * Here we have 2 options for each number. Either we can include the number or exclude the number.
 * Thus (n C 0 + n C 1 + … + n C n) = 2^n
 *
 * Thus, :
 * 2*S(n) = n * 2^n => S(n) = n * 2^(n-1)
 *
 * Refer https://stackoverflow.com/a/20711498
 * </pre>
 *
 * Time Complexity: O(N + S(N) + n C 0) = O(N + N * 2^(N-1) + 1) = O(N * 2^N)
 *
 * Space Complexity: O(N) (Excluding the result space)
 *
 * N = Length of input nums array
 */
class Solution1 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n : nums) {
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        List<List<Integer>> result = new ArrayList<>();
        subsetsWithDup(countMap, new ArrayList<>(countMap.keySet()), 0, new ArrayList<>(), result);
        return result;
    }

    private void subsetsWithDup(Map<Integer, Integer> countMap, List<Integer> uniqueNums, int start, List<Integer> cur,
            List<List<Integer>> result) {
        result.add(new ArrayList<>(cur));

        for (int i = start; i < uniqueNums.size(); i++) {
            int num = uniqueNums.get(i);
            int count = countMap.get(num);
            cur.add(num);
            countMap.put(num, count - 1);

            subsetsWithDup(countMap, uniqueNums, (count == 1 ? i + 1 : i), cur, result);

            countMap.put(num, count);
            cur.remove(cur.size() - 1);
        }
    }
}

/**
 * Backtracking (Recursion) - Using Sort
 *
 * Time Complexity: O(N*logN + N * 2 ^ N) Refer to above explanation
 *
 * Space Complexity: O(Unique Nums) (Excluding the result space)
 *
 * N = Length of input nums array
 */
class Solution2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        subsetsWithDup(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void subsetsWithDup(int[] nums, int start, List<Integer> cur, List<List<Integer>> result) {
        result.add(new ArrayList<>(cur));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i - 1] == nums[i]) {
                continue;
            }

            cur.add(nums[i]);
            subsetsWithDup(nums, i + 1, cur, result);
            cur.remove(cur.size() - 1);
        }
    }
}

/**
 * Iterative Solution
 *
 * Time Complexity: O(N * 2 ^ N) Refer to above explanation
 *
 * Space Complexity: O(Unique Nums) (Excluding the result space)
 *
 * N = Length of input nums array
 */
class Solution3 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n : nums) {
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for (int n : countMap.keySet()) {
            int size = result.size();
            int count = countMap.get(n);

            for (int i = 0; i < size; i++) {
                List<Integer> cur = result.get(i);
                for (int j = 0; j < count; j++) {
                    List<Integer> temp = new ArrayList<>(cur);
                    temp.add(n);
                    result.add(temp);
                    cur = temp;
                }
            }
        }

        return result;
    }
}

/**
 * Iterative Solution (Using Sort)
 *
 * Time Complexity: O(N*logN + N * 2 ^ N) Refer to above explanation
 *
 * Space Complexity: O(Unique Nums) (Excluding the result space)
 *
 * N = Length of input nums array
 */
class Solution4 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        int preLevelStartIdx = 0;

        for (int i = 0; i < nums.length; i++) {
            int size = result.size();
            int startIdx = 0;
            if (i > 0 && nums[i - 1] == nums[i]) {
                startIdx = preLevelStartIdx;
            }

            for (int j = startIdx; j < size; j++) {
                List<Integer> cur = new ArrayList<>(result.get(j));
                cur.add(nums[i]);
                result.add(cur);
            }

            preLevelStartIdx = size;
        }

        return result;
    }
}
