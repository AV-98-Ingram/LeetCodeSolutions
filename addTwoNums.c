/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

struct ListNode {                                                                                                                           
      int val;                                                                                                                                
      struct ListNode *next;                                                                                                                  
};

#define NULL (void*)0

// pre-condition !(l1 == null && l2 == null)
int addTwoDigits(struct ListNode* l1, struct ListNode* l2, int addon, struct ListNode* sum) {
  int digit;
  int new_addon = 0;

  if (l1 == NULL)
    digit = l2->val + addon;
  else if (l2 == NULL)
    digit = l1->val + addon;
  else {
    digit = l1->val + l2->val + addon;
  }
  if (digit >= 10) {
    digit -= 10;
    new_addon = 1;
  }
  sum->val = digit + addon;
  sum->next = NULL;    
  return new_addon;
}

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){
  struct ListNode *sum, *tail, *prev;
  int addon = 0;
  
  while (l1 != NULL || l2 != NULL) {
    tail = (struct ListNode*)malloc(sizeof(struct ListNode));

    if (sum == NULL) {
      sum = tail;
      prev = tail;
    } else {
      prev->next = tail;
      prev = tail;
    }
    addon = addTwoDigits(l1, l2, addon, tail);
    if (l1 != NULL) l1 = l1->next;
    if (l2 != NULL) l2 = l2->next;
  }
  tail->val += addon;
  return sum;
}



