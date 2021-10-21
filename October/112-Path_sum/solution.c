#define Node struct TreeNode
char f(Node * tree, int sum, int tgt);

char hasPathSum(struct TreeNode* root, int targetSum) {
  if (root == NULL)
    return 0;
  return f(root, 0, targetSum);
}

char f(Node * tree, int sum, int tgt) {
  if (tree == NULL)
    return 0;
  if (tree->left == NULL && tree->right == NULL)
    return sum + tree->val == tgt;
  sum += tree->val;
  if (f(tree->left, sum, tgt))
    return 1;
  return f(tree->right, sum, tgt);
}

