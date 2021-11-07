#include "string.h"
#include "stdio.h"

int partition(int * a, int pivot, int size) {
  int left = pivot, right = pivot;

  while (left >= 0) {
    if (a[left] > a[pivot]) {
      while (right < size && a[right] >= a[pivot])
	right++;
      if (right < size) {
	// swap left and right:
	int tmp = a[left];
	
	a[left] = a[right];
	a[right] = tmp;
	right++;
      } else {
	// adjust the pivot to left:
	int tmp = a[left];
	
	a[left] = a[pivot - 1];
	a[pivot - 1] = a[pivot];
	a[pivot] = tmp;
	pivot--;
      }
    }
    left--;
  }
  while (right < size) {
    if (a[right] < a[pivot]) {
      // adjust the pivot to right:
      int tmp = a[right];

      a[right] = a[pivot + 1];
      a[pivot + 1] = a[pivot];
      a[pivot] = tmp;
      pivot++;
    }
    right++;
  }
  return pivot;
}

void quick_sort(int * arr, int size) {
  if (size <= 1)
    return;

  int pivot = size / 2;

  pivot = partition(arr, pivot, size);
  quick_sort(arr, pivot);
  quick_sort(arr + pivot, size - pivot);
}


int minDeletions(char * s){
  int table[26];

  memset(table, 0, sizeof(int)*26);
  while (*s != 0) {
    table[(*s) - 'a']++;
    s++;
  }
  quick_sort(table, 26);

  int ndeletes = 0;
  int max = table[25];
  char freqs[max + 1];

  memset(freqs, 0 , max+1);
  for (int i = 25; i >= 0; i--) {
    if (table[i] == 0)
      break;
    while (table[i] > 0 && freqs[table[i]]) {
      ndeletes++;
      table[i]--;
    }
    freqs[table[i]] = 1;
  }
  return ndeletes;
}

int main() {
  char * in = "hickhhbonoibomfodddk";

  printf("%d\n", minDeletions(in));
}
