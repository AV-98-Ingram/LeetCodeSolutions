/*
  I didn't read the description carefully so that I wrote a program
  that partitioned the list into three parts: < x, = x, and > x with
  relative order preservation.  In fact, it only needs two parts: < x
  and >= x.  See solution2.c.
 */


#define Node struct ListNode

struct ListNode* partition(struct ListNode* head, int x){
  Node * post_head = NULL, * post_tail = NULL,  * target_head = NULL, * target_tail = NULL;
  Node * p = head, *prev = NULL;

  while (p != NULL) {
    Node * next = p->next;

    if (p->val == x) {
      // takes p off and adds it before target_head
      if (prev != NULL) {
	prev->next = next;
      } else
	head = next;
      p->next = target_head;
      target_head = p;
      if (target_tail == NULL)
	target_tail = p;
    } else if (p->val > x) {
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
    head = target_head;
    if (head == NULL)
      return post_head;
    else
      target_tail->next = post_head;
  } else if (target_head == NULL) {
    prev->next = post_head;
  } else {
    prev->next = target_head;
    target_tail->next = post_head;
  }
  return head;
}
