char isSameTree(struct TreeNode* p, struct TreeNode* q){
  if (p == NULL)
    return q == NULL;
  if (q == NULL)
    return 0;
  if (p->val != q->val)
    return 0;
  return isSameTree(p->left, q->left) && isSameTree(p->right, q->right);
}
