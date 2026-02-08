/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
      if (head == null) return head;

      Map<Node, Node> visited = new HashMap<>();
      return dfs(head, visited);
      
    }

    private Node dfs(Node node, Map<Node, Node> map) {
      if (map.containsKey(node)) return map.get(node);
      Node copy = new Node(node.val);
      map.put(copy);
      copy.next = dfs(node.next, map);
      copy.random = dfs(node.random, map);
      return map.get(node);
    }
}