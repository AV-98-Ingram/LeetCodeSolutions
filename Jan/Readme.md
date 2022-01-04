**207-Course_schedule**
- topological BFS

**212-Word_search_II**
- using a prefix tree and backtrack BFS
- optimization: delete the path to a found word in the prefix tree IFF
  no other word is sharing the path

**348-Design_tic_tac_toe**
- Simple solution: look at 8 neighbors each step, worst case O(n) complexity per move
- Advanced solution: we only need to count how many moves each play
  has placed on each row, column and the two (cross) diagonals.  Yes,
  there are only two diagonals have maximum length n.  For each move by
  one player, we can update the count of each row, column or either of
  the diagonal in a constant time.

**370-Range_addition**
- I immediately thought about break up the problem to multiple
  independent tasks for easy parallelization.
- The LC solution is amazing.  For a range update `[start, end, incr]`,
  we mark on the result array a that
```
a[start] = incr;
a[end+1] = -incr;
```
meaning that `a[start ...]` will increase by `incr` and `a[end+1 ...]`
will decrease by `incr`.  They together simply mean that the range
a[start .. end] will increase by `incr`.  We just need to do such mark
for each "update" record and scan the result array once at final.

**472-Concatenated_words**
- Build a Trie from shortest word to longest word.  During the build
  of each word, testing if a word segment is already in thee Trie.  
  For example, to build "catdogcat" with "cat", "dog" and
  "catdog" already in Trie:
```
  1. We would first match "cat" in "catdogcat" then we face a branch:
   1.1. try to find if "dogcat" is a word (or a word concatenation), i.e., (almost) repeat step 1 recursively.
   1.2. continue to match until we saw "catdog" and repeat step 1.

```
- We could optimize the solution above by caching words.