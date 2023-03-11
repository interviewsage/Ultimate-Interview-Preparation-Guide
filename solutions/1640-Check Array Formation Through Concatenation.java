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
        if (arr == null || pieces == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int arrLen = arr.length;
        int numPieces = pieces.length;
        if (arrLen == 0 && numPieces == 0) {
            return true;
        }
        if (numPieces == 0 || numPieces > arrLen) {
            return false;
        }

        Map<Integer, Integer> piecesMap = new HashMap<>();
        for (int i = 0; i < numPieces; i++) {
            piecesMap.put(pieces[i][0], i);
        }

        int i = 0;
        while (i < arrLen) {
            Integer pieceIdx = piecesMap.remove(arr[i]);
            if (pieceIdx == null || pieces[pieceIdx].length > arrLen - i) {
                return false;
            }

            for (int p : pieces[pieceIdx]) {
                if (arr[i++] != p) {
                    return false;
                }
            }
            // i++;
            // int j = 1;
            // while (j < pieces[pieceIdx].length) {
            // if (arr[i++] != pieces[pieceIdx][j++]) {
            // return false;
            // }
            // }
        }

        // Ask the interviewer if we need to use all pieces.
        return piecesMap.size() == 0;
        // return true;
    }
}
