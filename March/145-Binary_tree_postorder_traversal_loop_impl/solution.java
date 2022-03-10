/*
  Pure iterative implementation (no tree modification) of postorder tree traversal
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        
        if (root == null)
            return result;
        
        LinkedList<Entry> stack = new LinkedList<>();

        stack.addFirst(new Entry(root));
        while (!stack.isEmpty()) {            
            // this entry must hold a node whose children haven't been explored at all:
            Entry entry = stack.getFirst();           
            
            while (entry.node.left != null) {
                entry = new Entry(entry.node.left);
                stack.addFirst(entry);
            }
            // current entry (top of stack) has no left child              
            while (!stack.isEmpty() && 
                   (stack.getFirst().rightPushed || stack.getFirst().node.right == null)) {
                entry = stack.removeFirst();
                result.add(entry.node.val);
            } 
            if (!stack.isEmpty()) {
                entry = stack.getFirst();
                entry.rightPushed = true;
                stack.addFirst(new Entry(entry.node.right));
            }
        }
        return result;
    }
    
    class Entry {
        TreeNode node;
        boolean rightPushed = false;
        
        Entry(TreeNode node) {
            this.node = node;
        }
    }
}
