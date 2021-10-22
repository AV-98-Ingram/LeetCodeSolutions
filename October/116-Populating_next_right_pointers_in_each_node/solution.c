#define Tree struct Node

void f(Tree*);
void g(Tree*, Tree*);

struct Node* connect(struct Node* root) {
  if (root == NULL)
    return root;
  else
    f(root);
  return root;
}

void f(Tree * t) {
  if (t->left == NULL && t->right == NULL)
    return;  
  // connect siblings:
  t->left->next = t->right;
  // connect cousins, take use of previous estabilished "next-" relations:
  if (t->next != NULL)
    t->right->next = t->next->left;
  f(t->left);
  f(t->right);
}
