
int strStr(char * haystack, char * needle){
  char * head = haystack;

  if (*needle == 0)
    return 0;    
  while (*haystack != 0) {
    if (*haystack == *needle) {
      char * p1 = haystack + 1;
      char * p2 = needle + 1;
      char matched = 1;
      
      while (*p2 != 0) {
	if (*p1 == 0 || *p1 != *p2) {
	  matched = 0;
	  break;// unmatch
	}
	p1++;
	p2++;
      }
      if (matched)
	return haystack - head;      
    }
    haystack++;
  }
  return -1;
}
