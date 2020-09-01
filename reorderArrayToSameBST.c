/* Description: Given an array nums that represents a permutation of
   integers from 1 to n. We are going to construct a binary search
   tree (BST) by inserting the elements of nums in order into an
   initially empty BST. Find the number of different ways to reorder
   nums so that the constructed BST is identical to that formed from
   the original array nums.

   For example, given nums = [2,1,3], we will have 2 as the root, 1 as
   a left child, and 3 as a right child. The array [2,3,1] also yields
   the same BST but [3,2,1] yields a different BST.

   Return the number of ways to reorder nums such that the BST formed
   is identical to the original BST formed from nums.

   Since the answer may be very large, return it modulo 10^9 + 7.
 */

/* Basic idea: build the BST and traverse each sub-tree once and
   computes the number of permutations for each sub-tree.

   Let t be a sub-tree. Denoted by left(t) and right(t) respectively,
   the left and right child of t.  Let perms(x) denote the number of
   permutations of a sub-tree x and size(x) denote the number of nodes
   in a sub-tree x.  Then:

   perm(t) = perm(left(t)) * perm(right(t)) * place(size(left(t)), size(right(t)) + 1),  
   
   where place(n, m) means the number of ways to put n ordered
   elements in m places. Remark that each place can hold multiple
   elements.  For example, there are 3 ways to put the order elements
   {a, b} into two places p1 and p2:

   1. p1: a b, p2:
   2. p1: a    P2: b
   3. p1:      p2  a b

   Thus, place(2, 2) = 3.

   The equation about perm(t) above means that the left sub-tree of t
   has perm(left(t)) permutations and the right sub-tree of t has
   perm(right(t)) permutations.  So there are perm(left(t)) *
   perm(right(t)) kinds of combinations of two ordered numbers
   representing left and right children resp.  For each such
   combination, the two ordered number lists can be mixed in ways that
   the original order of the two lists are maintained.  That is
   equivalent to place the elements of the ordered list representing
   the left sub-tree into the places between elements of the ordered
   listing representing the right sub-tree.  There are size(right(t))
   + 1 such places.
 */


#include "string.h"
#include "stdlib.h"
#include "stdio.h"

typedef struct BST {
  int val;
  struct BST * left;
  struct BST * right;
} BST;


BST * createBST(int root) {
  BST *bst = (BST *)malloc(sizeof(BST));

  bst->val = root;
  bst->left = NULL;
  bst->right = NULL;
  return bst;
}

char insertBST(BST* bst, int val) {
  if (bst->val > val) {
    if (bst->left == NULL) {
      bst->left = createBST(val);
      return 1;
    } else {
      return insertBST(bst->left, val);
    }
  } else {
    if (bst->right == NULL) {
      bst->right = createBST(val);
      return 1;
    } else {
      return insertBST(bst->right, val);
    }
  }
}

void freeBST(BST* bst) {
  if (bst->left != NULL)
    free(bst->left);
  if (bst->right != NULL)
    free(bst->right);
  free(bst);
}

void printBSTWorker(BST *bst, int prefix) {
  for (int i = 0; i < prefix; i++)
    printf("|");
  printf("[%d]\n", bst->val);
  if (bst->left != NULL)
    printBSTWorker(bst->left, prefix + 1);
  if (bst->right != NULL)
    printBSTWorker(bst->right, prefix + 1);  
}

void printBST(BST * bst) {
  if (bst != NULL)
    printBSTWorker(bst, 0);
}

#define MOD(n) ((n)%(1000000007))

int insert_n_to_m(int n, int m, int size, int (*table)[size]) {
  if (table[n][m] > 0)
    return table[n][m];
  if (n <= 1)
    return m;
  if (m <= 1)
    return 1;
  
  long ret = insert_n_to_m(n-1, m, size, table) +
    insert_n_to_m(n, m-1, size, table);
  table[n][m] = MOD(ret);
  return table[n][m];
}

// Returns the number of different permutations of the arrays that can
// form the given BST
long nperms(BST *bst, int * size, int n, int (*table)[n]) {
  if (bst == NULL) {
    *size = 0;
    return 1;
  }
  
  int size_l, size_r;
  long perm_l = nperms(bst->left, &size_l, n, table);
  long perm_r = nperms(bst->right, &size_r, n, table);
  long perm = perm_l * perm_r;
  
  *size = 1 + size_l + size_r;
  if (size_l == 0 || size_r == 0)
    return perm;
  perm = MOD(perm) * insert_n_to_m(size_r, size_l + 1, n, table);
  return MOD(perm);
}

////////////////////////////////////////////

int main(int argc, char **argv) {
  int size = argc - 1;
  int a[size];

  for (int i = 0; i < size; i++) {
    a[i] = atoi(argv[i+1]);
    printf("%5d", a[i]);
  }
  printf("\n");

  BST * bst = createBST(a[0]);

  for (int i = 1; i < size; i++)
    insertBST(bst, a[i]);

  int n;
  int table[size][size];

  memset(table, 0, sizeof(int) * size * size);
  
  int perms = nperms(bst, &n, size, table) - 1; //subtract the origin one

  printf("perms = %d\n", perms);
  freeBST(bst);
}


