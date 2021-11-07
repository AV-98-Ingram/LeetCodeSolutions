#include "stdint.h";
#define Bit int32_t

Bit toBit(char * w) {
  Bit b = 0;
  
  while (*w != 0) {
    int shift = *w - 'a';

    b = b | (1 << shift);
    w++;
  }
  return b;
}

char test(Bit allowed, Bit b) {
  return (allowed | b) == allowed;
}

int countConsistentStrings(char * allowed, char ** words, int wordsSize){
  Bit b = toBit(allowed);
  int count = 0;
  
  for (int i = 0; i < wordsSize; i++)
    if (test(b, toBit(words[i])))
      count++;
  return count;
}
