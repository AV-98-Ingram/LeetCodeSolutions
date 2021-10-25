#define lowerCase(c)   ('A' <= (c) && (c) <= 'Z'? c-('A'-'a') : c)
#define alphaNumeric(c) (('a' <= (c) && (c) <= 'z') || ('0' <= (c) && (c) <= '9'))

bool isPalindrome(char * s) {
  char * b = s;
  char * e = s + (strlen(s)-1);

  while (e - b > 0) {
    char bc = lowerCase(*b);
    char ec = lowerCase(*e);

    if (alphaNumeric(bc) && alphaNumeric(ec)) {
      if (bc == ec) {
	b++;
	e--;
	continue;
      } else
	return 0;
    }
    if (!alphaNumeric(bc))
      b++;
    if (!alphaNumeric(ec))
      e--;
  }  
}
