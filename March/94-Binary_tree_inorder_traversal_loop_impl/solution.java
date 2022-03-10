class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null)
            return new LinkedList<>();
        
        LinkedList<TreeNode> stack = new LinkedList<>();
        List<Integer> result = new LinkedList<>();                

        stack.addFirst(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.getFirst();
            
            while (node.left != null) {
                stack.addFirst(node.left);
                node = node.left;
            }
            do {           
                node = stack.removeFirst();  
                result.add(node.val);
            } while (node.right == null && !stack.isEmpty());
            if (node.right != null)
                stack.addFirst(node.right);
        } 
        return result;
    }
}
