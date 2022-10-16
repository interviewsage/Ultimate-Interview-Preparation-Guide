// LeetCode URL: https://leetcode.com/problems/accounts-merge

import java.util.*;

/**
 * Using union-find (Union by rank and path compression) to group all emails
 * belonging to same owner. And using TreeSet to sort each group of emails.
 *
 * <pre>
 * N = Total Number of accounts in the input
 * Ei = Number of emails in each account in the input
 * E = Total Number of emails
 *
 * Time Complexity = O(∑(i = 1 -> N) (Ei)  +  ∑(i = 1 -> N) (Ei log Ei)  +  ∑(i = 1 -> N) (Ei))
 *                 = O(E + E * log(Max_No_of_unique_emails_in_an_account) + E)
 * </pre>
 *
 * Space Complexity: O(E)
 *
 * N = Total Number of accounts in the input
 * Ei = Number of emails in each account in the input
 * E = Total Number of emails
 * Here assuming the length of each email and owner string is a fixed constant.
 */

class Solution {
    private class Node {
        String ownerName;
        String parent;
        int rank;

        public Node(String ownerName, String parent) {
            this.ownerName = ownerName;
            this.parent = parent;
            this.rank = 0;
        }
    }

    private class UnionFind {
        Map<String, Node> emailToNode;

        public UnionFind() {
            emailToNode = new HashMap<>();
        }

        public void addEmail(String email, String ownerName) {
            emailToNode.putIfAbsent(email, new Node(ownerName, email));
        }

        public String findParent(String email) {
            Node cur = emailToNode.get(email);
            if (!email.equals(cur.parent)) {
                cur.parent = findParent(cur.parent);
            }
            return cur.parent;
        }

        public void union(String email1, String email2) {
            String parent1 = findParent(email1);
            String parent2 = findParent(email2);

            if (parent1.equals(parent2)) {
                return;
            }

            Node node1 = emailToNode.get(parent1);
            Node node2 = emailToNode.get(parent2);

            if (node1.rank >= node2.rank) {
                node2.parent = parent1;
                if (node1.rank == node2.rank) {
                    node1.rank++;
                }
            } else {
                node1.parent = parent2;
            }
        }

        public String getOwnerName(String email) {
            return emailToNode.get(email).ownerName;
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null) {
            throw new IllegalArgumentException("Input list is null");
        }

        List<List<String>> result = new ArrayList<>();
        if (accounts.size() == 0) {
            return result;
        }

        UnionFind uf = new UnionFind();
        for (List<String> acc : accounts) {
            if (acc == null || acc.size() <= 1) {
                continue;
            }

            String ownerName = acc.get(0);
            String firstEmail = acc.get(1);
            uf.addEmail(firstEmail, ownerName);

            for (int i = 2; i < acc.size(); i++) {
                String email = acc.get(i);
                uf.addEmail(email, ownerName);
                uf.union(firstEmail, email);
            }
        }

        Map<String, Set<String>> groups = new HashMap<>();
        for (List<String> acc : accounts) {
            if (acc == null || acc.size() <= 1) {
                continue;
            }

            String parent = uf.findParent(acc.get(1));
            Set<String> curGroup = groups.get(parent);
            if (curGroup == null) {
                curGroup = new TreeSet<>();
                groups.put(parent, curGroup);
            }
            for (int i = 1; i < acc.size(); i++) {
                curGroup.add(acc.get(i));
            }
        }

        for (String parent : groups.keySet()) {
            List<String> acc = new ArrayList<>();
            result.add(acc);
            acc.add(uf.getOwnerName(parent));
            acc.addAll(groups.get(parent));
        }

        return result;
    }
}

/**
 * Using union-find with rank and path compression.
 *
 * Reading and adding the email to Disjoint Set using union = N operations
 * Saving all emails in a set to one parent = N Operations. Thus Total 2N
 * operations using Union-Find. To sort and add name to each list is O(N logN)
 *
 * Thus Total Time Complexity : O(2*E + E * logE)
 *
 * Space Complexity : O(2*E + 2*E + 2*E)
 *
 * E = Total number of email addresses in the input.
 */
class Solution2 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        if (accounts == null) {
            return result;
        }
        if (accounts.size() < 2) {
            return accounts;
        }

        Map<String, Node> emailToNode = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // Reading all emails from the input List and adding them to Disjoint Set using
        // union.
        for (List<String> acc : accounts) {
            if (acc.size() < 2) {
                continue;
            }
            String name = acc.get(0);
            String email1 = acc.get(1);
            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                emailToName.put(email, name);
                if (!emailToNode.containsKey(email)) {
                    emailToNode.put(email, new Node());
                }
                union(emailToNode.get(email1), emailToNode.get(email));
            }
        }

        Map<Node, TreeSet<String>> userGroups = new HashMap<>();

        // Saving all emails in a set to one parent in a hashmap.
        for (String email : emailToNode.keySet()) {
            Node parent = findSet(emailToNode.get(email));
            if (!userGroups.containsKey(parent)) {
                userGroups.put(parent, new TreeSet<String>());
            }
            userGroups.get(parent).add(email);
        }

        // Adding the name of the user.
        for (TreeSet<String> userG : userGroups.values()) {
            LinkedList<String> tempList = new LinkedList<>(userG);
            tempList.addFirst(emailToName.get(tempList.get(0)));
            result.add(tempList);
        }

        return result;
    }

    private class Node {
        int rank;
        Node parent;

        Node() {
            rank = 0;
            parent = this;
        }
    }

    private Node findSet(Node node) {
        if (node != node.parent) {
            node.parent = findSet(node.parent);
        }
        return node.parent;
    }

    private void union(Node n1, Node n2) {
        Node p1 = findSet(n1);
        Node p2 = findSet(n2);

        if (p1 == p2) {
            return;
        }

        if (p1.rank >= p2.rank) {
            p1.rank = p1.rank == p2.rank ? p1.rank + 1 : p1.rank;
            p2.parent = p1;
        } else {
            p1.parent = p2;
        }
    }
}
