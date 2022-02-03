// LeetCode Question URL: https://leetcode.com/problems/h-index/
// LeetCode Discuss URL:

/**
 * Counting papers with same Citations
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input array (OR number of papers).
 */
class Solution1 {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int numPapers = citations.length;
        if (numPapers == 1) {
            return citations[0] >= 1 ? 1 : 0;
        }

        int[] counts = new int[numPapers + 1];
        for (int c : citations) {
            counts[Math.min(numPapers, c)]++;
        }

        // if (counts[0] == numPapers) {
        // return 0;
        // }

        int sum = 0;
        for (int i = numPapers; i >= 0; i--) {
            sum += counts[i];
            if (sum >= i) {
                /**
                 * Here count papers have at least i citations. Since we are going from the
                 * highest H-Index possible to zero, thus first match will give us the H-Index.
                 */
                return i;
            }
        }

        return 0;
    }
}

class Solution2 {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int maxHI = citations.length;
        int[] citationCount = new int[maxHI + 1];

        for (int c : citations) {
            if (c >= maxHI) {
                citationCount[maxHI]++;
            } else {
                citationCount[c]++;
            }
        }

        int count = 0;
        for (int i = maxHI; i >= 0; i--) {
            count += citationCount[i];
            if (count >= i) {
                /**
                 * Here count papers have at least i citations. Since we are going from the
                 * highest H-Index possible to zero, thus first match will give us the H-Index.
                 */
                return i;
            }
        }

        return 0;
    }
}