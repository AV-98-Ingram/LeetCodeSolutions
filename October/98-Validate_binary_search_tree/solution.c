#define Node struct TreeNode
char isValid(Node* root, int lb, int ub);

char isValidBST(struct TreeNode* root){
  return isValid(root, INT_MIN, INT_MAX);
}

char isValid(Node* root, int lb, int ub) {
  if (root == NULL)
    return 1;
  if (lb <= root->val && root->val <= ub) {
    if (root->val == INT_MIN)
      return root->left == NULL && isValid(root->right, root->val+1, ub);
    if (root->val == INT_MAX)
      return root->right == NULL && isValid(root->left, lb, root->val-1);   
    return isValid(root->left, lb, root->val-1) && isValid(root->right, root->val+1, ub);
  }
  return 0;
}
