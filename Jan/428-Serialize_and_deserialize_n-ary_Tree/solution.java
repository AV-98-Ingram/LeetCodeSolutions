class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
	if (root == null)
	    return "";
	
	StringBuffer sb = new StringBuffer();
	
        se(root, sb);
	return sb.toString();
    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
	LinkedList<Node> root = new LinkedList<>();

	de(data, 0, root);
	if (root.size() == 1)
	    return root.getFirst();
	return null;
    }

    private void se(Node root, StringBuffer sb) {
	sb.append(root.val);
	sb.append(" ");
	if (root.children.isEmpty())
	    return;
	sb.append("( ");
	for (Node child : root.children) {
	    se(child, sb);
	}
	sb.append(") ");
    }

    private int de(String data, int pos, LinkedList<Node> siblings) {
	int len = data.length();
	int i = pos;
	
	while (i < len) {
	    if (data.charAt(i) == ' ') {
		i++;
		continue;
	    }
	    if (data.charAt(i) == ')') {
		i++;
		break;
	    }
	    if (data.charAt(i) == '(') {
		// recursive:
		LinkedList<Node> children = new LinkedList<>();
		
		i = de(data, i+1, children);
		siblings.getLast().children.addAll(children);
	    } else 
		i = parseNode(data, i, siblings);
	}
	return i;
    }

    private int parseNode(String data, int pos, List<Node> siblings) {
	char c = data.charAt(pos);
	int n = 0;
	
	while ('0' <= c && c <= '9') {
	    n = n * 10 + (c - '0');
	    c = data.charAt(++pos);
	}
	siblings.add(new Node(n, new LinkedList<>()));	
	return pos;
    }
}
