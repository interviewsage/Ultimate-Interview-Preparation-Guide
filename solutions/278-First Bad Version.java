// LeetCode Question URL: https://leetcode.com/problems/first-bad-version/
// LeetCode Discuss URL: https://leetcode.com/problems/first-bad-version/discuss/1555825/Java-TC:-O(logN)-or-SC:-O(1)-or-Simple-Binary-Search-w-minimum-API-calls

import java.util.*;

// The isBadVersion API is defined in the parent class VersionControl.
// boolean isBadVersion(int version);

/**
 * Binary Search
 *
 * Note on Binary Search:
 * https://leetcode.com/problems/search-insert-position/discuss/249092/Come-on-forget-the-binary-search-patterntemplate!-Try-understand-it!
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(1)
 *
 * N = Number of versions.
 */
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input in invalid");
        }
        if (isBadVersion(1)) {
            return 1;
        }
        if (n == 1 || !isBadVersion(n)) {
            throw new NoSuchElementException("No bad version found");
        }

        // first version is already solved. Thus we can start from 2.
        int start = 2;
        int end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}
