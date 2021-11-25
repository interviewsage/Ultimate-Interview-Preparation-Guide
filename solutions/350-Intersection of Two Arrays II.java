// LeetCode Question URL: https://leetcode.com/problems/intersection-of-two-arrays-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Hashmap to store the count of each number in small array. Then find the
 * matching numbers in seconds array.
 *
 * <pre>
 * Time Complexity: O(M + N + min(M, N))
 *
 * Space Complexity:
 * 1. O(2 * min(UniqueM, UniqueN)) --> Space taken by HashMap
 * 2. O(min(M, N)) --> Space taken by result list.
 * Total Space Complexity = O(min(UniqueM, UniqueN) + min(M, N))
 * </pre>
 *
 * M = Length of nums1. N = Length of nums2.
 */
class Solution1 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int n : nums1) {
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        ArrayList<Integer> resultList = new ArrayList<>();
        for (int n : nums2) {
            Integer count = countMap.get(n);
            if (count == null) {
                continue;
            }
            resultList.add(n);
            if (count > 1) {
                countMap.put(n, count - 1);
            } else {
                countMap.remove(n);
            }
        }

        int[] result = new int[resultList.size()];
        int i = 0;
        for (int n : resultList) {
            result[i++] = n;
        }

        return result;
    }
}

/**
 * Sort both arrays and compare the values.
 *
 * Time Complexity: O(M*logM + N*logN + M+N)
 *
 * Space Complexity: O(M + N) (Required for sorting)
 *
 * M = Length of nums1. N = Length of nums2.
 */
class Solution2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        ArrayList<Integer> resultList = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                resultList.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] result = new int[resultList.size()];
        int k = 0;
        for (int n : resultList) {
            result[k++] = n;
        }

        return result;
    }
}

/**
 * Both arrays are sorted. One array is very long as compared to other array.
 *
 * <pre>
 * Time Complexity:
 * 1. O(M*logM + N*logN) -> For sorting. This is not required if the input is already sorted.
 * 2. O(min(M,N) * 2 * log(max(M,N)) + min(M, N)) -> This total complexity of while loop.
 *                                                   This includes binary search on longer array and
 *                                                   then adding the intersection numbers in the result list
 * 3. O(min(M, N)) -> To create the result array.
 * 4. Total time complexity: O(min(M,N) * 2 * log(max(M,N)) + 2*min(M, N))
 * </pre>
 *
 * Space Complexity: O(min(M, N))
 *
 * M = Length of nums1. N = Length of nums2.
 */
class Solution3 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> resultList = new ArrayList<>();

        int len1 = nums1.length;
        int i = 0;
        int nums2Start = 0;

        while (i < len1 && nums2Start < nums2.length) {
            int n = nums1[i++];
            int count = 1;
            while (i < len1 && nums1[i] == n) {
                i++;
                count++;
            }

            int[] range = getRange(nums2, n, nums2Start);
            nums2Start = range[1] + 1;
            if (nums2[range[0]] != n) {
                continue;
            }

            int l = Math.min(count, range[1] - range[0] + 1);
            for (int j = 0; j < l; j++) {
                resultList.add(n);
            }
        }

        int[] result = new int[resultList.size()];
        int k = 0;
        for (int n : resultList) {
            result[k++] = n;
        }
        return result;
    }

    private int[] getRange(int[] nums, int target, int start) {
        int[] range = new int[2];
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        range[0] = start;

        if (nums[start] != target) {
            // Here we are sending the new start so that we start from the next possible
            // value.
            // For example, [4, 4, 4, 4, 8, 8, 8, 8] and target is 6, In this case its
            // beneficial to send the end as 3, so that we can start from index 4.
            // Also, for the case where input is [4, 4, 4, 4] and target is 6, In this case
            // its beneficial to send 3, so that we will set the next start to 4, which will
            // break the while loop.
            range[1] = nums[start] < target ? start : start - 1;
            return range;
        }

        end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start + 1) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }

        range[1] = start;
        return range;
    }
}

/**
 * Sort both arrays. Find range in both smaller and larger array. This solution
 * can be used if both arrays are sorted and one array is very small.
 *
 * Time Complexity: O(M*2*logN) = O(M*logN)
 *
 * Space Complexity: O(M)
 *
 * M = Length of nums1. N = Length of nums2.
 */
