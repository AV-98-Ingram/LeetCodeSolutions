#include "stdio.h"
#include "stdlib.h"
#include "string.h"

char * addBinary(char * a, char * b){
  char carry = 0;
  int a_len = strlen(a);
  int b_len = strlen(b);
  
  if (b_len > a_len) {
    char * tmp = b;
    
    b = a;
    a = tmp;
    
    int tmp_len = a_len;

    a_len = b_len;
    b_len = tmp_len;
  }// now always len(a) >= len(b)

  int a_i = a_len-1, b_i = b_len-1;

  while (b_i >= 0) {
    a[a_i] = (a[a_i] - '0') + (b[b_i] - '0') + carry;
    if (a[a_i] >= 2) {
      carry = 1;
      a[a_i] = a[a_i] - 2 + '0';
    } else {
      carry = 0;
      a[a_i] += '0';
    }
    a_i--; b_i--;
  }
  while (a_i >= 0 && carry == 1) {
    a[a_i] = (a[a_i] - '0') + carry;
    if (a[a_i] >= 2) {
      a[a_i] = a[a_i] - 2 + '0';
    } else {
      carry = 0;
      a[a_i] += '0';
    }
    a_i--;
  }
  if (a_i < 0 && carry == 1) {
    char * new_bits = malloc(a_len + 2);

    memcpy(new_bits+1, a, a_len);
    new_bits[0] = '1';
    new_bits[a_len + 1] = 0; 
    return new_bits;
  }
  return a;
}


int main(int argc, char * argv[]) {
  printf("%s\n", addBinary(argv[1], argv[2]));
}
