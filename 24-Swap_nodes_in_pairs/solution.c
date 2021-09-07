/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
#include "stdlib.h"
#include "stdio.h"

struct ListNode {
  int val;
  struct ListNode *next;
};

#define List struct ListNode

struct ListNode* swapPairs(struct ListNode* head){
  int step = 0;
  List * node = head;
  List * prev = NULL;
  List * prevprev = NULL;
  
  while (node != NULL) {
    List * next = node->next;
      
    if (step % 2 == 1) {
      // swap with prev:       
      if (prevprev == NULL) {
	// prev is head:
	head = node;
	node->next = prev;
	prev->next = next;
      } else {
	prevprev->next = node;
	node->next = prev;
	prev->next = next;
      }
      // prev = prev; so skip
      prevprev = node;
    } else {
      // update prevprev and prev in case no swap at this step:
      prevprev = prev;
      prev = node;
    }
    step = (step + 1) % 2;
    node = next;
  }
  return head;
}

int main() {

  int a[11] = {1,2,3,4,5,6,7,8,9,10,11};
  List * l = (List*)malloc(sizeof(List));
  List * ll = l;
  
  l->val = a[0];
  for (int i = 1; i < 11;) {
    ll->next = (List*)malloc(sizeof(List));
    ll->next->val = a[i++];
    ll = ll->next;
  }
  ll->next = NULL;
  
  l = swapPairs(l);
  while (l!=NULL) {
    printf("%3d", l->val);
    
    List * next = l->next;
    
    free(l);
    l = next;
  }
}
