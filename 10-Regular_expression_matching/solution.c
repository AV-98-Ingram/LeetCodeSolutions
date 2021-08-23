#include "stdio.h"
#include "string.h"

/* lessons learned: 
   1. Note that the state machine is in fact a Nondeterministic Finite Automaton

   2. As being nondeterministic, all possible states need to be
      explored.  Saving visited states for optimization.

   3. When build the state machine, "a*" must be processed togather.
      I tried process 'a' then process '*' but it won't work because
      processing 'a' will result a regular transition labeled by 'a'.
      But in fact, 'a*' should only result in a self-loop transition
      labeled by a and a non-labeled outgoing transition.
*/
/*
  machine: (S, T), where 

    S: a set of non-negative IDs, each of which is a machine state.

    T: (s ==> s', l)  a transition from machine states s to s' with a label l;
       A label is either a lower case letter or '.' representing any.
       A transition can also have no label.  Let l == 1 represent no label.

  matching state (pos, s), where
    pos is a position in the matching string
    s is the machine state where the string position is placed.

  Implementation:
  S: represented by integers
  T: char[31][31] // (i ==> j, char[i][j])
  Saved maching states:  char[21][31] // char[i][j] == 1 means matching state (i, j) is visited 

  Time Complexity: the searching state space, i.e., len(s) * len(p)
 */

int trans[31][31]; 
char visited[21][31];

#define is_any(x)  ((x) == '.')

void build_machine(char * p) {
  memset(trans, 0, 31 * 31 * sizeof(int));
  int curr_state = 0; // start state
  
  while (*p != 0) {    
    if (*(p+1) == '*') {
      trans[curr_state][curr_state] = *p;
      trans[curr_state][curr_state + 1] = 1; // non-labeled transition
      p+=2;
    } else {
      trans[curr_state][curr_state + 1] = *p;
      p++;
    }
    curr_state++;
  }
}

// match the suffix of s starting at pos from the machine state
char match(char *s, int pos, int state) {
  if (s[pos] == 0) {
    if (trans[state][state + 1] == 0)
      return 1; // string and machine both ends
    if (trans[state][state + 1] == 1)
      if (match(s, pos, state + 1))
	return 1; // string ends and machine will be ended through non-labeled transitions
    return 0;
  } else if (trans[state][state + 1] == 0)
    return 0; // machine terminates while string doesn't
  if (visited[pos][state])
    return 0; // visited previously, return false for this path
  visited[pos][state] = 1;
  if (trans[state][state] != 0) 
    // try *-match:
    if (is_any(trans[state][state]) ||
	trans[state][state] == s[pos])
      if (match(s, pos + 1, state))
	return 1;
  if (is_any(trans[state][state + 1]) ||
      trans[state][state + 1] == s[pos])
    // regular-match:
    if (match(s, pos + 1, state + 1))
      return 1;
  if (trans[state][state + 1] == 1)
    // non-labeled transition move:
    return match(s, pos, state + 1);
  return 0;
}

char isMatch(char * s, char * p){
  memset(visited, 0, 21 * 31);
  build_machine(p);
  return match(s, 0, 0);
}

int main(int argc, char * argv[]) {
  printf("%d\n", isMatch(argv[1], argv[2]));
}



