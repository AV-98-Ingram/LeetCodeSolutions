class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        int depth_p = 0, depth_q = 0;
	Node pp = p;
	
	while (pp.parent != null) {
	    pp = pp.parent;
	    depth_p++;
	}
	pp = q;
	while (pp.parent != null) {
	    pp = pp.parent;
	    depth_q++;
	}
	return lca(p, depth_p, q, depth_q);
    }

    private Node lca(Node p, int depth_p, Node q, int depth_q) {
	if (depth_p == 0)
	    return p;
	if (depth_q == 0)
	    return q;	    
	if (depth_p == depth_q) {
	    if (p == q)
		return p;
	    return lca(p.parent, depth_p-1, q.parent, depth_q-1);
	}
	if (depth_p > depth_q)
	    return lca(p.parent, depth_p-1, q, depth_q);
	else
	    return lca(p, depth_p, q.parent, depth_q-1);
    }
}
