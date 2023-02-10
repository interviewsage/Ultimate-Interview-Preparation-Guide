// LeetCode Question URL: https://leetcode.com/problems/rectangle-area/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://www.dropbox.com/s/ta9aawvqji6plok/LC%20-%20223%20-%20Rectangle%20Area.pdf?dl=0
 *
 * Rectilinear polygon is a polygon with sides parallel to the axes of Cartesian
 * coordinates
 *
 * Area of a rectilinear rectangle = (x1 - x2) * (y1 - y2)
 *
 * overlapBottomLeftX (x1) = max(A, E); overlapTopRightX (x2) = max(min(C, G),
 * x1)
 *
 * overlapBottomLeftY (y1) = max(B, F); overlapTopRightY (y2) = max(min(D, H),
 * y1)
 *
 * If there is no overlap, maximum of bottom left coordinates will be greater
 * than minimum of top right coordinates. Thus, x1 == x2 or y1 == y2 ==> Side
 * length = 0.
 *
 * Time Complexity: O(1)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area = rectangleArea(ax1, ay1, ax2, ay2) + rectangleArea(bx1, by1, bx2, by2);

        int cx1 = Math.max(ax1, bx1);
        int cx2 = Math.min(ax2, bx2);
        if (cx1 >= cx2) {
            return area;
        }

        int cy1 = Math.max(ay1, by1);
        int cy2 = Math.min(ay2, by2);
        if (cy1 >= cy2) {
            return area;
        }

        return area - rectangleArea(cx1, cy1, cx2, cy2);
    }

    private int rectangleArea(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (y2 - y1);
    }
}

class Solution2 {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int rect1Area = (C - A) * (D - B);
        int rect2Area = (G - E) * (H - F);

        int overlapBottomLeftX = Math.max(A, E);
        int overlapBottomLeftY = Math.max(B, F);
        int overlapTopRightX = Math.max(Math.min(C, G), overlapBottomLeftX);
        int overlapTopRightY = Math.max(Math.min(D, H), overlapBottomLeftY);

        int overlapArea = (overlapTopRightX - overlapBottomLeftX) * (overlapTopRightY - overlapBottomLeftY);

        return rect1Area + rect2Area - overlapArea;
    }
}
