// LeetCode URL: https://leetcode.com/problems/dot-product-of-two-sparse-vectors/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Refer to this discuss post: https://leetcode.com/problems/dot-product-of-two-sparse-vectors/discuss/1522271/Java-O(n)-solution-using-Two-Pointers-with-detailed-explanation-and-Follow-up
 *
 * Why HashMap is not good.
 */

/**
 * Using HashMap to store the parse vector
 *
 * <pre>
 * Time Complexity:
 * Constructor: O(Size of the vector)
 * dotProduct: O(min(NumOfNonZerosV1, NumOfNonZerosV2))
 * </pre>
 *
 * Space Complexity: O(NumOfNonZeros * 2)
 */
class SparseVector1 {

    int size;
    HashMap<Integer, Integer> vector;

    SparseVector1(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        this.size = nums.length;
        this.vector = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                this.vector.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector1 vec) {
        if (vec == null || this.size != vec.size) {
            throw new IllegalArgumentException("Input vector is invalid");
        }

        if (this.vector.size() > vec.vector.size()) {
            return vec.dotProduct(this);
        }

        int result = 0;
        for (int i : this.vector.keySet()) {
            Integer val = vec.vector.get(i);
            if (val == null) {
                continue;
            }
            result += val * this.vector.get(i);
        }

        return result;
    }
}

/**
 * Using 2 lists. This solution will work better if the number of non-zero
 * elements is almost similar in 2 vectors.
 *
 * <pre>
 * Time Complexity:
 * Constructor: O(Size of the vector)
 * dotProduct: O(NumOfNonZerosV1 + NumOfNonZerosV2)
 * </pre>
 *
 * Space Complexity: O(NumOfNonZeros * 2)
 */
class SparseVector2 {

    int size;
    List<Integer> indexes;
    List<Integer> values;

    SparseVector2(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        this.size = nums.length;
        this.indexes = new ArrayList<>();
        this.values = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                this.indexes.add(i);
                this.values.add(nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector2 vec) {
        if (vec == null || this.size != vec.size) {
            throw new IllegalArgumentException("Input vector is invalid");
        }

        int result = 0;
        int i = 0;
        int j = 0;
        while (i < this.indexes.size() && j < vec.indexes.size()) {
            if (this.indexes.get(i).equals(vec.indexes.get(j))) {
                result += this.values.get(i) * vec.values.get(j);
                i++;
                j++;
            } else if (this.indexes.get(i) < vec.indexes.get(j)) {
                i++;
            } else {
                j++;
            }
        }

        return result;
    }
}

/**
 * Using 2 lists + Binary Search. One vector is sparse, but other is not.
 *
 * <pre>
 * Time Complexity:
 * Constructor: O(Size of the vector)
 * dotProduct: O(min(NumOfNonZerosV1, NumOfNonZerosV2) * log(max(NumOfNonZerosV1, NumOfNonZerosV2)))
 * </pre>
 *
 * Space Complexity: O(NumOfNonZeros * 2)
 */
class SparseVector3 {

    int size;
    List<Integer> indexes;
    List<Integer> values;

    SparseVector3(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        this.size = nums.length;
        this.indexes = new ArrayList<>();
        this.values = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                this.indexes.add(i);
                this.values.add(nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector3 vec) {
        if (vec == null || this.size != vec.size) {
            throw new IllegalArgumentException("Input vector is invalid");
        }

        if (this.indexes.size() > vec.indexes.size()) {
            return vec.dotProduct(this);
        }

        int result = 0;
        int nextStartForBinarySearch = 0;

        for (int i = 0; i < this.indexes.size(); i++) {
            int vecIdx = binarySearch(vec.indexes, this.indexes.get(i), nextStartForBinarySearch);
            // int vecIdx = Collections.binarySearch(vec.indexes, this.indexes.get(i));
            if (vecIdx >= 0) {
                nextStartForBinarySearch = vecIdx + 1;
                result += this.values.get(i) * vec.values.get(vecIdx);
            } else {
                nextStartForBinarySearch = -vecIdx - 1;
            }
        }

        return result;
    }

    private int binarySearch(List<Integer> arr, int target, int start) {
        int end = arr.size() - 1;

        while (start < end) {
            int mid = start + (end - start + 1) / 2;
            if (arr.get(mid) > target) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }

        return start <= end && arr.get(start) == target ? start : -start - 1;
    }
}

/**
 * Using 2 lists + Binary Search. One vector is sparse, but other is not.
 *
 * <pre>
 * Time Complexity:
 * Constructor: O(Size of the vector)
 * dotProduct: O(min(NumOfNonZerosV1, NumOfNonZerosV2) * log(max(NumOfNonZerosV1, NumOfNonZerosV2)))
 * </pre>
 *
 * Space Complexity: O(NumOfNonZeros * 2)
 */
class SparseVector4 {

    int size;
    List<int[]> idxList;

    SparseVector4(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input vector is null");
        }

        this.size = nums.length;
        this.idxList = new ArrayList<>();

        for (int i = 0; i < this.size; i++) {
            if (nums[i] != 0) {
                idxList.add(new int[] { i, nums[i] });
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector4 vec) {
        if (vec == null || vec.size != this.size) {
            throw new IllegalArgumentException("Invalid input sparse vector");
        }

        if (this.idxList.size() > vec.idxList.size()) {
            return vec.dotProduct(this);
        }

        int res = 0;
        int nextIdxStart = 0;

        for (int[] idxVal : idxList) {
            int idx = idxVal[0];

            int start = nextIdxStart;
            int end = vec.idxList.size() - 1;

            while (start <= end) {
                int mid = start + (end - start) / 2;

                if (vec.idxList.get(mid)[0] == idx) {
                    res += vec.idxList.get(mid)[1] * idxVal[1];
                    nextIdxStart = mid + 1;
                    break;
                } else if (vec.idxList.get(mid)[0] < idx) {
                    nextIdxStart = mid + 1;
                    start = mid + 1;
                } else {
                    nextIdxStart = start;
                    end = mid - 1;
                }
            }
        }

        return res;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
