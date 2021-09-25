/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

#define List struct ListNode

struct ListNode* rotateRight(struct ListNode* head, int k){
  if (head == NULL)
    return head;
  
  List * tail = head;
  int size = 1;
  
  while (tail->next != NULL) {
    tail = tail->next;
    size++;
  }  
  if (size == 1)
    return head;  
  k = k % size;
  if (k == 0)
    return head;

  // now we have 0 < k < size.
  // Suppose the list is  1->2->3->4->5, k = 3
  // We just need to let new_head point to 3 and new_tail point to 2.
  // Then append original head (1) to the original tail (5).
  
  int r = size - k - 1;  // #steps to reach new_tail
  List * new_tail = head;

  // 0 <= r < size - 1, so
  // new_tail->next MUST not be NULL after-loop.
  for (int i = 0; i < r; i++) 
    new_tail = new_tail->next;
  
  List * new_head = new_tail->next;
  
  new_tail->next = NULL;
  tail->next = head;
  return new_head;
}
