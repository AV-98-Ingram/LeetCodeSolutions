/* Takes an input, converts it to number and adds it to the result.
   Specially, when the input is 'I', 'X', and 'C', we need to look
   ahead one more for IV,IX, XL, XC, CD, and CM.
*/

int scan(char *s, int result) {
  switch (*s) {
  case 0:
    return result;
  case 'I': { //1
    if (*(s+1) == 'V')
      return scan(s + 2, result + 4);
    if (*(s+1) == 'X')
      return scan(s + 2, result + 9);
    return scan(s + 1, result + 1);
  }
  case 'V': { //5
    return scan(s + 1, result + 5);
  }
  case 'X': { //10
    if (*(s+1) == 'L')
      return scan(s + 2, result + 40);
    if (*(s+1) == 'C')
      return scan(s + 2, result + 90);
    return scan(s + 1, result + 10);
  }
  case 'L': { //50
    return scan(s + 1, result + 50);
  }
  case 'C': { //100
    if (*(s+1) == 'D')
      return scan(s + 2, result + 400);
    if (*(s+1) == 'M')
      return scan(s + 2, result + 900);
    return scan(s + 1, result + 100);
  }
  case 'D': { //500
    return scan(s + 1, result + 500);
  }
  case 'M': { //1000
    return scan(s + 1, result + 1000);
  }
  }
  return -1;
}


int romanToInt(char * s){
  return scan(s, 0);
}
