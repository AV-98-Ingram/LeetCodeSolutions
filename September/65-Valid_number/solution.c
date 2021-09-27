char parseDecimalOrInteger(char ** s);
char parseOptionalE(char **s);

#include "stdio.h"

char isNumber(char * s){
  char ** ss = &s;
 
  if (!parseDecimalOrInteger(ss))
    return 0;
  if (!parseOptionalE(ss))
    return 0;
  return **ss == 0; // string must ends
}

// returns the number of digits
int parseDigits(char **s) {
  char * start = *s;
  
  while ('0' <= **s && **s <= '9')
    (*s)++;
  return *s - start;
}

char parseOptionalE(char **s) {
  if (**s == 'e' || **s == 'E')
    (*s)++;
  else
    return 1;

  if (**s == '+' || **s =='-') {
    (*s)++;
    return parseDigits(s) > 0;
  } 
  return parseDigits(s) > 0;
}

char parseDecimalOrInteger(char ** s) {
  if (**s == '+' || **s == '-')
    (*s)++;

  int numIntDigits = parseDigits(s);
  
  if (**s == '.') {
    // parse whatever after '.' and before 'e/E' in a decimal number
    (*s)++;           
    return parseDigits(s) > 0 || numIntDigits > 0;
  }
  return numIntDigits > 0; // this part cannot be empty
}

#include "stdio.h"

int main(int argc, char * argv[]) {
  printf("%d\n", isNumber(argv[1]));
}
