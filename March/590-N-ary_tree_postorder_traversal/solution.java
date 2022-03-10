/*
// Definition for a Node.
class Node {
public int val;
public List<Node> children;

public Node() {}

public Node(int _val) {
val = _val;
}

public Node(int _val, List<Node> _children) {
val = _val;
children = _children;
}
};
*/

class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> result = new LinkedList<>();
        
        if (root == null)
            return result;
        
        LinkedList<Entry> stack = new LinkedList<>();
        
        stack.add(new Entry(root));
        while (!stack.isEmpty()) {
            Entry entry = stack.getFirst();
            // entry's children haven't been explored:
            
            while (entry.children.hasNext()) {
                Node node = entry.children.next();
                
                stack.addFirst(new Entry(node));
                entry = stack.getFirst();
            }
            // top entry has no child:
            while (!stack.isEmpty() && !stack.getFirst().children.hasNext()) {
                entry = stack.removeFirst();
                result.add(entry.node.val);
            }
            if (!stack.isEmpty()) {
                Node node = stack.getFirst().children.next();
                
                stack.addFirst(new Entry(node));
            }
        }        
        return result;
    }
    
    class Entry {
        Node node;
        Iterator<Node> children;
        
        Entry (Node node) {
            this.node = node;
            this.children = node.children.iterator();
        }
    }
}
