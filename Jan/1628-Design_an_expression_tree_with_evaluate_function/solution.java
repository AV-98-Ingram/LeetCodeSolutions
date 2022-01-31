abstract class Node {
    public abstract int evaluate();
    
};


// WHY BOTHER TO BUILD A TREE?
class TreeBuilder {
    Node buildTree(String[] postfix) {
	LinkedList<Integer> operands = new LinkedList<>();
	int val;
	
	for (String s : postfix) {
	    switch (s) {
	    case "+": {
		val = operands.removeFirst() + operands.removeFirst();
		operands.addFirst(val);
		break;
	    }
	    case "-":{
		int opd1 = operands.removeFirst();
		int opd2 = operands.removeFirst();
		val = opd2 - opd1;
		operands.addFirst(val);
		break;
	    }       
	    case "*":
		{
		    val = operands.removeFirst() * operands.removeFirst();
		    operands.addFirst(val);
		    break;
		}		
	    case "/":
		{
		    int opd1 = operands.removeFirst();
		    int opd2 = operands.removeFirst();
		    
		    val = opd2 / opd1;
		    operands.addFirst(val);
		    break;
		}    			       
	    default:
		operands.addFirst(Integer.valueOf(s));
	    }
	}
	return new MyNode(operands.removeFirst());
    }
};


class MyNode extends Node {
    final int val;

    MyNode(int val) {
	this.val = val;
    }
    
    public int evaluate() {
	return val;
    }
}
