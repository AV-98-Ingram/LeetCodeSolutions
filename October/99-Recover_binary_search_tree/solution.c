#define Node struct TreeNode
#define MAX(a,b) (((a)->val)>((b)->val)?(a):(b))
#define MIN(a,b) (((a)->val)>((b)->val)?(b):(a))

void f(Node * tree, Node ** out);
Node * errs[4];

void recoverTree(struct TreeNode* root) {
  if (root == NULL)
    return;  
  errs[0] = errs[1] = errs[2] = errs[3] = NULL;
  
  Node * out[2];

  f(root, out);
  // re-use out[0] and out[1] for value holder:
  if (errs[2] != NULL) {
    out[0] = MAX(MAX(MAX(errs[0], errs[1]), errs[2]), errs[3]);
    out[1] = MIN(MIN(MIN(errs[0], errs[1]), errs[2]), errs[3]);
  } else {
    out[0] = errs[0];
    out[1] = errs[1];
  }

  int tmp = out[0]->val;

  out[0]->val = out[1]->val;
  out[1]->val = tmp;
}

// f := find_wrong_nodes
void f(Node * tree, Node **out) {
  Node * one, * two, * three; // one >= two >= three
  Node * min, * max;          // min is the far left and max is the far right
  
  if (tree->left == NULL) {
    one = tree;
    min = tree;
  } else {
    f(tree->left, out);
    one = out[1];
    min = out[0];
  }
  two = tree;
  if (tree->right == NULL) {
    three = tree;
    max = tree;
  } else {
    f(tree->right, out);
    three = out[0];
    max = out[1];
  }

  if (one->val > two->val) {
    if (errs[0] == NULL) {
      errs[0] = one;
      errs[1] = two;
    } else {
      errs[2] = one;
      errs[3] = two;
    }
  }  
  if (two->val > three->val) {
    if (errs[0] == NULL) {
      errs[0] = two;
      errs[1] = three;
    } else {
      errs[2] = two;
      errs[3] = three;
    }
  }
  out[0] = min;
  out[1] = max;
}
