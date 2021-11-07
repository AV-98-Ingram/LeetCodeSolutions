
#define Node struct TreeNode
int f(Node * tree, int seen_big);
int goodNodes(struct TreeNode* root){
  if (root == NULL)
    return 0;
  return f(root, root->val);
}

// return the number of good nodes in tree
// seen_big is the max value along a path from root to tree
int f(Node * tree, int seen_big) {
  if (tree == NULL)
    return 0;
   
  if (tree->val >= seen_big)
    return 1 + f(tree->left, tree->val) + f(tree->right, tree->val);
  else
    return f(tree->left, seen_big) + f(tree->right, seen_big);
}
