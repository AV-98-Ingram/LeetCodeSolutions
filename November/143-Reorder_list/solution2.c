/*
    1 -> 2 .. -> n-1 -> n
   head                tail 
    1    2
    2    4    
 */
#define Node struct ListNode

void reorderList(struct ListNode* head){
  int numNodes = 0;
  Node * node = head;

  while (node != NULL) {
    numNodes++;
    node = node->next;
  }

  int reorder_start = numNodes / 2 + 1;
  Node * nodes[numNodes - reorder_start + 1]; // nodes include the last non-reorder node and all reordering nodes
                                              // so that we can set the last one to point to NULL

  node = head;
  for (int i = 0; i < numNodes; i++) {
    if (i >= reorder_start - 1)
      nodes[i - reorder_start + 1] = node;
    node = node->next;
  }
  node = head;  
  for (int i = numNodes - reorder_start; i > 0; i--) {
    nodes[i]->next = node->next;
    node->next = nodes[i];
    node = nodes[i]->next;
    nodes[i-1]->next = NULL;
  }
}

