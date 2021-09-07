#include "stdlib.h"

#define T int

typedef struct PNode {
  T val;
  struct PNode * next;
  struct PNode * prev;
} PNode;

typedef struct PQueue {
  int size;
  struct PNode * top;
} PQueue;

PQueue * empty_queue() {
  PQueue * queue = (PQueue *)malloc(sizeof(PQueue));
  
  queue->size = 0;
  return queue;
}

int compare(T t1, T t2) {
  if (t1 == t2)
    return 0;
  else if (t1 < t2)
    return -1;
  return 1;
}

char is_empty(PQueue * queue) {
  return queue->size <= 0;
}

PNode * add(PQueue * queue, T val) {
  PNode * new = (PNode *)malloc(sizeof(PNode));
 
  new->val = val;    
  if (is_empty(queue)) {
    queue->top = new;
    queue->top->next = NULL;
    queue->top->prev = NULL;
  } else {
    PNode * node = queue->top;
    PNode * prev = node->prev;
    char inserted = 0;
    
    while (node != NULL) {
      if (compare(val, node->val) < 0) {	
	if (prev == NULL) {
	  prev = new;
	  new->next = node;
	  new->prev = NULL;
	  queue->top = new;
	} else {
	  prev->next = new;
	  new->prev = prev;
	  new->next = node;
	  node->prev = new;
	}
	inserted = 1;
	break;
      }
      prev = node;
      node = node->next;
    }
    if (!inserted) {
      prev->next = new;
      new->next = NULL;
    }
  }
  queue->size++;
  return new;
}

PNode * poll(PQueue * queue) {
  if (is_empty(queue))
    return NULL;
  queue->size--;

  PNode * top = queue->top;

  queue->top = top->next;
  return top;
}

PNode * peek(PQueue * queue) {
  if (is_empty(queue))
    return NULL;
  
  return queue->top;
}

void free_queue(PQueue * queue) {
  PNode * node = queue->top;
  
  while (node != NULL) {
    PNode * next = node->next;

    free(node);
    node = next;
  }
  free(queue);
}

// test
#include "stdio.h"

int main() {
  int a[10] = {9,8,1,2,3,7,6,4,5};
  PQueue * queue = empty_queue();

  for (int i = 0; i < 10; i++)
    add(queue, a[i]);
  for (int i = 0; i < 8; i++) {
    PNode * n = poll(queue);

    printf("%3d", n->val);
    free(n);
  }
  free_queue(queue);
}



