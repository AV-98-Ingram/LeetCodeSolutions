#include "stdlib.h"

#define T int

typedef struct List {
  T val;
  struct List * next;
} List;


// returns a pointer to the newly created node
List * add(List * l, T val) {
  List * node = (List *)malloc(sizeof(List));

  node->val = val;
  node->next = NULL;
  if (l != NULL)
    l->next = node;
  return node;
}

// inserts node after list l
// pre-cond: l != NULL
// returns a pointer to the newly inserted node
List * insert(List * l, T val) {
  List * old_next = l->next;
  List * new_next = add(l, val);

  new_next->next = old_next;
  return new_next;
}

// l: null-able
// ensures: l->next == NULL
void delete_after(List * l) {
  if (l != NULL) {
    delete_after(l->next);
    free(l->next);
  }
}

List * array_to_list(int * arr, int size) {
  if (size <= 0)
    return NULL;
  
  List * head = add(NULL, arr[0]);
  List * curr = head;
  
  for (int i = 1; i < size; i++)
    curr = add(curr, arr[i]);
  return head;
}
