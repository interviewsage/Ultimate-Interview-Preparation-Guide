// LeetCode Question URL: https://leetcode.com/problems/first-unique-number/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using LinkedHashSet and HashSet.
 *
 * <pre>
 * LinkedHashSet stores the Non Repeating Numbers. It also maintains the insertion order.
 * HashSet stores the Repeating Numbers.
 *
 * Time Complexity:
 * FirstUnique() Constructor --> O(K). Each number will be processed once. Where, K = Size of input array.
 * showFirstUnique() --> O(1).
 * add() --> O(1).
 *
 * Space Complexity:
 * Each individual functions need O(1) auxillary space.
 * Overall, we will save only one occurence of each number seen so far in both the sets.
 * Thus, Total Space Complexity is bounded by O(N). Where, N = All Numbers seen so far.
 * </pre>
 */
class FirstUnique1 {

    LinkedHashSet<Integer> nonRepeatingNums;
    HashSet<Integer> repeatingNums;

    public FirstUnique1(int[] nums) {
        nonRepeatingNums = new LinkedHashSet<>();
        repeatingNums = new HashSet<>();

        if (nums == null || nums.length == 0) {
            return;
        }
        // Adding each input number in the FirstUnique object
        for (int num : nums) {
            add(num);
        }
    }

    public int showFirstUnique() {
        return nonRepeatingNums.size() == 0 ? -1 : nonRepeatingNums.iterator().next();
    }

    public void add(int value) {
        if (repeatingNums.contains(value)) {
            // Number is a previously known repeating number.
            return;
        }
        if (!nonRepeatingNums.add(value)) {
            // Number has now become a repeating number, so remove it from nonRepeatingNums
            // Set and add it to repeatingNums set.
            nonRepeatingNums.remove(value);
            repeatingNums.add(value);
        }
    }
}

/**
 * Using DoublyLinkedList and HashMap.
 *
 * <pre>
 * DoublyLinkedList stores the Non Repeating Numbers. It also maintains the insertion order.
 * HashMap stores one occurence of each number seen so far. If the node is non repeating, it also saves the ListNode reference.
 *
 * Time Complexity:
 * FirstUnique() Constructor --> O(K). Each number will be processed once. Where, K = Size of input array.
 * showFirstUnique() --> O(1).
 * add() --> O(1).
 *
 * Space Complexity:
 * Each individual functions need O(1) auxillary space.
 * Total Space Complexity is bounded by O(N). Where, N = All Numbers seen so far.
 * </pre>
 */
class FirstUnique2 {

    public class ListNode {
        int value;
        ListNode prev;
        ListNode next;

        public ListNode(int val) {
            this.value = val;
        }
    }

    HashMap<Integer, ListNode> map;
    ListNode head;
    ListNode tail;

    public FirstUnique2(int[] nums) {
        map = new HashMap<>();
        head = new ListNode(-1);
        tail = new ListNode(-1);
        head.next = tail;
        tail.prev = head;

        if (nums == null || nums.length == 0) {
            return;
        }
        // Adding each input number in the FirstUnique object
        for (int num : nums) {
            add(num);
        }
    }

    public int showFirstUnique() {
        return head.next == tail ? -1 : head.next.value;
    }

    public void add(int value) {
        if (map.containsKey(value)) {
            // Input number already exists.
            ListNode node = map.get(value);
            if (node == null) {
                return;
            }
            // Marking the number as repeating, by removing its ListNode
            map.put(value, null);
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
        } else {
            // New number is seen for first time.
            ListNode node = new ListNode(value);
            map.put(value, node);
            tail.prev.next = node;
            node.prev = tail.prev;
            node.next = tail;
            tail.prev = node;
        }
    }
}
