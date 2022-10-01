// LeetCode Question URL: https://leetcode.com/problems/kth-largest-element-in-an-array/

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/60294/Solution-explained
 *
 * Also Check top voted comments and its replies:
 * https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/60294/Solution-explained/61503
 */

/**
 * Using Quick Select algorithm. Same algorithm is used in Quick Sort.
 *
 * This use Random.nextInt() to randomize the pivot.
 *
 * Time Complexity: Best Case -> O(N). Worst Case -> O(N^2).
 *
 * Best Case: T(N) = T(N/2) + O(N) ==> T(N) = O(N)
 *
 * Worst Case: T(N) = T(N-1) + O(N) ==> T(N) = O(N^2)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    private static final Random RANDOM = new Random();

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        k = len - k;
        int start = 0;
        int end = len - 1;

        while (start < end) {
            int pivot = partition(nums, start, end);
            if (pivot == k) {
                return nums[pivot];
            }
            if (pivot < k) {
                start = pivot + 1;
            } else {
                end = pivot - 1;
            }
        }

        return nums[start];
    }

    private int partition(int[] nums, int start, int end) {
        int idx = start + RANDOM.nextInt(end - start + 1);
        swap(nums, start, idx);
        int insertPos = start;

        for (int i = start + 1; i <= end; i++) {
            if (nums[i] <= nums[start]) {
                insertPos++;
                swap(nums, i, insertPos);
            }
        }

        swap(nums, start, insertPos);
        return insertPos;
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}

/**
 * Using Quick Select algorithm. Same algorithm is used in Quick Sort.
 *
 * This does not shuffles the input. Thus worst case is not good.
 *
 * Time Complexity: Best Case -> O(N). Worst Case -> O(N^2).
 *
 * Best Case: T(N) = T(N/2) + O(N) ==> T(N) = O(N)
 *
 * Worst Case: T(N) = T(N-1) + O(N) ==> T(N) = O(N^2)
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public int findKthLargest(int[] nums, int k) throws IllegalArgumentException {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input nums array is null / empty");
        }
        if (k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Invalid k value");
        }

        int len = nums.length;
        int start = 0;
        int end = len - 1;
        k = len - k;

        while (start < end) {
            int pivot = partition(nums, start, end);
            if (k == pivot) {
                return nums[k];
            }
            if (k < pivot) {
                end = pivot - 1;
            } else {
                start = pivot + 1;
            }
        }

        return nums[start];
    }

    private int partition(int[] nums, int start, int end) {
        int insertPos = start;

        for (int i = start + 1; i <= end; i++) {
            if (nums[i] < nums[start]) {
                insertPos++;
                swap(nums, insertPos, i);
            }
        }

        swap(nums, start, insertPos);
        return insertPos;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/*
 * Using Quick Select algorithm. Same algorithm is used in Quick Sort.
 *
 * This algorithm shuffles the input array. It uses Fisher–Yates shuffle
 * algorithm (https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle)
 *
 * Time Complexity: Best Case -> O(N). Worst Case -> O(N^2).
 *
 * Best Case: T(N) = T(N/2) + O(N) ==> T(N) = O(N)
 *
 * Worst Case: T(N) = T(N-1) + O(N) ==> T(N) = O(N^2)
 *
 * Space Complexity: O(1)
 */
class Solution3 {
    private static final Random rand = new Random();

    public int findKthLargest(int[] nums, int k) throws IllegalArgumentException {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input nums array is null / empty");
        }
        if (k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Invalid k value");
        }

        shuffle(nums);

        int len = nums.length;
        int start = 0;
        int end = len - 1;
        k = len - k;

        while (start < end) {
            int pivot = partition(nums, start, end);
            if (k == pivot) {
                return nums[k];
            }
            if (k < pivot) {
                end = pivot - 1;
            } else {
                start = pivot + 1;
            }
        }

        return nums[start];
    }

    private void shuffle(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            int r = rand.nextInt(i + 1);
            swap(nums, r, i);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int insertPos = start;

        for (int i = start + 1; i <= end; i++) {
            if (nums[i] < nums[start]) {
                insertPos++;
                swap(nums, insertPos, i);
            }
        }

        swap(nums, start, insertPos);
        return insertPos;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/*
 * Using Priority Queue
 *
 * Time Complexity: O(N * log k)
 *
 * Space Complexity: O(k)
 *
 * N = number of elements in the input array
 */
class Solution4 {
    public int findKthLargest(int[] nums, int k) throws IllegalArgumentException {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input nums array is null / empty");
        }
        if (k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Invalid k value");
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);

            if (queue.size() > k) {
                queue.poll();
            }
        }

        return queue.peek();
    }
}

/*
 * Using Array Sort function
 *
 * Time Complexity: O(N * log N)
 *
 * Space Complexity: O(N) (Space taken by Arrays.sort function)
 *
 * N = number of elements in the input array
 */
class Solution5 {
    public int findKthLargest(int[] nums, int k) throws IllegalArgumentException {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input nums array is null / empty");
        }
        if (k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Invalid k value");
        }

        Arrays.sort(nums);

        return nums[nums.length - k];
    }
}