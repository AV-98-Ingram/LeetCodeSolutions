#define Node struct TreeNode

int sumNumbers(struct TreeNode* root){
  if (root == NULL)
    return 0;
  return f(root, 0);
}

int f(Node * root, int pathSumSoFar) {
  int newPathSum = pathSumSoFar * 10 + root->val;
  
  if (root->left == NULL && root->right == NULL)
    return newPathSum; 
  
  int ret = 0;
  
  if (root->left != NULL)
    ret += f(root->left, newPathSum);
  if (root->right != NULL)
    ret += f(root->right, newPathSum);
  return ret;
}
