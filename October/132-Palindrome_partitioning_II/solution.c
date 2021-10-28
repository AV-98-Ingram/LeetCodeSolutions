/*
  Let minCut[i] be the minimum cut required for the string[0 .. i] (i excluisve).
  We use DP to compute minCut[i+1] from computed results minCur[0 .. i]. 
  Let string[0 .. i+1] be "c_0 c_1 ... c_i".  

  minCut[i+1] = min(   minCut[i] + 1,  // i.e., result of "c_0 c_1 ... c{i-1}" with a partition "c_i"
                       minCut[x] + 1,  // i.e., result of "c_0 c_1 .. c_{x-1}" with a partition "c_x .. c_i" such that 
                                       // "c_x .. c_i" is a palindrome string
                       ...,
                    )

  In addition, checking whether a string is palindrome can also be done by DP:

  To check if "c_x .. c_i" is a palindrome, we check c_x == c_i and cache[x+1][i-1], where the cache is a cache for all
  checked palindromes.  If "c_{x+1} .. c_{i-1}" is a palindrome, it must have already been validated in last iteration.

 */

int p(char* a, int b, int * c, const int n, char (*restrict)[n]);

int minCut(char * s) {
  int len = strlen(s);
  int hist[len+1];
  char seenPalinds[len][len+1];

  memset(seenPalinds, 0, len*(len+1));
  hist[0] = -1;
  return p(s, len, hist, len+1, seenPalinds);
}


int p(char * s, int end, int * hist, const int len, char (* restrict seenPalinds)[len]) {
  if (end == 1) {
    hist[1] = 0;
    return 0;
  }
  p(s, end - 1, hist, len, seenPalinds);
  
  int min_cut = end-1;
  
  for (int i = 0; i < end; i++)
    if (s[i] == s[end-1]) {
      char is_palind;
      
      if (i + 1 == end || i + 1 == end - 1 || i + 2 == end - 1)
	is_palind = 1;
      else
	is_palind = seenPalinds[i+1][end-1];
      if (is_palind) {
	int new_cut = hist[i] + 1;
	
	if (new_cut < min_cut)
	  min_cut = new_cut;
	seenPalinds[i][end] = 1;
      }
    }
  hist[end] = min_cut;
  return min_cut;
}
