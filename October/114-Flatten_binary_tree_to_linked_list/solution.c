#define Node struct TreeNode

Node * flat(Node * tree);

void flatten(struct TreeNode* root){
  if (root != NULL)
    flat(root);
  return root;
}

// returns the last node in the one after flattening the input
Node * flat(Node * tree) {
  if (tree->left == NULL) {
    if (tree->right != NULL)
      return flat(tree->right);
    return tree;
  }
  
  Node * right = tree->right;
  Node * end = flat(tree->left);

  tree->right = tree->left;
  tree->left = NULL;
  end->right = right;
  if (right != NULL)
    return flat(right);
  return end;  
}
