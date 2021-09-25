int lengthOfLastWord(char * s){
  int last_wlen = 0;
  int curr_wlen = 0;
  
  while (*s != 0) {
    if (*s != ' ') {
      curr_wlen++;
    } else {
      if (curr_wlen > 0)
	last_wlen = curr_wlen;
      curr_wlen = 0;
    }
    s++;
  }
  if (curr_wlen > 0)
    last_wlen = curr_wlen;  
  return last_wlen;
}
