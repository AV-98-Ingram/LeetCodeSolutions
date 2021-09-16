/* 
  This problem can be categorized as "Come up a intuitive solution and
  design a data structure for it."  Here the intuitive solution is
  that "I need to know for each number i, how many publications having
  at least i citations?"

  Let atLeastCites[i] be the number of papers of at least i citations.
  To compute atLeastCites[i],  we do 

     atLeastCites[i] = cites[i] + atLeastCites[i+1],

  where cites[i] is the number of papers of i citations.
  Note that the computation of the arrays cites and atLeastCites cost only O(n), respectively.

  With atLeastCites being computed, we only need to find the maximum j
  such that atLeastCites[j] >= j.
 */
#include "stdio.h"
#include "stdlib.h"

#define MAX 1001

 // cache[i] = sum(papersOfCites[i, i+1, MAX-1]), 
                       // so cache[i] = papersofCites[i] + cache[i+1] if i < MAX-1

/* pre-cond: 
     1 <= citationsSize <= 5000 
     0 <= citations[i] <= 1000
*/
int hIndex(int* citations, int citationsSize){
  int papersOfCites[MAX] = {0};

  for (int i = 0; i < citationsSize; i++)
    papersOfCites[citations[i]]++;

  // (papersOfAtLeastCites of i) represents #papers of at least i cites,
  // i.e.,  
  // (papersOfAtLeastCites of i) := papersOfCites[i] +
  // (papersOfAtLeastCites of i+1)
  int papersOfAtLeastCites = 0; 

  papersOfAtLeastCites = 0; 
  for (int i = MAX-1; i >=0; i--) {  
    papersOfAtLeastCites = papersOfCites[i] + papersOfAtLeastCites; // #papers of at least i cites
    if (papersOfAtLeastCites >= i)
      return i;    
  }
  return 0;
}

int main(int argc, char * argv[]) {
  int input[argc-1];

  for (int i = 1; i < argc; i++)
    input[i-1] = atoi(argv[i]);
  printf("%d\n", hIndex(input, argc-1));
}
