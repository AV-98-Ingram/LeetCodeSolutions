#define Tree struct TreeNode
#define List struct ListNode

Tree * build(List * head, List * end);

struct TreeNode* sortedListToBST(struct ListNode* head){
  return build(head, NULL);
}

Tree * build(List * head, List * end) {
  int list_size = 0;
  List * l = head, * m = l;
  
  while (l != NULL && l != end) {
    list_size++;
    l = l->next;
    if (list_size % 2 == 0)
      m = m->next;
  }
  if (list_size == 0)
    return NULL;
  
  Tree * tree = (Tree *)malloc(sizeof(Tree));

  tree->val = m->val;
  tree->left = build(head, m);
  tree->right = build(m->next, end);
  return tree;
}