class Solution4 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> list = new ArrayList<>();

        int start = 0;

        for (int i = 0; i < nums1.length; i++) {
            if (start >= nums2.length) {
                break;
            }

            int cnt = 1;
            int n = nums1[i];
            while (i < nums1.length - 1 && nums1[i + 1] == n) {
                cnt++;
                i++;
            }

            int[] range = searchRange(nums2, n, start);
            if (range[0] == -1 || range[1] == -1) {
                continue;
            }
            start = range[1] + 1;

            int l = Math.min(cnt, range[1] - range[0] + 1);
            for (int j = 0; j < l; j++) {
                list.add(n);
            }
        }

        int[] result = new int[list.size()];
        int i = 0;
        for (int n : list) {
            result[i++] = n;
        }

        return result;
    }

    private int[] searchRange(int[] nums, int target, int start) {
        if (nums == null || nums.length == 0 || start >= nums.length) {
            return new int[] { -1, -1 };
        }

        int[] result = new int[2];

        int end = nums.length - 1;

        // Find starting point
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (target <= nums[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        if (nums[start] != target) {
            return new int[] { -1, -1 };
        }

        result[0] = start;

        end = nums.length - 1;

        // Find ending point
        while (start < end) {
            int mid = start + (end - start + 1) / 2;
            if (target >= nums[mid]) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        result[1] = start;

        return result;
    }

}

/**
 * Follow Up 3:
 *
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/82243/Solution-to-3rd-follow-up-question
 *
 * What if elements of nums2 are stored on disk, and the memory is limited such
 * that you cannot load all elements into the memory at once?
 *
 * If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap,
 * read chunks of array that fit into the memory, and record the intersections.
 *
 * If both nums1 and nums2 are so huge that neither fit into the memory, sort
 * them individually (external sort), then read 2 elements from each array at a
 * time in memory, record intersections.
 *
 * ----
 *
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/82243/Solution-to-3rd-follow-up-question/86588
 *
 * Thanks for the solution. I think the second part of the solution is
 * impractical, if you read 2 elements at a time, this procedure will take
 * forever. In principle, we want minimize the number of disk access during the
 * run-time.
 *
 * An improvement can be sort them using external sort, read (let's say) 2G of
 * each into memory and then using the 2 pointer technique, then read 2G more
 * from the array that has been exhausted. Repeat this until no more data to
 * read from disk.
 *
 * But I am not sure this solution is good enough for an interview setting.
 * Maybe the interviewer is expecting some solution using Map-Reduce paradigm.
 *
 * ----
 *
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/82243/Solution-to-3rd-follow-up-question/86577
 *
 * I think the goal of this question is to test whether the interview
 * understands some of the data engineering techniques. From a data engineer's
 * perspective, basically there are three ideas to solve the question:
 *
 * Store the two strings in distributed system(whether self designed or not),
 * then using MapReduce technique to solve the problem;
 *
 * Processing the Strings by chunk, which fits the memory, then deal with each
 * chunk of data at a time;
 *
 * Processing the Strings by streaming, then check.
 *
 * ---
 *
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/1468295/Python-2-approaches-and-3-Follow-up-Questions-Clean-and-Concise
 *
 * ✔️ Follow-up Question 3: What if elements of nums2 are stored on disk, and
 * the memory is limited such that you cannot load all elements into the memory
 * at once?
 *
 * <pre>
 * If nums1 fits into the memory, we can use Approach 1 which stores all elements of nums1 in the HashMap. Then, we can sequentially load and process nums2.
If neither nums1 nor nums2 fits into the memory, we split the numeric range into numeric sub-ranges that fit into the memory.
We modify Approach 1 to count only elements which belong to the given numeric sub-range.
We process each numeric sub-ranges one by one, util we process all numeric sub-ranges.
For example:
Input constraint:
1 <= nums1.length, nums2.length <= 10^10.
0 <= nums1[i], nums2[i] < 10^5
Our memory can store up to 1000 elements.
Then we split numeric range into numeric sub-ranges [0...999], [1000...1999], ..., [99000...99999], then call Approach 1 to process 100 numeric sub-ranges.
 * </pre>
 *
 * ---
 *
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/solution/
 *
 * <pre>
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

If nums1 fits into the memory, we can use Approach 1 to collect counts for nums1 into a hash map. Then, we can sequentially load and process nums2.

If neither of the arrays fit into the memory, we can apply some partial processing strategies:

Split the numeric range into subranges that fits into the memory. Modify Approach 1 to collect counts only within a given subrange, and call the method multiple times (for each subrange).

Use an external sort for both arrays. Modify Approach 2 to load and process arrays sequentially.
 * </pre>
 */
