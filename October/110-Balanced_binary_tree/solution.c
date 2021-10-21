#define Node struct TreeNode
#define ABS(x) ((x) > 0 ? (x) : -(x))
#define MAX(x, y) ((x)>(y) ? (x) : (y))

int f(Node * tree);

bool isBalanced(struct TreeNode* root) {
  return f(root) >= 0;
}

int f(Node * tree) {
  if (tree == NULL)
    return 0;

  int left_depth = f(tree->left);
  int right_depth = f(tree->right);

  if (left_depth < 0 || right_depth < 0)
    return -1;
  
  if (ABS(left_depth - right_depth) <= 1)
    return MAX(left_depth, right_depth) + 1;
  else
    return -1;
}
