// LeetCode Question URL: https://leetcode.com/problems/trapping-rain-water/
// LeetCode Discuss URL:

/**
 * Two Pointers Approach
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/trapping-rain-water/discuss/17357/Sharing-my-simple-c++-code:-O(n)-time-O(1)-space
 * 2) https://leetcode.com/articles/trapping-rain-water/#approach-4-using-2-pointers
 * 3) https://leetcode.com/problems/trapping-rain-water/solution/231601
 * </pre>
 *
 * Time complexity: O(N). Single Iteration
 *
 * Space Complexity: O(1)
 *
 * N = Length of input height array.
 */
class Solution1 {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int waterTrapped = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                int leftBoundary = height[left++];
                while (left < right && height[left] <= leftBoundary) {
                    waterTrapped += leftBoundary - height[left++];
                }
            } else {
                int rightBoundary = height[right--];
                while (left < right && height[right] <= rightBoundary) {
                    waterTrapped += rightBoundary - height[right--];
                }
            }
        }

        return waterTrapped;
    }
}

class Solution2 {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int minHeight = Math.min(height[left], height[right]);
        int waterTrapped = 0;

        while (left < right) {
            // minHeight = Math.min(height[left], height[right]);
            while (left < right && height[left] <= height[right]) {
                if (minHeight >= height[left]) {
                    waterTrapped += minHeight - height[left];
                } else {
                    minHeight = height[left];
                }
                left++;
            }

            // minHeight = Math.min(height[left], height[right]);
            while (left < right && height[left] > height[right]) {
                if (minHeight >= height[right]) {
                    waterTrapped += minHeight - height[right];
                } else {
                    minHeight = height[right];
                }
                right--;
            }
        }

        return waterTrapped;
    }
}

class Solution3 {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int waterTrapped = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                waterTrapped += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                waterTrapped += rightMax - height[right];
                right--;
            }
        }

        return waterTrapped;
    }
}
