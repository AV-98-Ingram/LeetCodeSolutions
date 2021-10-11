#define Node struct ListNode

struct ListNode* reverseList(struct ListNode* head){
  Node * node = head;
  Node * reversed = NULL;

  while (node != NULL) {
    Node * next = node->next;

    node->next = reversed;
    reversed = node;
    node = next;
  }
  return reversed;
}
