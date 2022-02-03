public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
	if (root == null)
	    return "";

	LinkedList<TreeNode> que = new LinkedList<>();
	LinkedList<Integer> l = new LinkedList<>();

	que.add(root);
	while (!que.isEmpty()) {
	    TreeNode node = que.removeFirst();

	    if (node != null) {
		l.add(node.val);
		que.add(node.left);
		que.add(node.right);		
	    } else 
		l.add(null);
	}
	while (l.getLast() == null)
	    l.removeLast();
	
	StringBuffer sb = new StringBuffer();

	for (Integer elt : l)
	    sb.append(String.valueOf(elt) + ",");
	sb.deleteCharAt(sb.length() - 1); // detele ","
	return sb.toString();
    }



    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
	if (data.equals(""))
	    return null;

	LinkedList<TreeNode> que = new LinkedList<>();
	String[] vals = data.split(",");
	final int len = vals.length;
	int i = 1;
	TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
	
	que.add(root);
	while (!que.isEmpty() && i < len) {
	    TreeNode parent = que.removeFirst();
	    
	    if (vals[i].equals("null")) {
		parent.left = null;
		i++;
	    } else {
		parent.left = new TreeNode(Integer.valueOf(vals[i++]));
		que.add(parent.left);
	    }
	    if (i < len) {
		if (vals[i].equals("null")) {
		    parent.right = null;
		    i++;
		} else {
		    parent.right = new TreeNode(Integer.valueOf(vals[i++]));
		    que.add(parent.right);
		}
	    }	    
	}
	return root;
    }
}
