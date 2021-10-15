#define Node struct TreeNode
char f(Node * a, Node * b);

char isSymmetric(struct TreeNode* root){
  if (root == NULL)
    return 1;
  return f(root->left, root->right);
}

char f(Node * a, Node * b) {
  if (a == NULL || b == NULL)
    return a == b;
  if (a->val != b->val)
    return 0;
  return f(a->left, b->right) && f(a->right, b->left);
}
