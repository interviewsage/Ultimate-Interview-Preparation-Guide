// LeetCode Question URL: https://leetcode.com/problems/rectangle-overlap/
// LeetCode Discuss URL:

/**
 * <pre>
 * Refer:
 * 1. https://www.dropbox.com/s/ta9aawvqji6plok/LC%20-%20223%20-%20Rectangle%20Area.pdf?dl=0
 * 2. https://leetcode.com/problems/rectangle-overlap/discuss/132340/C++JavaPython-1-line-Solution-1D-to-2D
 * 3. https://leetcode.com/problems/rectangle-overlap/solution/
 * </pre>
 *
 * Rectilinear polygon is a polygon with sides parallel to the axes of Cartesian
 * coordinates
 *
 * Time Complexity: O(1)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int cx1 = Math.max(rec1[0], rec2[0]);
        int cx2 = Math.min(rec1[2], rec2[2]);
        if (cx1 >= cx2) {
            return false;
        }

        int cy1 = Math.max(rec1[1], rec2[1]);
        int cy2 = Math.min(rec1[3], rec2[3]);
        if (cy1 >= cy2) {
            return false;
        }

        return true;
    }
}

class Solution2 {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return Math.max(rec1[0], rec2[0]) < Math.min(rec1[2], rec2[2])
                && Math.max(rec1[1], rec2[1]) < Math.min(rec1[3], rec2[3]);
    }
}

class Solution3 {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // check if either rectangle is actually a line
        if (rec1[0] == rec1[2] || rec1[1] == rec1[3] ||
                rec2[0] == rec2[2] || rec2[1] == rec2[3]) {
            // the line cannot have positive overlap
            return false;
        }

        return !(rec1[2] <= rec2[0] || // left
                rec1[3] <= rec2[1] || // bottom
                rec1[0] >= rec2[2] || // right
                rec1[1] >= rec2[3]); // top
    }
}
