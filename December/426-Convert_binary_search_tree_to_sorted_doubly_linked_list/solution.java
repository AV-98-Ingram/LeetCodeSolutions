class Solution {
    public Node treeToDoublyList(Node root) {
	if (root == null)
	    return null;
	
        Node head = new Node(-1);
	Node tail = inorder(root, head);

	head.right.left = tail;
	tail.right = head.right;
	return head.right;
    }

    // return: tail of the converted list
    private Node inorder(Node tree, Node head) {
	if (tree == null)
	    return head;
	Node tail = inorder(tree.left, head);
	Node tree2Node = new Node(tree.val);

	tail.right = tree2Node;
	tree2Node.left = tail;
	tail = tree2Node;
	return inorder(tree.right, tail);
    }
}
