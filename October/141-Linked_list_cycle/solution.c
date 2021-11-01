bool hasCycle(struct ListNode *head) {
  while (head != NULL) {
    if (head->val == 100001)
      return 1;    
    head->val = 100001;
    head = head->next;
  }
  return 0;
}
