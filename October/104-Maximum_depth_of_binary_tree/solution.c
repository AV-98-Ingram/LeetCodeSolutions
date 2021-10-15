#define MAX(x,y) ((x)>(y)?(x):(y))

int maxDepth(struct TreeNode* root){
  if (root == NULL)
    return 0;
  
  int depth1 = 1 + maxDepth(root->left);
    
  if (depth1 >= 10000)
    return depth1;
  
  int depth2 = 1 + maxDepth(root->right);
  
  return MAX(depth1, depth2);
}
