/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

abstract class Node {
    public abstract int evaluate();
    // define your fields here
};


/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input 
 * and returns the expression tree represnting it as a Node.
 */

class TreeBuilder {
    Node buildTree(String[] postfix) {
	LinkedList<Node> stack = new LinkedList<>();

	for (String s : postfix) {
	    if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
		Node right = stack.removeFirst();
		Node left = stack.removeFirst();

		stack.addFirst(new OpNode(left, right, s.charAt(0)));
	    } else
		stack.addFirst(new ValNode(Integer.valueOf(s)));
	}
	return stack.getFirst();
    }
};

class ValNode extends Node {
    int val;

    ValNode(int val) {
	this.val = val;
    }
    
    public int evaluate() {
	return val;
    }

    public String toString() {
	return ""+val;
    }
}

class OpNode extends Node{

    Node left, right;
    char kind;
    
    OpNode (Node left, Node right, char kind) {
	this.left = left;
	this.right = right;
	this.kind = kind;
    }
    
    public int evaluate() {
	switch (kind) {
	case '+':
	    return left.evaluate() + right.evaluate();
	case '-':
	    return left.evaluate() - right.evaluate();	    
	case '*':
	    return left.evaluate() * right.evaluate();	    
	case '/':
	    return left.evaluate() / right.evaluate();
	}
	return 0;
    }
    
    public String toString() {
	return left + ""+kind + right;
    }
    
};
