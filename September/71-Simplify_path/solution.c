#include "stdlib.h"
#include "string.h"

typedef struct List {
  char * name;
  int name_len;
  struct List * next;
  struct List * prev;
  struct List * head;
} List;

List * add(List * l, char * name, int name_len) {
  List * node = (List *)malloc(sizeof(List));

  node->name = name;
  node->name_len = name_len;
  node->prev = l;
  node->next = NULL;
  node->head = node;
  if (l != NULL) {
    l->next = node;
    node->head = l->head;
  }
  return node;
}

// l: null-able
// ensures: l->next == NULL
void delete_after(List * l) {
  if (l != NULL) {    
    delete_after(l->next);
    free(l->next);
  }
}

int parse_path(char * path, List ** list);
char * parse_slashes(char * path);
int parse_dots(char * path);

char * simplifyPath(char * path) {
  List * list = NULL;
  List * head = NULL; // head of list
  int totalSize = parse_path(path, &list);
  char * result = (char *)malloc(totalSize + 2); // in case of totalSize == 0, we need to print '\' + '\0;

  memset(result, 0, totalSize + 2);
  if (list != NULL) {
    char * p = result;
    
    head = list->head;
    while (head != NULL) {
      *p = '/';
      p++;
      memcpy(p, head->name, head->name_len);
      p+=head->name_len;
      head = head->next;
    }
  } else
    result[0] = '/';
  return result;
}

int parse_path(char * path, List ** list) {
  if (*path == 0)
    return 0;
  path = parse_slashes(path);
  if (*path == 0)
    return 1;
  
  char num_dots = parse_dots(path);

  if (num_dots == 1) 
    // ignore:
    return parse_path(path + num_dots, list);
  if (num_dots == 2) {
    if (*list != NULL) {
      // go back to last dir:
      int curr_dir_name_len = (*list)->name_len;
      List * prev = (*list)->prev;

      free(*list);
      if (prev != NULL) 
	prev->next = NULL;
      *list = prev;
      return parse_path(path + num_dots, list) - curr_dir_name_len - 1;
    } else
    // otherwise, no-op
      return parse_path(path + num_dots, list) + 1 + num_dots;
  }

  char * p = path;
  
  while (*p != '/' && *p != 0)
    p++;
  (*list) = add(*list, path, p - path);
  return parse_path(p, list) + 1 + (p - path);
}

char * parse_slashes(char * path) {
  while (*path == '/')
    path++;
  return path;
}

int parse_dots(char * path) {
  if (*path == '.') {
    if (*(path + 1) == '.') {
      if (*(path + 2) == '/' || *(path + 2) == 0)
	return 2;
    } else if (*(path + 1) == '/' || *(path + 1) == 0)
      return 1;
  }
  return 0;
}

#include "stdio.h"

int main(int argc, char * argv[]) {
  printf("%s\n", simplifyPath(argv[1]));
}
