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

// Reverses the list from *start_ref to last (last exclusive).
// Updates *prev_ref and *start_ref to the last of the reversed list
// and the head of the reversed list, respectively.
// pre-cond: list shall not be empty.
void reverse(List ** prev_ref, List ** start_ref, List * last) {
  List * start = *start_ref;
  List * prev = *prev_ref;
  List * reversed = last;
  List * node = start;

  while (node != last) {
    List * reversed_suffix = reversed;
    List * next = node->next;
    
    reversed = node;
    reversed->next = reversed_suffix;
    node = next;
  }
  if (prev != NULL)
    prev->next = reversed;
  *prev_ref = start;
  *start_ref = reversed;
}

struct ListNode* reverseKGroup(struct ListNode* head, int k){
  List * prev = NULL;
  List * start = head;
  List * last = head;
  int step = 0;

  // In the case of last == NULL && step == k, we need to reverse the
  // last segment:
  while (last != NULL || step == k) { 
    if (step == k) {
      // revserse:
      char set_head = prev == NULL;
      
      reverse(&prev, &start, last); // last exclusive
      if (set_head)
	head = start;
      start = last;
      step = 0;
      continue;
    } else {
      last = last->next;
      step++;
    }
  }
  return head;
}


int main(int argc, char * argv[]) {

  int a[10] = {1,2,3,4,5,6,7,8,9,10};
  List * l = (List*)malloc(sizeof(List));
  List * ll = l;
  
  l->val = a[0];
  for (int i = 1; i < 10;) {
    ll->next = (List*)malloc(sizeof(List));
    ll->next->val = a[i++];
    ll = ll->next;
  }
  ll->next = NULL;
  
  l = reverseKGroup(l, atoi(argv[1]));
  while (l!=NULL) {
    printf("%3d", l->val);
    
    List * next = l->next;
    
    free(l);
    l = next;
  }
}
