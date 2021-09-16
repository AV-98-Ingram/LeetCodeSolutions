/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

/* Maintaining a collection of size n+1 saving the last n+1 visited
   nodes.  When the traversal is done, take the last n visited node
   off.  This operation will need the last n+1 visited node and the
   last n-1 visited node.

   Be careful that the original head may not be the head after
   removing.
 */
#include "stdlib.h"
#include "stdio.h"

 struct ListNode {
     int val;
     struct ListNode *next;
 };

#define Node struct ListNode

#define N (n+1)

struct ListNode* removeNthFromEnd(struct ListNode* head, int n){
  Node * hist[N];
  Node * curr = head;
  int i = 0;
  int oldest = 0; // index of the oldest node in hist
  char full = 0;  // hist is full, the oldest index has to keep moving

  while (curr != NULL) {
    hist[i] = curr;
    if (full && i == oldest)
      oldest = (i+1)%N;
    i = (i+1)%N;
    if (i == 0)
      full = 1;
    curr = curr->next;
  }
  // remove the hist[oldest+1] node:  
  if (!full) {
    // there are no n+1 nodes in the list then n must == sizeof(list)
    // as the pre-condition "n <= sizeof(list)"
    return head->next;
  } else {
    Node * prev, * removal;

    prev = hist[oldest];
    removal = hist[(oldest+1)%N];
    prev->next = removal->next;
  }  
  return head;
}

int main(int argc, char * argv[]) {
  Node * head = (Node*)malloc(sizeof(Node));
  Node * tail = head;

  head->val = 0;
  tail->next = NULL;
  for (int i = 1; i < 5; i++) {
    tail->next = (Node*)malloc(sizeof(Node));
    tail = tail->next;
    tail->val = i;
    tail->next = NULL;
  }

  tail = head;
  while (tail!=NULL) {
    printf("%2d", tail->val);
    tail = tail->next;
  }
  tail = removeNthFromEnd(head, atoi(argv[1]));
  printf("\n");
  while (tail!=NULL) {
    printf("%2d", tail->val);
    tail = tail->next;
  }
}
