// LeetCode Question URL: https://leetcode.com/problems/check-array-formation-through-concatenation/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/problems/check-array-formation-through-concatenation/discuss/919790/C++-Track-First-Elements
 *
 * Time Complexity: O(P + N) ≈ O(N)
 *
 * Space Complexity: O(P) ≈ O(N)
 *
 * P = Number of pieces. N = Length of arr.
 */
class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        if (arr == null || pieces == null || pieces.length > arr.length) {
            return false;
        }

        int len = arr.length;
        int numPieces = pieces.length;

        if (len == 0 && numPieces == 0) {
            return true;
        }
        if (numPieces == 0) {
            return false;
        }

        Map<Integer, Integer> piecesMap = new HashMap<>();
        for (int i = 0; i < numPieces; i++) {
            piecesMap.put(pieces[i][0], i);
        }

        int i = 0;
        while (i < len) {
            Integer pieceIdx = piecesMap.remove(arr[i]);
            if (pieceIdx == null || pieces[pieceIdx].length > len - i) {
                return false;
            }

            i++;
            for (int j = 1; j < pieces[pieceIdx].length; j++) {
                if (arr[i++] != pieces[pieceIdx][j]) {
                    return false;
                }
            }

        }

        return piecesMap.size() == 0;
    }
}
