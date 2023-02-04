// LeetCode Question URL: https://leetcode.com/problems/4sum/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(NlogN + N^3)
 *
 * Space Complexity: O(N) -> Space taken by Arrays.sort()
 *
 * N = Length of input array.
 */
class Solution1 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums is null");
        }

        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len < 4) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < len - 3; i++) {
            long n1 = nums[i];
            if (n1 * 4 > target) {
                break;
            }
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            for (int j = i + 1; j < len - 2; j++) {
                long n2 = nums[j];
                if (n2 * 3 > target - n1) {
                    break;
                }
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }

                int start = j + 1;
                int end = len - 1;
                while (start < end) {
                    long sum = n1 + n2 + nums[start] + nums[end];
                    if (sum == target) {
                        result.add(List.of(nums[i], nums[j], nums[start], nums[end]));

                        while (start < end && nums[start] == nums[start + 1]) {
                            start++;
                        }
                        while (start < end && nums[end - 1] == nums[end]) {
                            end--;
                        }

                        start++;
                        end--;
                    } else if (sum < target) {
                        while (start < end && nums[start] == nums[start + 1]) {
                            start++;
                        }
                        start++;
                    } else {
                        while (start < end && nums[end - 1] == nums[end]) {
                            end--;
                        }
                        end--;
                    }
                }
            }
        }

        return result;
    }
}

/**
 * Using K-Sum
 *
 * Time Complexity: O(N^(K-1) + N log N)
 *
 * Space Complexity: O(K + K + Sorting) -> TempList + Recursion Stack + Sorting
 *
 * N = Length of input array.
 */
class Solution2 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return kSum(nums, target, 4);
    }

    public List<List<Integer>> kSum(int[] nums, int target, int k) {
        if (nums == null || k <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (len < k) {
            return result;
        }
        if (k == 1) {
            for (int n : nums) {
                if (n == target) {
                    result.add(List.of(n));
                    return result;
                }
            }
        }

        Arrays.sort(nums);

        kSumHelper(nums, target, 0, k, new ArrayList<>(), result);

        return result;
    }

    private void kSumHelper(int[] nums, long target, int start, int k, List<Integer> tempList,
            List<List<Integer>> result) {
        if (k == 2) {
            twoSumHelper(nums, target, start, tempList, result);
            return;
        }

        for (int i = start; i <= nums.length - k; i++) {
            if ((long) nums[i] * k > target) {
                break;
            }
            if (i > start && nums[i - 1] == nums[i]) {
                continue;
            }
            tempList.add(nums[i]);
            kSumHelper(nums, target - nums[i], i + 1, k - 1, tempList, result);
            tempList.remove(tempList.size() - 1);
        }
    }

    private void twoSumHelper(int[] nums, long target, int start, List<Integer> tempList, List<List<Integer>> result) {
        int end = nums.length - 1;

        while (start < end) {
            long sum = (long) nums[start] + nums[end];
            if (sum == target) {
                List<Integer> list = new ArrayList<>(tempList);
                list.add(nums[start]);
                list.add(nums[end]);
                result.add(list);

                while (start < end && nums[start] == nums[start + 1]) {
                    start++;
                }
                while (start < end && nums[end - 1] == nums[end]) {
                    end--;
                }

                start++;
                end--;
            } else if (sum < target) {
                while (start < end && nums[start] == nums[start + 1]) {
                    start++;
                }
                start++;
            } else {
                while (start < end && nums[end - 1] == nums[end]) {
                    end--;
                }
                end--;
            }
        }
    }
}
