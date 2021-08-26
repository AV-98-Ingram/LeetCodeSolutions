/*  This problem itself is easy.  If I ever meet this problem in real
    interview, I should use Java so that I can use List easily.

    I wrote a list by myself and I made a mistake in it.  In addition,
    we did not carefully read the description so that I thought 9000
    is "IM" but in fact it is "CM".

    My solution here is relatively general but slow.  I could do some
    hack to make it fast as the input is bounded only in between 1 and
    3999.
 */

#include "stdlib.h"
#include "stdio.h"

typedef struct List {
  struct List * next;
  char value;
} List;

typedef struct List_head {
  List * head;
  List * last;
  int size;
} List_head;

List_head * new_list() {
  List_head* head = (List_head *)malloc(sizeof(List_head));

  head->head = NULL;
  head->last = NULL;
  head->size = 0;
  return head;
}

void add(List_head * list, char val) {
  List * new = (List *)malloc(sizeof(List));

  new->next = NULL;
  new->value = val;
  if (list->head == NULL) {
    list->head = new;
    list->last = new;
  } else {    
    list->last->next = new;
    list->last = new;
  }
  list->size++;
}

char * list_to_str(List_head * result) {
  int size = result->size;
  char * str = (char*)malloc(size + 1);
  char * str_head = str;
  List * list = result->head;
  
  while (list != NULL) {
    *str = list->value;
    list = list->next;
    str++;
  }
  *str = 0;
  return str_head;
}

void free_list(List * lst) {
  if (lst != NULL) {
    free_list(lst->next);
    free(lst);
  }
}

// pre-cond: 1 <= num <= 3999
char * intToRoman(int num){
  int digits[4] = {0}; // initializes all to zero
  int idx = 3;

  while (num > 0) {
    int digit = num % 10;

    digits[idx--] = digit;
    num = num / 10;
  }

  char symbols[4][2] = {{'M', 0}, {'C', 'D'}, {'X', 'L'}, {'I', 'V'}};
  List_head * result = new_list();
  
  for (int i = 0; i < 4; i++) {
    if (i == 0)
      for (int j = 0; j < digits[i]; j++)
	add(result, symbols[i][0]);
    else {
      int digit = digits[i];
      
      if (digit >= 5) {
	// 5 <= digit <= 9
	if (digits[i] == 9) {
	  add(result, symbols[i][0]);   // symbol of 10^(current place)
	  add(result, symbols[i-1][0]); // symbol of 10^(current place + 1)
	  digit = 0; // done convert this digit
	} else {
	  add(result, symbols[i][1]); // symbol of 5 * 10^(current place)
	  digit -= 5;
	}
      } else {
	// 0 <= digit < 5
	if (digits[i] == 4) {
	  add(result, symbols[i][0]); // symbol of 10^(current place)
	  add(result, symbols[i][1]); // symbol of 5 * 10^(current place)
	  digit = 0; // done convert this digit
	}        
      }

      // repeats the symbol of 10^(current place) for the "remaining digits" times:
      for (int j = 0; j < digit; j++)
	add(result, symbols[i][0]);
    }
  }
  
  char * str = list_to_str(result);

  free_list(result->head);
  free(result);
  return str;
}

int main(int argc, char * argv[]) {
  int num = atoi(argv[1]);

  printf("%s\n", intToRoman(num));
}
