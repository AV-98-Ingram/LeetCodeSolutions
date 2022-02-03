public class Codec {

    // write nodes level by level. Using "()" to group level.
    public String serialize(TreeNode root) {
	if (root == null)
	    return "";
	
        StringBuffer sb = new StringBuffer();
	LinkedList<TreeNode> level = new LinkedList<>();
	LinkedList<TreeNode> nxtLevel = new LinkedList<>();
	
	level.add(root);
	while (!level.isEmpty()) {
	    sb.append("(");
	    while (!level.isEmpty()) {
		TreeNode node = level.poll();
		
		if (node != null) {
		    sb.append(node.val);
		    nxtLevel.add(node.left);
		    nxtLevel.add(node.right);
		} else
		    sb.append("null");
		sb.append(",");
	    }
	    sb.deleteCharAt(sb.length()-1); // delete last ","
	    sb.append("),");
	    LinkedList<TreeNode> tmp = level;
	    
	    level = nxtLevel;
	    nxtLevel = tmp;
	}
	sb.deleteCharAt(sb.length()-1); // delete last ","
	return sb.toString();
    }
    
    
    public TreeNode deserialize(String data) {
	if (data.equals(""))
	    return null;
	
	LinkedList<TreeNode> level = new LinkedList<>();
	int start = 0, end = 0;
	final int size = data.length();
	TreeNode root = null;
	
	end = parseLevel(data, start);
	root = new TreeNode(Integer.valueOf(data.substring(start+1, end)));
	level.add(root);
	start = end+2;
	while (start < size) {
	    end = parseLevel(data, start);
	    level = buildLevel(level, data, start, end);
	    start = end+2;
	}
	return root;
    }
    
    private int parseLevel(String data, int start) {
	assert data.charAt(start) == '(';
	int end = start;
	
	while (data.charAt(end) != ')')
	    end++;
	return end;
    }
    
    private LinkedList<TreeNode> buildLevel(LinkedList<TreeNode> prevLv,
					    String data, int start, int end) {
	String[] vals = data.substring(start+1, end).split(",");
	int i = 0;
	LinkedList<TreeNode> nxtLv = new LinkedList<>();
	
	while (!prevLv.isEmpty()) {
	    TreeNode parent = prevLv.removeFirst();
	    
	    if (vals[i].equals("null"))
		parent.left = null;
	    else {
		parent.left = new TreeNode(Integer.valueOf(vals[i]));
		nxtLv.add(parent.left);
	    }
	    i++;
	    if (vals[i].equals("null"))
		parent.right = null;
	    else {
		parent.right = new TreeNode(Integer.valueOf(vals[i]));
		nxtLv.add(parent.right);		
	    }
	    i++;
	}
	return nxtLv;
    }
}
