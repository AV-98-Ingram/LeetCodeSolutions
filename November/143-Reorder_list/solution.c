/*
    1 -> 2 .. -> n-1 -> n
   head                tail 
    1    2
    2    4    
 */
#define Node struct ListNode
Node * reorder(Node * head, Node * tail);
void reorderList(struct ListNode* head){
  if (head == NULL || head->next == NULL)
    return;

  Node * tail = head, * faster = head->next;
  while (faster != NULL) {
    tail = tail->next;
    if (faster->next == NULL)
      break;
    faster = faster->next->next;
  }
  // tail->next now is the one needs to be inserted back
  reorder(head, tail->next);
  tail->next = NULL;
}

/*
  Returns the "new head" after reordering all elements after tail (including tail).
  The "new head" is the node following the tail after reordering.
  e.g.,
  Given 1 -> 2 -> 3,  f(1, 3) = 2 and the list becomes 1 -> 3 -> 2.
  
*/
Node * reorder(Node * head, Node * tail) {
  if (tail == NULL)
    return head;  
  
  Node * new_head = reorder(head, tail->next);
  
  tail->next = new_head->next;
  new_head->next = tail;
  return tail->next;
}
