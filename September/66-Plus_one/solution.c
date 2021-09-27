/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* plusOne(int* digits, int digitsSize, int* returnSize){
  int carry = 1; // the "one" being plus-ed
  int i;
  
  for (i = digitsSize-1; i >= 0; i--) {
    int newDigit = digits[i] + carry;

    if (newDigit == 10) {
      carry = 1;
      digits[i] = 0;
    } else {
      carry = 0;
      digits[i] = newDigit;
      break;
    }
  }
  *returnSize = digitsSize;
  if (i == -1 && carry == 1) {
    (*returnSize)++;

    int * ret = (int*)malloc(sizeof(int) * (*returnSize));

    memcpy(ret + 1, digits, sizeof(int) * digitsSize);
    ret[0] = 1;
    return ret;
  }
  return digits;  
}
