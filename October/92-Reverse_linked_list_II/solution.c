#define Node struct ListNode

struct ListNode* reverseBetween(struct ListNode* head, int left, int right){
  Node * left_head = NULL;
  Node * node = head;
  int i = left;

  while (i > 1) {
    left_head = node;
    node = node->next;
    i--;
  } // node refers to left; left_head is either NULL or refers to the node before left

  Node * reverse_tail = NULL;
  Node * reverse = NULL;
  
  i = right;
  while (i >= left) {
    Node * curr = node;

    node = node->next;
    curr->next = reverse;
    reverse = curr;
    if (reverse_tail == NULL)
      reverse_tail = reverse;
    i--;
  }
  reverse_tail->next = node;
  if (left_head != NULL)
    left_head->next = reverse;
  else
    head = reverse;
  return head;
}
