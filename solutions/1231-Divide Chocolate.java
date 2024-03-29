// LeetCode Question URL: https://leetcode.com/problems/divide-chocolate/
// LeetCode Discuss URL: https://leetcode.com/problems/divide-chocolate/discuss/1554392/Java-Simple-Binary-Search-constant-space-solution-w-detailed-explanation

/**
 * Binary Search
 *
 * Search space will start from the minimum sweetness and end at maximum
 * possible sweetness of a piece.
 *
 * <pre>
 * Time Complexity:
 * 1. O(N) --> To find the min and sum.
 * 2. O(N * log(Sum/(K+1) - Min)) --> Time taken by binary search.
 * 3. Thus worst case complexity = O(N + N * log(Sum/(K+1) - Min))
 *
 * Where, Sum = Sum of sweetness of all chunks. Min = Min sweetness of all chunks.
 * </pre>
 *
 * Space Complexity: O(1)
 *
 * N = Length of sweetness array. K = Input number of friends.
 */
class Solution {
    public int maximizeSweetness(int[] sweetness, int k) {
        // Invalid Input
        if (sweetness == null || k < 0 || sweetness.length <= k) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = sweetness.length;

        // Find minimum sweetness and sum of all sweetness. This will help us in
        // defining the search space of the binary search
        int sum = sweetness[0];
        int min = sweetness[0];
        for (int i = 1; i < len; i++) {
            sum += sweetness[i];
            min = Math.min(min, sweetness[i]);
        }

        // If number of friends is zero, then return sum. You can eat the whole
        // chocolate bar.
        if (k == 0) {
            return sum;
        }

        // Length is same as the number of pieces required, then each piece will have
        // one chunk. You will get the chuck with minimum sweetness.
        if (k + 1 == len) {
            return min;
        }

        // Search space will start from the minimum sweetness and end at maximum
        // possible sweetness of a piece.
        int start = min;
        int end = sum / (k + 1); // This is maximum possible sweetness of a piece if the bar is divided into k+1
                                 // pieces.
        while (start < end) {
            // Here +1 is required to avoid Infinite Loop
            int mid = start + (end - start + 1) / 2;
            if (canCutAtLeastKPieces(sweetness, k + 1, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    /**
     * Checking if the chocolate bar can be divided into k+1 pieces, where each
     * piece will have at least minDesiredSweetness.
     *
     * canDivideChocolate() function will scan the whole array each time. Thus it
     * will take O(N) time.
     */
    private boolean canCutAtLeastKPieces(int[] sweetness, int k, int minSum) {
        int sum = 0;
        for (int s : sweetness) {
            sum += s;
            // As soon as the minimum desired sweetness is met, cut the piece and start
            // finding the next piece.
            if (sum >= minSum) {
                if (--k == 0) {
                    return true;
                }
                sum = 0;
            }
        }
        return false;
    }
}
