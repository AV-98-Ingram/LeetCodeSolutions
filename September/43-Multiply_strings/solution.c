#include "string.h"
#include "stdlib.h"

/*
   Simply follow the multiplication algorithm learned in elementary school.
   It needs to take huge carefulness to engineer the code.   
 */

char* mult(char * num, int num_len, char digit, char * result);
char * add(char * base, char * adden, int adden_len);

char * multiply(char * num1, char * num2){
  int len1 = strlen(num1);
  int len2 = strlen(num2);

  if (*num1 == '0' || *num2 == '0')
    return "0";

  
  if (len1 < len2) {
    char * tmp_c = num2;
    int tmp_i = len1;
    
    num2 = num1;
    num1 = tmp_c;
    len1 = len2;
    len2 = tmp_i;
  }
  // Now num1 is no shorter than num2.
  
  char * result = (char *)malloc(len1 + len2 + 2);  // the space for holding the result
  char * result_end = result + len1 + len2;         // result_end points to the last digit in result to update
  char * tmp = (char *)malloc(len1 + 2);            // the space for holding the temporary number: num1 * (a digit of num2)
  char * tmp_end = tmp + len1;                      // tmp_end points to the last digit to write
  char * digit = num2 + (len2 - 1);                 // points to a digit in num2
  
  memset(result, '0', len1 + len2 + 1);
  *(result_end+1) = 0;                              // set '\0' at the end of result
  *(tmp_end+1) = 0;                                 // ditto for tmp
  
  int i = 0;
  char * result_head = result;

  while (digit >= num2) {
    char * tmp_head = mult(num1, len1, *digit, tmp_end);  
    int tmp_num_len = len1 + 1 - (tmp_head - tmp);       // tmp_head points to  num1 * digit

    result_head = add(result_end - i, tmp_head, tmp_num_len); // add (num1 * digit) * 10^i to the result 
    i++;
    digit--;
  }    
  return result_head;
}


/* Multiplies "num" with a "digit" and saves the result in "result". Assuming "result" is large enough.
   
   requires: "result" points to the last digit to write
   return: the pointer to the starting point of the result in "result"
 */
char* mult(char * num, int num_len, char digit, char * result) {
  int i = num_len - 1;
  int carry = 0;
  int digit_int = (int)(digit - '0');
  
  while (i >= 0) {
    int prod = (int)(num[i] - '0') * digit_int + carry;

    if (prod >= 10) {
      carry = prod / 10;
      prod = prod - carry * 10;
    } else
      carry = 0;
    *result = prod + '0';
    result--;
    i--;
  }
  if (carry > 0) {
    *result = carry + '0';
    result--;
  }
  return result + 1;
}

/* 
   Adds "adden" to "base" and saves result in "base".  Assume
   "base" is large enough to hold the result.

   requires "base" points to its last digit
   requires "adden" points to its last digit
   return: the pointer to the starting point of the result in "base"
 */
char * add(char * base, char * adden, int adden_len) {    
  int carry = 0;
  int i = adden_len - 1;
  
  while (i >= 0) {
    int base_digit = (int)(*base - '0');
    int sum = (int)(adden[i] - '0') + base_digit + carry;
    
    if (sum >= 10) {
      carry = 1;
      sum -= 10;
    } else
      carry = 0;
    *base = sum + '0';
    base--;
    i--;
  }
  if (carry > 0) {
    *base = carry + '0';
    base--;
  }
  return base + 1;
}

#include "stdio.h"

int main(int argc, char * argv[]) {
  char * num1 = argv[1];
  char * num2 = argv[2];

  printf("%s\n", multiply(num1, num2));
}
