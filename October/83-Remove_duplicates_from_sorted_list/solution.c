#define List struct ListNode

struct ListNode* deleteDuplicates(struct ListNode* head) {
  List * node = head;
  List * prev = NULL;

  while (node != NULL) {
    if (prev != NULL) {
      if (prev->val == node->val) {
	node = node->next;
	continue;
      } else
	prev->next = node;
    }
    prev = node;
    node = node->next;
  }
  if (prev != NULL)
    prev->next = NULL;
  return head;
}
