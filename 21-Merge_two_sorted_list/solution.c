/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

/*
  l1: 0 -> 1 -> 2
  l2: 0 -> 1 -> 3

  Iterate over l1, cuts all l2 nodes that smaller or equal to the
  current l1 node and inserts them immediately before the current
  node.
 */
#include "stdlib.h"
#include "stdio.h"
struct ListNode {
      int val;
      struct ListNode *next;
  };
#define Node struct ListNode

Node * cut_until(Node * l, int val, Node ** seg_tail) {
  *seg_tail = NULL;
  while ((l != NULL) && (l->val < val)) {
    *seg_tail = l;
    l = l->next;
  }
  return l;
}

struct ListNode* mergeTwoLists(struct ListNode* l1, struct ListNode* l2){
  Node * curr = l1;
  Node * prev = NULL;
  
  if (l1 == NULL)
    return l2;
  if (l2 == NULL)
    return l1;       
  while (curr != NULL && l2 != NULL) {
    if (curr->val > l2->val) {    
      Node * seg_head, * seg_tail;

      seg_head = l2;
      l2 = cut_until(l2, curr->val, &seg_tail);
      // insert segment immediately before curr
      if (prev == NULL) {
	l1 = seg_head; // segment now is the prefix of the result
	seg_tail->next = curr;
      } else {
	prev->next = seg_head;
	seg_tail->next = curr;
      }
    }
    prev = curr;
    curr = curr->next;
  }
  if (l2 != NULL) 
    prev->next = l2;
  return l1;
}

#define N 5
#define M 5

int main() {
  int a[N] = {1,2,3,4,5};
  int b[M] = {0,2,2,4,4};
  Node * l1 = NULL, * l2 = NULL;
  Node * tail;
  
  for (int i = 0; i < N; i++) {
    Node * tmp = (Node *)malloc(sizeof(Node));
    
    tmp->val = a[i];
    tmp->next = NULL;
    if (l1 == NULL) {
      l1 = tmp;
      tail = tmp;
    } else {
      tail->next = tmp;
      tail = tmp;
    }    
  }
  
  for (int i = 0; i < M; i++) {
    Node * tmp = (Node *)malloc(sizeof(Node));
    
    tmp->val = b[i];
    tmp->next = NULL;
    if (l2 == NULL) {
      l2 = tmp;
      tail = tmp;
    } else {
      tail->next = tmp;
      tail = tmp;
    }
  }
  l1 = mergeTwoLists(l1, l2);  
  while (l1 != NULL) {
    printf("%d-> ", l1->val);
    
    Node * freeable = l1;
    
    l1 = l1->next;
    free(freeable);
  }  
}
