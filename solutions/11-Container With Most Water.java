// LeetCode Question URL: https://leetcode.com/problems/container-with-most-water/
// LeetCode Discuss URL:

/**
 * Refer for Proof:
 * https://leetcode.com/problems/container-with-most-water/discuss/6099/Yet-another-way-to-see-what-happens-in-the-O(n)-algorithm
 *
 * Better & Simpler Proof/Explanation:
 * We move the idex of the minimum height because, we have found the maximum
 * possible volume of water that can be stored at that minimum height.
 * Therefore, we can safely move to the next index as this height cannot yield
 * greater volume of water.
 *
 * Time Complexity: O(N). Whole height array will be scanned once
 *
 * Space Complexity: O(1)
 *
 * N = Length of height array.
 */
class Solution1 {
    public int maxArea(int[] height) {
        if (height == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = height.length;
        if (len <= 1) {
            return 0;
        }

        int l = 0;
        int r = len - 1;
        int maxWater = 0;

        while (l < r) {
            int dist = r - l;
            int minHeight = height[l];
            if (height[l] <= height[r]) {
                do {
                    l++;
                } while (l < r && height[l] <= minHeight);
            } else {
                minHeight = height[r];
                do {
                    r--;
                } while (l < r && height[r] <= minHeight);
            }
            maxWater = Math.max(maxWater, dist * minHeight);
        }

        return maxWater;
    }
}

class Solution2 {
    public int maxArea(int[] height) {
        if (height == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (height.length <= 1) {
            return 0;
        }

        int maxWater = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            if (height[left] < height[right]) {
                maxWater = Math.max(maxWater, height[left] * (right - left));
                left++;
            } else {
                maxWater = Math.max(maxWater, height[right] * (right - left));
                right--;
            }
        }

        return maxWater;
    }
}
