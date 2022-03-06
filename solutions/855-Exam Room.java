// LeetCode Question URL: https://leetcode.com/problems/exam-room/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using TreeMap + TreeSet + HashMap
 *
 * <pre>
 * Time Complexity:
 * ExamRoom() -> O(1)
 * seat() -> O(log(Filled Seats + 1)) ≈ O(log N)
 * leave() -> O(log(Filled Seats + 1)) ≈ O(log N)
 *
 * Space Complexity: O(N)
 * </pre>
 */
class ExamRoom1 {
    HashMap<Integer, int[]> hMap;
    TreeMap<Integer, int[]> map;
    TreeSet<Integer> starts;
    int N;

    public ExamRoom1(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid Input");
        }

        hMap = new HashMap<>();
        map = new TreeMap<>((a, b) -> {
            int[] i1 = hMap.get(a);
            int[] i2 = hMap.get(b);
            return i1[1] != i2[1] ? Integer.compare(i2[1], i1[1]) : Integer.compare(a, b);
        });
        int[] it = new int[] { n - 1, n - 1 };
        hMap.put(0, it);
        map.put(0, it);
        starts = new TreeSet<>();
        starts.add(0);
        N = n;
    }

    public int seat() {
        Map.Entry<Integer, int[]> cur = map.pollFirstEntry();
        int curStart = cur.getKey();
        int curEnd = cur.getValue()[0];
        int curLen = cur.getValue()[1];
        starts.remove(curStart);
        hMap.remove(curStart);

        int seat;
        if (curStart == 0) {
            seat = 0;
        } else if (curEnd == N - 1) {
            seat = N - 1;
        } else {
            seat = curStart + curLen;
        }

        if (curStart < seat) {
            int[] it = new int[] { seat - 1, getDistance(curStart, seat - 1) };
            hMap.put(curStart, it);
            map.put(curStart, it);
            starts.add(curStart);
        }
        if (seat < curEnd) {
            int[] it = new int[] { curEnd, getDistance(seat + 1, curEnd) };
            hMap.put(seat + 1, it);
            map.put(seat + 1, it);
            starts.add(seat + 1);
        }

        return seat;
    }

    private int getDistance(int start, int end) {
        if (start == 0 || end == N - 1) {
            return end - start;
        } else {
            return (end - start) / 2;
        }
    }

    public void leave(int p) {
        Integer l = starts.lower(p);
        int start = p;
        if (l != null) {
            int[] it = map.get(l);
            if (it[0] + 1 == p) {
                start = l;
                map.remove(l);
                hMap.remove(l);
                starts.remove(l);
            }
        }

        Integer h = starts.higher(p);
        int end = p;
        if (h != null) {
            if (h - 1 == p) {
                end = map.get(h)[0];
                map.remove(h);
                hMap.remove(h);
                starts.remove(h);
            }
        }

        int[] it = new int[] { end, getDistance(start, end) };
        hMap.put(start, it);
        map.put(start, it);
        starts.add(start);
    }
}

/**
 * Using Doubly LinkedList
 *
 * <pre>
 * Time Complexity:
 * ExamRoom() -> O(1)
 * seat() -> O(Filled Seats) ≈ O(N)
 * leave() -> O(1)
 *
 * Space Complexity: O(Filled Seats) ≈ O(N)
 * </pre>
 */
class ExamRoom2 {
    class Node {
        int val;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int v) {
            val = v;
        }
    }

    Node head;
    Node tail;
    Map<Integer, Node> map;
    int N;

    public ExamRoom2(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid Input");
        }
        head = new Node();
        tail = new Node();
        head.right = tail;
        tail.left = head;
        map = new HashMap<>();
        N = n;
    }

    public int seat() {
        if (map.size() == N) {
            throw new NoSuchElementException("All seats are full");
        }

        if (map.size() == 0) {
            Node node = new Node(0);
            map.put(0, node);
            insertNode(node, head);
            return 0;
        }

        Node lastFilledSeat = head.right;
        int maxDist = lastFilledSeat.val;
        Node preSeat = head;
        Node cur = lastFilledSeat.right;

        while (cur != tail) {
            int d = (cur.val - lastFilledSeat.val) / 2;
            if (d > maxDist) {
                maxDist = d;
                preSeat = lastFilledSeat;
            }
            lastFilledSeat = cur;
            cur = cur.right;
        }

        int d = N - 1 - lastFilledSeat.val;
        if (d > maxDist) {
            preSeat = lastFilledSeat;
            maxDist = d;
        }

        int curSeat = preSeat == head ? 0 : preSeat.val + maxDist;
        Node node = new Node(curSeat);
        map.put(curSeat, node);
        insertNode(node, preSeat);
        return curSeat;
    }

    public void leave(int p) {
        Node node = map.remove(p);
        if (node != null) {
            node.left.right = node.right;
            node.right.left = node.left;
            node.right = null;
            node.left = null;
        }
    }

    private void insertNode(Node newNode, Node prevNode) {
        newNode.left = prevNode;
        newNode.right = prevNode.right;
        prevNode.right.left = newNode;
        prevNode.right = newNode;
    }
}

class ExamRoom3 {
    TreeSet<Integer> seats;
    int N;

    public ExamRoom3(int n) {
        seats = new TreeSet<>();
        N = n;
    }

    public int seat() {
        int seat = 0;

        if (seats.size() > 0) {
            int dist = seats.first();
            int prev = seats.first();
            for (int s : seats) {
                int d = (s - prev) / 2;
                if (d > dist) {
                    dist = d;
                    seat = prev + d;
                }
                prev = s;
            }

            if (N - 1 - prev > dist) {
                seat = N - 1;
            }
        }

        seats.add(seat);
        return seat;
    }

    public void leave(int p) {
        seats.remove(p);
    }
}

class ExamRoom4 {
    boolean[] seats;
    int count;

    public ExamRoom4(int n) {
        seats = new boolean[n];
        count = 0;
    }

    public int seat() {
        if (count == 0) {
            seats[0] = true;
            count++;
            return 0;
        }

        int n = seats.length;

        if (count == n) {
            throw new NoSuchElementException("All seats are full");
        }

        int lastFilledSeat = seats[0] ? 0 : -1;
        int i = seats[0] ? 1 : 0;
        int maxDistance = 0;
        int candidateSeat = -1;

        for (; i < n; i++) {
            if (seats[i]) {
                if (lastFilledSeat == -1) {
                    maxDistance = i;
                    candidateSeat = 0;
                } else {
                    int d = (i - lastFilledSeat) / 2;
                    if (d > maxDistance) {
                        maxDistance = d;
                        candidateSeat = lastFilledSeat + d;
                    }
                }
                lastFilledSeat = i;
            }
        }
        if (lastFilledSeat != n - 1) {
            if (n - lastFilledSeat - 1 > maxDistance) {
                candidateSeat = n - 1;
            }
        }

        seats[candidateSeat] = true;
        count++;
        return candidateSeat;
    }

    public void leave(int p) {
        if (seats[p]) {
            seats[p] = false;
            count--;
        }
    }
}

// Your ExamRoom object will be instantiated and called as such:
// ExamRoom obj = new ExamRoom(n);
// int param_1 = obj.seat();
// obj.leave(p);
