#define GNode struct Node

GNode * clone(GNode * node, GNode ** nodes);

struct Node *cloneGraph(struct Node *s) {
  if (s == NULL)
    return s;
  
  GNode * nodes[101];

  memset(nodes, 0, 101 * sizeof(GNode *));
  return clone(s, nodes);
}

GNode * clone(GNode * node, GNode ** nodes) {  
  int num_neibs = node->numNeighbors;
  
  if (nodes[node->val] == NULL) {
    nodes[node->val] = (GNode*)malloc(sizeof(GNode));
    nodes[node->val]->val = node->val;
    nodes[node->val]->numNeighbors = num_neibs;
    nodes[node->val]->neighbors = (GNode**)malloc(sizeof(GNode*) * num_neibs);
  }
  else
    return nodes[node->val];
  
  for (int i = 0; i < num_neibs; i++) {
    GNode * neib_clone = clone(node->neighbors[i], nodes);

    nodes[node->val]->neighbors[i] = neib_clone;
  }
  return nodes[node->val];
}
