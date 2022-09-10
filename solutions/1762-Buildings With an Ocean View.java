// LeetCode URL: https://leetcode.com/problems/buildings-with-an-ocean-view/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Keep traversing from back and maintain the max height seen till now.
 * Everything time you encounter a building greater than max height,
 * then add the index to the head of linked list and update the max height.
 *
 * Return the reverse of LinkedList.
 *
 * Time Complexity: O(2 * N) = O(N)
 * Space Complexity: O(N)
 */
class Solution {
    public int[] findBuildings(int[] heights) {
        if (heights == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = heights.length;
        if (len <= 1) {
            return new int[len];
        }

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(len - 1);
        for (int i = len - 2; i >= 0; i--) {
            if (heights[i] > heights[stack.peek()]) {
                stack.push(i);
            }
        }

        int[] result = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }

        return result;
    }
}

/**
 * Using LinkedList
 */
class Solution2 {
    public int[] findBuildings(int[] heights) {
        if (heights == null || heights.length == 0) {
            return new int[0];
        }

        if (heights.length == 1) {
            return new int[] { 0 };
        }

        LinkedList<Integer> list = new LinkedList<>();

        int maxHeightTillNow = 0;

        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > maxHeightTillNow) {
                maxHeightTillNow = heights[i];
                list.offerFirst(i);
            }
        }

        int len = list.size();
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            result[i] = list.pollFirst();
        }

        return result;
    }
}
