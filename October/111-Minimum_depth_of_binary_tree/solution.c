#define Node struct TreeNode
#define MIN(x, y)  ((x) < (y) ? (x) : (y))

int f(Node * tree);

int minDepth(struct TreeNode* root) {
  if (root == NULL)
    return 0;
  return f(root);
}

int f(Node * tree) {
  if (tree == NULL)
    return 0;

  int left_min = f(tree->left);
  int right_min = f(tree->right);

  if (left_min == 0)
    return right_min + 1;
  if (right_min == 0)
    return left_min + 1;  
  return MIN(left_min, right_min) + 1;
}
