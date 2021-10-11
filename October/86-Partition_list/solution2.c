/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */


#define Node struct ListNode

struct ListNode* partition(struct ListNode* head, int x){
  Node * post_head = NULL, * post_tail = NULL;
  Node * p = head, *prev = NULL;

  while (p != NULL) {
    Node * next = p->next;

    if (p->val >= x) {
      // takes p off and appends it after post_tail:
      if (prev != NULL) {
	prev->next = next;
      } else 
	head = next;
      if (post_head == NULL) {
	post_head = p;
	post_tail = p;
      }
      post_tail->next = p;
      post_tail = p;
    } else
      prev = p;
    p = next;
  }
  if (post_tail != NULL)
    post_tail->next = NULL;
  if (head == NULL) {
    head = post_head;
  } else {
    prev->next = post_head;
  }
  return head;
}
