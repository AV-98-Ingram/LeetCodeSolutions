/* DUMB Leetcode cannot recognize my C code again. Trash Leetcode. */

char table[27][9];

//book keeping macros:
#define ROW(i)   (table[i])
#define COL(i)   (table[9+(i)])
#define SUB(i)   (table[18+(i)])

char update(int r, int c, int val) {
  if (val == 0)
    return 1;
  val--; // decr for 0-based indexing
  if (ROW(r)[val] > 0)
    // val is duplicated in row r
    return 0;
  ROW(r)[val] = 1;
  if (COL(c)[val] > 0)
    // val is duplicated in col c
    return 0;
  COL(c)[val] = 1;

  int sub_idx = (r / 3) * 3 + c / 3;

  if (SUB(sub_idx)[val] > 0)
    return 0;
  SUB(sub_idx)[val] = 1;
  return 1;
}

char isValidSudoku(char** board, int boardSize, int* boardColSize){
  for (int r = 0; r < 9; r++)
    for (int c = 0; c < 9; c++) {
      int val = board[r][c] == '.' ? 0 : board[r][c] - '0';
      
      if (!update(r, c, val))
	return 0;
    }
  return 1;
}

#include "stdio.h"

int main() {
  char sudoku[9][9] =
    {{'.','8','7','6','5','4','3','2','1'},
     {'2','.','.','.','.','.','.','.','.'},
     {'3','.','.','.','.','.','.','.','.'},
     {'4','.','.','.','.','.','.','.','.'},
     {'5','.','.','.','.','.','.','.','.'},
     {'6','.','.','.','.','.','.','.','.'},
     {'7','.','.','.','.','.','.','.','.'},
     {'8','.','.','.','.','.','.','.','.'},
     {'9','.','.','.','.','.','.','.','.'}};
  
  char * board[9];
  for (int i = 0; i < 9; i++)
    board[i] = &sudoku[i][0];
  
  printf("%d\n", isValidSudoku(board, 9, (void*)0));
}
