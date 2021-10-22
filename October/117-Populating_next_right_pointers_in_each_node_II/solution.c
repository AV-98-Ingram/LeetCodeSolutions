#define Tree struct Node

struct Node* connect(struct Node* root) {
  if (root == NULL || (root->left == NULL && root->right == NULL))
    return root;

  Tree * left, * right, * right_next = NULL;
  Tree * uncle = root->next;

  left = root->left;
  right = root->right;
  while (uncle != NULL) {
    if (uncle->left != NULL) {
      right_next = uncle->left;
      break;
    }
    if (uncle->right != NULL) {
      right_next = uncle->right;
      break;
    }    
    uncle = uncle->next;
  }
  if (left != NULL) 
    left->next = right;
  if (right == NULL)
    right = left;
  if (right != NULL) 
    right->next = right_next;
  connect(root->right);
  connect(root->left);  
  return root;
}
