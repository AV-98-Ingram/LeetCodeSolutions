#define List struct ListNode

struct ListNode* deleteDuplicates(struct ListNode* head) {
  List * node = head;
  List * prev = NULL;
  
  while (node != NULL) {
    if (node->next != NULL && node->next->val == node->val) {
      int val = node->val;
      
      while (node != NULL && node->val == val)
	node = node->next;
      if (prev == NULL)
	head = node;
      else
	prev->next = node;
      continue;
    }
    prev = node;
    node = node->next;
  }
  return head;
}
