/* Description: There are several stones arranged in a row, and each
   stone has an associated value which is an integer given in the
   array stoneValue.  In each round of the game, Alice divides the row
   into two non-empty rows (i.e. left row and right row), then Bob
   calculates the value of each row which is the sum of the values of
   all the stones in this row. Bob throws away the row which has the
   maximum value, and Alice's score increases by the value of the
   remaining row. If the value of the two rows are equal, Bob lets
   Alice decide which row will be thrown away. The next round starts
   with the remaining row.
   
   The game ends when there is only one stone remaining. Alice's is
   initially zero.

   Return the maximum score that Alice can obtain.
*/
/* Basic idea: Recursively tries all cuts (every cut makes the row
   become a prefix and a suffix) and caches the best score of each
   cut.
 */

#include "string.h"
#include "stdio.h"
#include "stdlib.h"

int play(int *stones, int sum, int start, int end, int size, int (*scores)[size]) {  
  if (start >= end-1) 
    return 0;
  
  if (scores[start][end - 1] > 0) 
    return scores[start][end - 1];
  
  float half = ((float)sum)/2.0;
  int mysum = 0, score = 0, max = 0;
  
  for (int i = start; i < end; i++) {
    mysum += stones[i];
    if (mysum == half) {
      int left = play(stones, mysum, start, i+1, size, scores) + mysum;
      int right = play(stones, mysum, i+1, end, size, scores) + mysum;
      
      score = left > right ? left : right;
    }
    else if (mysum > half) 
      score = play(stones, sum - mysum, i+1, end, size, scores) + sum - mysum;
    else if (mysum < half) 
      score = play(stones, mysum, start, i+1, size, scores) + mysum;
    
    if (score > max)
      max = score;
  }
  scores[start][end - 1] = max;
  return scores[start][end - 1];
}



int stoneGameV(int* stoneValue, int stoneValueSize){
  int sum = 0;
  int scores[stoneValueSize][stoneValueSize];

  for (int i = 0; i < stoneValueSize; i++) {
    sum += stoneValue[i];
  }
  memset(scores, 0, sizeof(int) * stoneValueSize * stoneValueSize);
  return play(stoneValue, sum, 0, stoneValueSize, stoneValueSize, scores);
}


int main(int argc, char **argv) {
  int size = argc - 1;
  int in[size];

  for (int i = 1; i <= size; i++) {
    in[i-1] = atoi(argv[i]);
  }

  int score = stoneGameV(in, size);

  printf("score = %d\n", score);
}
